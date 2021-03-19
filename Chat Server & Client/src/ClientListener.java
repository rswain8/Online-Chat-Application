import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientListener implements Runnable{



    private ObjectInputStream is;
    private Frame frame;

    public ClientListener(ObjectInputStream is,Frame frame){

        this.is=is;
        this.frame=frame;

    }

    public void run(){
        try {

            while(true) {
                Command c=(Command)is.readObject();

                if(c.getFullMessage()!=""){
                    frame.txt_messageBoard.setText(frame.txt_messageBoard.getText()+"\n"+c.getFullMessage());
                }

                if(c.getCommand()==Command.ONLY_NAME){
                    if(!frame.users.contains(c.getName()))
                    frame.users.add(c.getName());

                    frame.updateUsers();
                }
                if(c.getCommand()==Command.RESET_NAMES){
                    frame.users.clear();
                }

            }


        }
        catch(Exception e){
            System.out.println("Error in clients Listener Main");
            e.printStackTrace();
        }

    }
}
