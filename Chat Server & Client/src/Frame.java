import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Frame extends JFrame{

    ObjectOutputStream os;

    JList list_users=new JList();
    JLabel lbl_users=new JLabel("Users: ");

    JButton btn_X=new JButton("X");
    JButton btn_send=new JButton("Send");

    JLabel lbl_messageBoard=new JLabel("Messages");
    JScrollPane scr_messageBoard=null;
    JTextArea txt_messageBoard=new JTextArea();

    JLabel lbl_message=new JLabel("Enter Message");
    JTextArea txt_message=new JTextArea();

    ArrayList<String>users=new ArrayList<>();

    String user="";





    public Frame(ObjectOutputStream os,String user){

        super("Chat Server");
        setSize(420,720);
        this.user=user;
        //setUndecorated(true);
        setLayout(null);
        users.add(user);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list_users.setListData(users.toArray());
        list_users.setEnabled(false);
        scr_messageBoard=new JScrollPane(txt_messageBoard,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txt_messageBoard.setEditable(false);

        btn_X.setBounds(350,0,50,50);
        btn_send.setBounds(200,600,200,50);

        lbl_messageBoard.setBounds(10,10,100,40);
        scr_messageBoard.setBounds(0,50,400,440);

        lbl_users.setBounds(0,480,100,40);
        list_users.setBounds(0,520,200,200);

        lbl_message.setBounds(210,480,40,100);
        txt_message.setBounds(210,520,190,40);

        btn_X.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try{
                os.writeObject(new Command(user+" is logging off",user));
                os.writeObject(new Command(user,2));
                    os.flush();}
                catch(Exception ee){ee.printStackTrace();}
                System.exit(0);
            }
        });
        btn_send.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Command c=new Command(txt_message.getText(),user);
                try{
                    os.writeObject(c);
                    os.flush();
                }catch(Exception ee){ee.printStackTrace();}
                txt_message.setText("");
            }
        });


        add(txt_message);
        add(scr_messageBoard);
        //add(txt_messageBoard);
        add(lbl_message);
        add(lbl_messageBoard);
        add(lbl_users);
        add(list_users);
        add(btn_send);
        add(btn_X);


        this.os=os;
        setVisible(true);
    }
    public void updateUsers(){
        list_users.setListData(users.toArray());
    }

}
