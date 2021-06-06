package Xadrex;

import boardgame.Peça;
import boardgame.Position;
import boardgame.Tabuleiro;

public abstract class XadrexPeça extends Peça {
	private Cor cor;
	private int count;
	
	public XadrexPeça(Tabuleiro board, Cor cor) {
		super(board);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

	public void SobeCount() {
		count++;
	}
	
	public void DesceCount() {
		count--;
	}
	
	public int getCount() {
		return count;
	}
	
	public PosicaoXadrez getXadrezPosition() {
		return PosicaoXadrez.fromPosition(position);
	}
	
	protected boolean PecaInimiga(Position position) {
		XadrexPeça p = (XadrexPeça)getBoard().peca(position);
		return p != null && p.getCor() != cor;
	}
	
}
