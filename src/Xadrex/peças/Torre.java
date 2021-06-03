package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.XadrexPeça;
import boardgame.Tabuleiro;

public class Torre extends XadrexPeça{

	public Torre(Tabuleiro board, Cor cor) {
		super(board, cor);
		
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
}
