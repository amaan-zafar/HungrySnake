public class Player {
    private static int players;
    private int id;
    private String name;
    // private boolean isRegistered;
    private boolean isLoggedIn;
    private int currScore;
    private int highestScore;

    public Player(String name) {
        players++;
        id = id + players;
        this.name = name;
        this.isLoggedIn = true;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return this.currScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
        if (currScore > highestScore)
            highestScore = currScore;
    }

    // public boolean isRegistered() {
    //     return isRegistered;
    // }

    // public void registerPlayer() {
    //     this.isRegistered = true;
    // }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void logOut() {
        this.isLoggedIn = false;
    }
}
