import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {



    public static void main(String[]args){
        try {
            ServerSocket serverSocket=new ServerSocket(8001);

            while(true){
                //accepts a users connection
                Socket socket=serverSocket.accept();
                //make input/output streams to this client
                ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is=new ObjectInputStream(socket.getInputStream());

                ServersListener sl=new ServersListener(is,os);
                Thread t=new Thread(sl);
                t.start();



            }



        }
        catch(Exception e){
            System.out.println("Error in Sever Main");
            e.printStackTrace();
        }

    }



}
