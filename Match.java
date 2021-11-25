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
        GameBoard.displaySizeOptions();
        sc = new Scanner(System.in);
        int boardSizeChoice = sc.nextInt();
        gameBoard.initGameBoard(boardSizeChoice);
        
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
            char input = obj.nextLine().charAt(0);
            int in = (int)input - 48;
            snake.setNextHeadDir(in);
        }
        int score = gameBoard.getFinalScore(difficulty);
        System.out.println("Your score is : " + score);
        player.updateScoreInDb(score, boardSizeChoice);
    }
}
