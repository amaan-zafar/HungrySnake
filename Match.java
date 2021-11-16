import java.util.Scanner;
import java.util.Timer;

public class Match {
    private Player player;
    public static boolean gameStatus = true;
    private final int EASY = 1;
    private final int MEDIUM = 2;
    private final int HARD = 4;
    private int difficulty = EASY;

    public Match(Player player) {
        this.player = player;
    }

    public void startMatch() {
        Scanner sc;
        Timer timer = new Timer();
        // Initialise GameBoard
        GameBoard gameBoard = new GameBoard(timer);
        gameBoard.displaySizeOptions();
        sc = new Scanner(System.in);
        int ch = sc.nextInt();
        gameBoard.initGameBoard(ch);
        
        System.out.println("Enter 1 for easy, 2 for medium, 3 for hard");
        sc = new Scanner(System.in);
        int diff = sc.nextInt();
        switch (diff) {
            case 1:
                difficulty = EASY; 
                break;
            case 2:
                difficulty = MEDIUM; 
                break;
            case 3:
                difficulty = HARD; 
                break;
        
            default:
                difficulty = EASY;
                break;
        }
        
        Snake snake = new Snake(gameBoard.getSizeSelected());
        gameBoard.positionSnake(snake);

        Food food = new Food('@');
        gameBoard.positionFood(food);

        timer.schedule(gameBoard, 1000, 4000/difficulty);
        while (gameStatus) {
            Scanner obj = new Scanner(System.in);
            Game.userInput = obj.nextLine().charAt(0);
            int in = (int)Game.userInput - 48;
            snake.setNextHeadDir(in);
        }
    }
}
