import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class LoginSignup {
    private String userName;
    private String password;

    public LoginSignup(){//constructor
      this.userName="";
      this.password="";
    }  
      
    public Player loginSignupOption() throws FileNotFoundException, IOException{
        Player player;
        System.out.println("Enter 1 for login and 2 for signup");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if (x==1) {
            player = login();
        } else if (x==2) {
            player = signup();
        } else {
            System.out.println("WRONG INPUT! PROVIDE A VALID INPUT");
            player = this.loginSignupOption();
        }
        return player;
    }

    private Player login() throws FileNotFoundException {
        Player player;
        File file = new File("userdb.txt");
        Scanner fr = new Scanner(file);
        System.out.println("Enter userName:");
        Scanner sc1 = new Scanner(System.in);
        userName = sc1.nextLine(); //taking input as userName
        System.out.println("Enter password:");
        password = sc1.nextLine(); //taking password as input
        boolean flag = false;
        while (fr.hasNextLine()) {
            String credential[] = fr.nextLine().split(",");
            // System.out.println(line[0]);
            if (credential[0].equals(userName) && credential[1].equals(password)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("LOGIN SUCCESSFUL");
            player = new Player(userName, password);
            fr.close();
            // Enter userName of function to redirect to main page
        } else {
            System.out.println("WRONG CREDENTIALS ENTERED! TRY TO LOGIN AGAIN");
            player = this.login();
        }
        return player;        
    }

    private Player signup() throws IOException {
        Player player;
        System.out.println("Enter userName:");
        Scanner sc2 = new Scanner(System.in);
        userName = sc2.nextLine();
        Scanner fr = new Scanner(new File("userdb.txt"));
        boolean flag = false;
        while (fr.hasNextLine()) {
            String data[] = fr.nextLine().split(",");
            if (data[0].equals(userName)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("USERNAME ALREADY EXISTS! TRY A DIFFERENT ONE");
            player = signup();
        } else {
            System.out.println("Enter password:");
            password = sc2.nextLine();
            String userData = userName + "," + password;
            for (int i = 0; i < GameBoard.boardSizes.size(); i++) {
                userData += "," + 0;
            }
            PrintWriter fw = new PrintWriter(new FileOutputStream("userdb.txt", true));
            fw.println(userData);
            System.out.println("Signed up successfully");
            fw.close();
            player = new Player(userName, password);
        }
        return player;
    }
}