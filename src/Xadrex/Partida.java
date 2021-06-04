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
	
	public XadrexPe�a[][] getPecas(){
		XadrexPe�a[][] mat = new XadrexPe�a[tabuleiro.getLinhas()][tabuleiro.getColuna()];
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
		BotaNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		BotaNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
	
	
}
