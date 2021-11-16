public class Square {
    	
	private int x, y;
	private char symbol;

	public Square () {
		this.symbol = '.';
	}

	public Square (char symbol) {
		this.symbol = symbol;
	}
	
	// Getters
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public char getSymbol() {
		return symbol;
	}

	// Setters
	public void setX(int newLocation) {
		this.x = newLocation;
	}
	
	public void setY(int newLocation) {
		this.y = newLocation;
	}
	
	public void setSymbol(char newSymbol) {
		this.symbol = newSymbol;
	}
}
