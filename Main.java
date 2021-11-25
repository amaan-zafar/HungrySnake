import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main{

    private static ArrayList<LBRow> leaderBoard = new ArrayList<LBRow>();

    private static void initBoardSizes() {
        GameBoard.addBoardSize(10);
        GameBoard.addBoardSize(15);
        GameBoard.addBoardSize(20);
        GameBoard.addBoardSize(25);
    }

    private static void display() {
        System.out.println("\nGame Controls :");
        System.out.println("Press 0 to move the snake upwards");
        System.out.println("Press 1 to move the snake downwards");
        System.out.println("Press 2 to move the snake to the Left");
        System.out.println("Press 3 to move the snake to the Right\n\n");
        System.out.println("Available Board Sizes :\n");
        for (int n : GameBoard.boardSizes) {
            System.out.println(n + "x" + n + " size board :");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    System.out.print(". ");
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("\nDifficulty Levels :\nEasy\nMedium\nHard\n");
    }

    private static void start_logout (Player player) throws IOException, FileNotFoundException {
        System.out.println("Press 1 to start the game, 2 to logout.");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        if (ch==1) {
            Match match = new Match(player);
            match.startMatch();
        } else if (ch==2) {
            player.logOut();
        } else {
            System.out.println("INVALID INPUT! PROVIDE A VALID INPUT");
            start_logout(player);
        }
    }

    private static void showLeaderBoard() {
        System.out.println("Enter 1 to see Leaderboard");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        if (x==1) {
            getScoresFromDb();
            System.out.println("Select a boardsize to see its leaderboard");
            GameBoard.displaySizeOptions();
            sc = new Scanner(System.in);
            int ch = sc.nextInt();
            Collections.sort(leaderBoard, new Comparator<LBRow>(){ 
                public int compare(LBRow o1, LBRow o2) { 
                    return o2.getHighScores()[ch] - o1.getHighScores()[ch];
                }
             });
             System.out.println("\nLeaderboard\n#\tUsername\tHighestScore");
             for (int i = 0; i < leaderBoard.size(); i++) {
                 LBRow row = leaderBoard.get(i);
                 System.out.println(i+1+"\t"+row.getUserName()+"\t\t"+row.getHighScores()[ch]);
             }
        } 
    }

    private static void getScoresFromDb() {
        try {
            Scanner fr = new Scanner(new File("userdb.txt"));
            while (fr.hasNextLine()) {
                String line = fr.nextLine();
                LBRow lbRow = new LBRow(line);
                leaderBoard.add(lbRow);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        
    public static void main(String[] args) throws IOException, FileNotFoundException {
        initBoardSizes();
        // display();
        // LoginSignup ls = new LoginSignup();
        // Player curPlayer = ls.loginSignupOption();
        // start_logout(curPlayer);
        // new Match(new Player("amaan", "zafar")).startMatch();
        showLeaderBoard();  
    }
}