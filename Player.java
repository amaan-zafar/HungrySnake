import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player {
    private String userName;
    private String password;
    private boolean isLoggedIn;
    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isLoggedIn = true;
    }

    public String getuserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void logOut() {
        this.isLoggedIn = false;
    }

    public void updateScoreInDb (int newScore, int boardSizeChoice) {
        try {
            Scanner fr = new Scanner(new File("userdb.txt"));
            StringBuffer buffer = new StringBuffer();
            while (fr.hasNextLine()) {
                String line = fr.nextLine();
                String data[] = line.split(",");
                if (data[0].equals(userName)) {
                    int currHighScore = Integer.parseInt(data[boardSizeChoice+2]);
                    if (newScore > currHighScore) {
                        data[boardSizeChoice+2] = Integer.toString(newScore);
                        line = String.join(",", data);
                    }
                }
                buffer.append(line + System.lineSeparator());
            }
            fr.close();
            PrintWriter fw = new PrintWriter(new FileOutputStream("userdb.txt"));
            fw.println(buffer.toString());
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
