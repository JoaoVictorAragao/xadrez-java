package Xadrex;

import Xadrex.pe�as.Rei;
import Xadrex.pe�as.Torre;
import boardgame.Pe�a;
import boardgame.Tabuleiro;

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
	
	public void BotaNovaPeca(char coluna, int linha, XadrexPe�a peca) {
		tabuleiro.botaPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
	}
	
	private void SetupInicial() {
		BotaNovaPeca('b', 6, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('d', 5, new Rei(tabuleiro, Cor.PRETO));
	}
	
	
}
