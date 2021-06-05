package Xadrex;

import boardgame.Peça;
import boardgame.Position;
import boardgame.Tabuleiro;

public abstract class XadrexPeça extends Peça {
	private Cor cor;

	public XadrexPeça(Tabuleiro board, Cor cor) {
		super(board);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

	protected boolean PecaInimiga(Position position) {
		XadrexPeça p = (XadrexPeça)getBoard().peca(position);
		return p != null && p.getCor() != cor;
	}
	
}
