import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

class Game extends TimerTask {

    static int n = 15;

    static Timer timer;

    static int x_dir[] = {-1, 1, 0, 0};
    static int y_dir[] = {0, 0, -1, 1};
    static int headX;
    static int headY;
    static int headDir;
    static int newHeadDir;
    static int tailX;
    static int tailY;
    static int foodX;
    static int foodY;
    static char mat[][] = new char[n][n];
    static char userInput;
    static boolean gameStatus = true;

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

    @Override
    public void run() {
        // printBoard
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        if (headX+x_dir[newHeadDir]==foodX && headY+y_dir[newHeadDir]==foodY) {
            mat[headX][headY] = '*';
            foodX = getRandomInt(0, n);
            foodY = getRandomInt(0, n);
            mat[foodX][foodY] = '@';
        } else {
            mat[tailX][tailY] = '.';
            tailX += x_dir[headDir];
            tailY += y_dir[headDir];
            mat[headX][headY] = '*';
        }
        headDir = newHeadDir;
        headX += x_dir[headDir];
        headY += y_dir[headDir];
        if (headX==-1 || headY==-1 || headX==10 || headY==10) {
            System.out.println("Game Ended!");
            gameStatus = false;
            timer.cancel();
        }
        mat[headX][headY] = getHeadChar(headDir);
    }

    static int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    
    public static void main(String[] args) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = '.';
            }
        }
        headX = getRandomInt(1, n-1);
        headY = getRandomInt(1, n-1);
        headDir = getRandomInt(0, 3);
        newHeadDir = headDir;
        tailX = headX - x_dir[headDir];
        tailY = headY - y_dir[headDir];
        mat[headX][headY] = getHeadChar(headDir);
        mat[headX-x_dir[headDir]][headY-y_dir[headDir]] = '*';
        do {
            foodX = getRandomInt(0, n);
            foodY = getRandomInt(0, n);
        } while (!(mat[foodX][foodY]=='.'));
        mat[foodX][foodY] = '@';

        timer = new Timer();
        timer.schedule(new Game(), 0, 2000);
        while (gameStatus) {
            Scanner sc = new Scanner(System.in);
            Game.userInput = sc.nextLine().charAt(0);
            int in = (int)Game.userInput - 48;
            newHeadDir = in;
        }

    }
    
}


