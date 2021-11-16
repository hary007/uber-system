import java.util.Scanner;

public class Login {
    String userName;
    String password;
    String userType;
   boolean logidIn=false;
    void register(String userName,String password,String userType){
        this.userName=userName;
        this.password=password;
        this.userType=userType;
        //add all these to database of the system.
        logidIn=true;
    }
    void login(String userName,String password){
        // check if the username and password are in the database 
        // and then login the user.
        if(logidIn){
            //user is loggid In and show the main menu based on the userType.
        }
    }
}
class NewUser extends Login{
    //String name;
    NewUser(){
        Scanner s= new Scanner(System.in);
        String userName;
        System.out.println("Enter new email");
        userName=s.next();
        //check the database such that the email is unique.

        String password;
        System.out.println("Enter password");
        password=s.next();
        
        String userType;
        System.out.println("Create account as user or driver?");
        userType=s.next();
        s.close();

        register(userName, password, userType);
    }
}
class ExistingUser extends Login{
    //String name;
    ExistingUser(){
        Scanner s= new Scanner(System.in);
        String userName;
        System.out.println("Enter new email");
        userName=s.next();
        //check the database for the existence of email.

        String password;
        System.out.println("Enter password");
        password=s.next();
        s.close();
        //check the database for given userName and password.
        login(userName, password); 
    }
    void editProfile(){
        System.out.println("Enter new password if you want to change it");
        Scanner s=new Scanner(System.in);
        String password;
        password=s.nextLine();
        this.password=password;
        //update the database //
        s.close();
    }
}
