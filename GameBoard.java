import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends TimerTask{
    public static ArrayList<Integer> boardSizes = new ArrayList<Integer>();
    private int sizeSelected;
    public char[][] matrix;
    private Snake snake;
    private Food food;
    boolean gameStatus = true;
    private Timer timer;

    public GameBoard(Timer timer) {
        this.timer = timer;
    }

    public static void addBoardSize (int size) {
        boardSizes.add(size);
    }

    public void displaySizeOptions () {
        for (int i = 0; i < boardSizes.size(); i++) {
            System.out.println("Enter " + i + " to select " + boardSizes.get(i) + "x" + boardSizes.get(i) + "size board");
        }
    }

    public void initGameBoard (int index) {
        this.sizeSelected = boardSizes.get(index);
        matrix = new char[sizeSelected][sizeSelected];
        for (int i = 0; i < sizeSelected; i++) {
            for (int j = 0; j < sizeSelected; j++) {
                matrix[i][j] = '.';
            }
        }
    }

    public int getSizeSelected () {
        return this.sizeSelected;
    }

    
    private int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public void positionSnake (Snake snake) {
        this.snake = snake;
        this.matrix[snake.getHead().getX()][snake.getHead().getY()] = snake.getHead().getSymbol();
        this.matrix[snake.getTail().getX()][snake.getTail().getY()] = snake.getTail().getSymbol();
    }

    public void positionFood (Food food) {
        int x, y;
        do {
            x = getRandomInt(0, sizeSelected);
            y = getRandomInt(0, sizeSelected);
        } while (matrix[x][y] != '.');
        matrix[x][y] = food.getSymbol();
        food.setX(x);
        food.setY(y);
        this.food = food;
    }

    private void printBoard () {
        for (int i = 0; i < sizeSelected; i++) {
            for (int j = 0; j < sizeSelected; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void run() {
        if (snake.getNextHeadX() == food.getX() && snake.getNextHeadY() == food.getY()) {
            System.out.println("Eating food");
            snake.eat(this, food);
        } else 
            snake.moveTail(this);
        snake.moveHead(this, timer);  
        if (gameStatus != false)
        printBoard();
    }
}
