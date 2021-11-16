import java.util.*;

public class SnakeGame{
    private static Player curPlayer = null;

    private static void initGame() {
        GameBoard.addBoardSize(15);
        GameBoard.addBoardSize(20);
        GameBoard.addBoardSize(25);
        GameBoard.addBoardSize(30);
    }

    private static void display() {
        System.out.println("Game Controls :");
        System.out.println("Press 0 to move the snake upwards");
        System.out.println("Press 1 to move the snake downwards");
        System.out.println("Press 2 to move the snake to the Left");
        System.out.println("Press 3 to move the snake to the Right");
        System.out.println("\n\nDifficulty Levels :");
        for (int n : GameBoard.boardSizes) {
            System.out.println(n + "x" + n + " size board :");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(". ");
                }
                System.out.println();
            }
        }
        System.out.println("Easy");
        System.out.println("Medium");
        System.out.println("Hard");
    }
    
    private static void loginPlayer() {
        do {
            System.out.println("\nEnter your name to login:");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            curPlayer = new Player(name);
            System.out.println("Press 1 to start the game, 2 to logout.");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    Match match = new Match(curPlayer);
                    match.startMatch();
                    break;
                case 2:
                    curPlayer.logOut();
                    break;
            
                default:
                    System.out.println("Invalid Input");
                    break;
            }

        } while (!curPlayer.isLoggedIn());
    }

    public static void main(String[] args) {
        initGame();
        display();
        loginPlayer();
    }
}