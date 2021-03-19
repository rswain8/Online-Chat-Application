import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServersListener implements Runnable{

    public static int value=0;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private static ArrayList<ObjectOutputStream>outs=new ArrayList<>();
    private static ArrayList<String>names=new ArrayList<>();


    public ServersListener(ObjectInputStream is, ObjectOutputStream os){
        this.os=os;
        this.is=is;
        outs.add(os);

    }

    public void run(){
        try {
           String name=(String)is.readObject();

           while(names.contains(name)){
               os.writeObject(new Command(0));
               name=(String)is.readObject();
           }
           os.writeObject(new Command(1));
           names.add(name);
            for(ObjectOutputStream out:outs){
                for(int i=0;i<names.size();i++){
                out.writeObject(new Command(names.get(i),Command.ONLY_NAME));
                out.flush();
                }
            }
            String messagee=name+" just logged on!";
            for(ObjectOutputStream out:outs){
                out.writeObject(new Command(messagee));
                out.flush();
            }
            while(true) {

                Command c=(Command)is.readObject();

                if(c.getCommand()==Command.LOGOFF)
                {
                    names.remove(c.getName());
                    for(ObjectOutputStream out:outs){
                        for(int i=0;i<names.size();i++){
                            out.writeObject(new Command(Command.RESET_NAMES));
                            out.flush();
                        }
                    }

                }
                for(ObjectOutputStream out:outs){
                    for(int i=0;i<names.size();i++){
                        out.writeObject(new Command(names.get(i),Command.ONLY_NAME));
                        out.flush();
                    }
                }
                if(c.getCommand()!=Command.LOGOFF){
                String message=c.getName()+": "+c.getMessage();
                for(ObjectOutputStream out:outs){
                    out.writeObject(new Command(message));
                    out.flush();
                }}

            }
        }
        catch(Exception e){
            System.out.println("Error in Server Listener Main");
            e.printStackTrace();
        }
        finally {
            outs.remove(os);
        }
    }
}
