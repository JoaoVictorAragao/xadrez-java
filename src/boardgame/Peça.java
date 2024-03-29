package boardgame;

public abstract class Pe�a {
	protected Position position;
	private Tabuleiro board;
	
	public Pe�a(Tabuleiro board) {
		this.board = board;
		position = null;
	}

	protected Tabuleiro getBoard() {
		return board;
	}
	
	public abstract boolean[][] possivelMovs();
	
	public boolean possivelMov(Position position) {
		return possivelMovs()[position.getRow()][position.getColuna()];
	}
	
	public boolean TemMov(){
		boolean[][] mat = possivelMovs();
		for(int i = 0; i<mat.length; i++) {
			for(int j = 0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
		
	
	
}
