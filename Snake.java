import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
    private static final int X_DIR[] = {-1, 1, 0, 0};
    private static final int Y_DIR[] = {0, 0, -1, 1};
    
    private Square head = new Square();
    private Square tail = new Square('*');
    private int headDir;
    private int nextHeadDir;

    
    public Snake (int size) {
        head.setX(getRandomInt(1, size-1));
        head.setY(getRandomInt(1, size-1));
        
        headDir = getRandomInt(0, 3);
        nextHeadDir = headDir;
        
        head.setSymbol(getHeadChar(headDir));
        
        tail.setX(head.getX() - X_DIR[headDir]);
        tail.setY(head.getY() - Y_DIR[headDir]);
    }
    
    private int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private char getHeadChar (int n) {
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
                ch = '^';
                break;
        }
        return ch;
    }
    
    public Square getHead() {
        return head;
    }

    public Square getTail() {
        return tail;
    }

    public int getHeadDir() {
        return headDir;
    }

    public int getNextHeadDir() {
        return nextHeadDir;
    }

    public int getNextHeadX () {
        return head.getX() + X_DIR[nextHeadDir];
    }

    public int getNextHeadY () {
        return head.getY() + X_DIR[nextHeadDir];
    }

    public void setHeadDir(int headDir) {
        this.headDir = headDir;
    }

    public void setNextHeadDir(int nextHeadDir) {
        this.nextHeadDir = nextHeadDir;
    }

    public void eat (GameBoard gameBoard, Food food) {
        gameBoard.matrix[head.getX()][head.getY()] = tail.getSymbol();
        gameBoard.noOfFoodEaten++;
        gameBoard.positionFood(food);
    }

    public void moveTail(GameBoard gameBoard) {
        gameBoard.matrix[tail.getX()][tail.getY()] = '.';
        tail.setX(tail.getX() + X_DIR[headDir]);
        tail.setY(tail.getY() + Y_DIR[headDir]);
        gameBoard.matrix[head.getX()][head.getY()] = tail.getSymbol();
    }

    public void moveHead(GameBoard gameBoard, Timer timer) {
        int nextTailX = tail.getX() + X_DIR[headDir];
        int nextTailY = tail.getY() + Y_DIR[headDir];
        if (nextTailX == -1 || nextTailX == gameBoard.getSizeSelected() || nextTailY == -1 || nextTailY == gameBoard.getSizeSelected() || gameBoard.matrix[nextTailX][nextTailY] != tail.getSymbol())
        this.headDir = nextHeadDir;
        head.setX(head.getX() + X_DIR[nextHeadDir]);
        head.setY(head.getY() + Y_DIR[nextHeadDir]);
        if (head.getX() == -1 || head.getY() == -1 || head.getX() == gameBoard.getSizeSelected() || head.getY() == gameBoard.getSizeSelected()) {
            System.out.println("Game Ended!\nEnter 1 to know your score");
            gameBoard.gameStatus = false;
            Match.gameStatus = false;
            timer.cancel();
        } else
        gameBoard.matrix[head.getX()][head.getY()] = getHeadChar(nextHeadDir);
    }
}
