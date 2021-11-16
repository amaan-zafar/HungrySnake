import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Match {
    private GameBoard gameBoard;
    private Player player;
    private Snake snake;
    Timer timer;
    public static boolean gameStatus = true;

    public Match(Player player) {
        this.player = player;
    }

    int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static char getHeadChar (int n) {
        char ch;
        switch (n) {
            case 0:
                ch = '^';
                break;
            case 1:
                ch = 'v';
                break;
            case 2:
                ch = '<';
                break;
            case 3:
                ch = '>';
                break;
        
            default:
                ch = '~';
                break;
        }
        return ch;
    }


    public void startMatch() {
        // Initialise GameBoard
        gameBoard = new GameBoard();
        gameBoard.displaySizeOptions();
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        gameBoard.selectSize(index);
        int n = gameBoard.getSizeSelected();
        
        snake = new Snake(n);
        gameBoard.positionSnake(snake);
        gameBoard.positionFood();

        timer = new Timer();
        timer.schedule(new GameBoard(), 0, 2000);
        while (gameStatus) {
            Scanner obj = new Scanner(System.in);
            Game.userInput = obj.nextLine().charAt(0);
            int in = (int)Game.userInput - 48;
            snake.newHeadDir = in;
        }
    }
}
