import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard extends TimerTask{
    private int sizeSelected;
    public char[][] matrix;
    public static ArrayList<Integer> boardSizes = new ArrayList<Integer>();
    int x_dir[] = {-1, 1, 0, 0};
    int y_dir[] = {0, 0, -1, 1};
    private int headX;
    private int headY;
    private int headDir;
    private int newHeadDir;
    private int tailX;
    private int tailY;
    private int foodX;
    private int foodY;

    public static void addBoardSize (int size) {
        boardSizes.add(size);
    }

    public void displaySizeOptions () {
        for (int i = 0; i < boardSizes.size(); i++) {
            System.out.println("Enter " + i + " to select " + boardSizes.get(i) + "x" + boardSizes.get(i) + "size board");
        }
    }

    public void selectSize (int index) {
        sizeSelected = boardSizes.get(index);
        matrix = new char[sizeSelected][sizeSelected];
        for (int i = 0; i < sizeSelected; i++) {
            for (int j = 0; j < sizeSelected; j++) {
                matrix[i][j] = '.';
            }
        }
    }

    public int getSizeSelected () {
        return sizeSelected;
    }

    char getHeadChar (int n) {
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

    
    int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public void positionSnake (Snake snake) {
        headX = snake.head.x;
        headY = snake.head.y;
        tailX = snake.tail.x;
        tailY = snake.tail.y;
        headDir = snake.headDir;
        newHeadDir = snake.newHeadDir;
        matrix[headX][headY] = getHeadChar(headDir);
        matrix[headX-x_dir[headDir]][headY-y_dir[headDir]] = '*';
    }

    public void positionFood () {
        do {
            foodX = getRandomInt(0, sizeSelected);
            foodY = getRandomInt(0, sizeSelected);
        } while (!(matrix[foodX][foodY]=='.'));
        matrix[foodX][foodY] = '@';
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        
        if (headX+x_dir[newHeadDir]==foodX && headY+y_dir[newHeadDir]==foodY) {
            matrix[headX][headY] = '*';
            foodX = getRandomInt(0, sizeSelected);
            foodY = getRandomInt(0, sizeSelected);
            matrix[foodX][foodY] = '@';
        } else {
            matrix[tailX][tailY] = '.';
            tailX += x_dir[headDir];
            tailY += y_dir[headDir];
            matrix[headX][headY] = '*';
        }
        headDir = newHeadDir;
        headX += x_dir[headDir];
        headY += y_dir[headDir];
        if (headX==-1 || headY==-1 || headX==10 || headY==10) {
            System.out.println("Game Ended!");
            Match.gameStatus = false;
            // timer.cancel();
        }
        matrix[headX][headY] = getHeadChar(headDir);        
    }


}
