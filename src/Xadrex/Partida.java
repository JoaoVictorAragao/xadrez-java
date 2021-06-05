package Xadrex;

import Xadrex.peças.Rei;
import Xadrex.peças.Torre;
import boardgame.Peça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Partida {
	
	private Tabuleiro tabuleiro;
	
	public Partida() {
		tabuleiro= new Tabuleiro(8, 8);
		SetupInicial();
	}
	
	public XadrexPeça[][] getPecas(){
		XadrexPeça[][] mat = new XadrexPeça[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		for(int i = 0; i<tabuleiro.getLinhas(); i++) {
			for(int y=0; y<tabuleiro.getColuna(); y++){
				mat[i][y] = (XadrexPeça) tabuleiro.peca(i, y);
			}
		}
		return mat;
	}
	
	public XadrexPeça Movimento(PosicaoXadrez origem, PosicaoXadrez destino) {
		Position origemPos = origem.toPosition();
		Position destinoPos = destino.toPosition();
		validarPosicaoOrig(origemPos);
		Peça refem = FazMov(origemPos, destinoPos);
		return (XadrexPeça)refem;
	}
	
	private Peça FazMov(Position origem, Position destino) {
		Peça p = tabuleiro.removePeca(origem);
		Peça refem = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, destino);
		return refem;
	}
	
	private void validarPosicaoOrig(Position position) {
		if(!tabuleiro.TemPeca(position)) {
			throw new ExcessaoDXadrez("Não existe peça nessa posição");
		}
		if(!tabuleiro.peca(position).TemMov()) {
			throw new ExcessaoDXadrez("Não tem movimento possível para esta peça");
		}
	}
	
	public void BotaNovaPeca(char coluna, int linha, XadrexPeça peca) {
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
