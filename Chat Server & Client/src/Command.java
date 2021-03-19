import java.io.Serializable;
import java.util.ArrayList;

public class Command implements Serializable{

    public static final int NOT_VALID_USERNAME=0;
    public static final int VALID_USERNAME=1;
    public static final int LOGOFF=2;
    public static final int ONLY_NAME=3;
    public static final int RESET_NAMES=4;


    private int command;

    private String message="";
    private String name="";
    private String fullMessage="";
    ArrayList<String>names;
    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public Command(int command){
        this.command=command;
    }
    public Command(ArrayList<String>names){
        this.names=names;command=3;
    }
    public Command(String fullMessage){
        this.fullMessage=fullMessage;
    }
    public Command(String name,int noMeaning){
        this.name=name;
        this.command=noMeaning;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Command(String message, String name){
        this.message=message;
        this.name=name;

    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
