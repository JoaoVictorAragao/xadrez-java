package Xadrex;

import Xadrex.pe�as.Rei;
import Xadrex.pe�as.Torre;
import boardgame.Pe�a;
import boardgame.Position;
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
	
	public XadrexPe�a Movimento(PosicaoXadrez origem, PosicaoXadrez destino) {
		Position origemPos = origem.toPosition();
		Position destinoPos = destino.toPosition();
		validarPosicaoOrig(origemPos);
		Pe�a refem = FazMov(origemPos, destinoPos);
		return (XadrexPe�a)refem;
	}
	
	private Pe�a FazMov(Position origem, Position destino) {
		Pe�a p = tabuleiro.removePeca(origem);
		Pe�a refem = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, destino);
		return refem;
	}
	
	private void validarPosicaoOrig(Position position) {
		if(!tabuleiro.TemPeca(position)) {
			throw new ExcessaoDXadrez("N�o existe pe�a nessa posi��o");
		}
		if(!tabuleiro.peca(position).TemMov()) {
			throw new ExcessaoDXadrez("N�o tem movimento poss�vel para esta pe�a");
		}
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
