package boardgame;

public class Peça {
	protected Position position;
	private Tabuleiro board;
	
	public Peça(Tabuleiro board) {
		this.board = board;
		position = null;
	}

	protected Tabuleiro getBoard() {
		return board;
	}
	
	
}
