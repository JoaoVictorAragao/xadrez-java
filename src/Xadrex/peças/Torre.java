package Xadrex.pe�as;

import Xadrex.Cor;
import Xadrex.XadrexPe�a;
import boardgame.Tabuleiro;

public class Torre extends XadrexPe�a{

	public Torre(Tabuleiro board, Cor cor) {
		super(board, cor);
		
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
}
