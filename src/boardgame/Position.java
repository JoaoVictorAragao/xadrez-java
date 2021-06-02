package boardgame;

public class Position {
	private int row;
	private int coluna;
	
	public Position(int row, int coluna) {
		this.row = row;
		this.coluna = coluna;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	@Override
	public String toString() {
		return row +", " + coluna;
		
	}
	
}
