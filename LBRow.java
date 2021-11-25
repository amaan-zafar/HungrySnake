public class LBRow {
    private String userName;
    private int[] highScores;

    public LBRow(String line) {
        String data[] = line.split(",");
        this.userName = data[0];
        highScores = new int[GameBoard.boardSizes.size()];
        for (int i = 0; i < highScores.length; i++) {
            highScores[i] = Integer.parseInt(data[i+2]);
        }
    }
    public int[] getHighScores() {
        return highScores;
    }

    public void setScoreAt(int score, int index) {
        this.highScores[index] = score;
    }

    public String getUserName() {
        return userName;
    }
}
