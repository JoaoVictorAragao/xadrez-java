package boardgame;

public class Pe�a {
	protected Position position;
	private Tabuleiro board;
	
	public Pe�a(Tabuleiro board) {
		this.board = board;
		position = null;
	}

	protected Tabuleiro getBoard() {
		return board;
	}
	
	
}
