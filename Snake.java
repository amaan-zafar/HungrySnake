import java.util.concurrent.ThreadLocalRandom;

public class Snake {
    public Square head;
    public Square tail;
    public int headDir;
    public int newHeadDir;
    int x_dir[] = {-1, 1, 0, 0};
    int y_dir[] = {0, 0, -1, 1};

    int getRandomInt (int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public Snake (int n) {
        head.x = getRandomInt(1, n-1);
        head.y = getRandomInt(1, n-1);
        headDir = getRandomInt(0, 3);
        newHeadDir = headDir;
        tail.x = head.x - x_dir[headDir];
        tail.y = head.y - y_dir[headDir];
    }

}
