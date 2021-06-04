package Xadrex;

import boardgame.Tabuleiro;
import Xadrex.pe�as.Rei;
import Xadrex.pe�as.Torre;
import boardgame.Pe�a;
import boardgame.Position;

public class Partida {
	
	private Tabuleiro tabuleiro;
	
	public Partida() {
		tabuleiro= new Tabuleiro(8, 8);
		SetupInicial();
	}
	
	public Pe�a[][] getPecas(){
		Pe�a[][] mat = new Pe�a[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		for(int i = 0; i<tabuleiro.getLinhas(); i++) {
			for(int y=0; y<tabuleiro.getColuna(); y++){
				mat[i][y] = (XadrexPe�a) tabuleiro.peca(i, y);
			}
		}
		return mat;
	}
	
	private void SetupInicial() {
		tabuleiro.botaPeca(new Torre(tabuleiro, Cor.BRANCO), new Position(2, 1));
		tabuleiro.botaPeca(new Rei(tabuleiro, Cor.PRETO), new Position(2, 1));
	}
	
}
