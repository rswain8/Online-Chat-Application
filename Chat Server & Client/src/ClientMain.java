import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
public class ClientMain {



    public static void main(String[]args){
        String user="";
        try {
            Scanner s=new Scanner(System.in);

            //creates a connection
            Socket socket=new Socket("10.195.75.25",8001);

            //make input/output streams to this client
            ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is=new ObjectInputStream(socket.getInputStream());

            System.out.print("Enter a username: ");
            user=s.next();
            os.writeObject(user);
            Command c=(Command)is.readObject();
            if(c.getCommand()==0){
                while(c.getCommand()==0){
                    System.out.print("Invalid username, try again : ");
                    user=s.next();
                    os.writeObject(user);
                    c=(Command)is.readObject();
                }
            }
            System.out.println("Successful");



            ClientListener cl=new ClientListener(is,new Frame(os,user));

            Thread t=new Thread(cl);
            t.start();

        }
        catch(Exception e){
            System.out.println("Error in Clinet Main");
            e.printStackTrace();
        }

    }
}
