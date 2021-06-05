package Xadrex;

import java.util.ArrayList;
import java.util.List;

import Xadrex.pe�as.Rei;
import Xadrex.pe�as.Torre;
import boardgame.Pe�a;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Partida {
	
	private int turno;
	private Cor jogador;
	private Tabuleiro tabuleiro;
	
	private List<Pe�a> pecasNoTabu = new ArrayList<>();
	private List<Pe�a> capturedPecas = new ArrayList<>();
	
	public Partida() {
		tabuleiro= new Tabuleiro(8, 8);
		turno = 1;
		jogador = Cor.BRANCO;
		SetupInicial();
	}
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public Cor getJogador() {
		return jogador;
	}

	public void setJogador(Cor jogador) {
		this.jogador = jogador;
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
	
	public boolean[][] possibleMoves(PosicaoXadrez sourcePosition){
		Position position = sourcePosition.toPosition();
		validarPosicaoOrig(position);
		
		return tabuleiro.peca(position).possivelMovs();
	}
	
	public XadrexPe�a Movimento(PosicaoXadrez origem, PosicaoXadrez destino) {
		Position origemPos = origem.toPosition();
		Position destinoPos = destino.toPosition();
		validarPosicaoOrig(origemPos);
		validarPosicaoDest(origemPos, destinoPos);
		Pe�a refem = FazMov(origemPos, destinoPos);
		ProxTurno();
		return (XadrexPe�a)refem;
	}
	
	private Pe�a FazMov(Position origem, Position destino) {
		Pe�a p = tabuleiro.removePeca(origem);
		Pe�a refem = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, destino);
		
		if(refem != null) {
			pecasNoTabu.remove(refem);
			capturedPecas.add(refem);
		}
		
		return refem;
	}
	
	private void validarPosicaoOrig(Position position) {
		if(!tabuleiro.TemPeca(position)) {
			throw new ExcessaoDXadrez("N�o existe pe�a nessa posi��o");
		}
		if(jogador != ((XadrexPe�a)tabuleiro.peca(position)).getCor()) {
			throw new ExcessaoDXadrez("Essa pe�a n�o � da sua cor");
		}
		if(!tabuleiro.peca(position).TemMov()) {
			throw new ExcessaoDXadrez("N�o tem movimento poss�vel para esta pe�a");
		}
	}
	
	private void validarPosicaoDest(Position origem, Position destino) {
		if(!tabuleiro.peca(origem).possivelMov(destino)) {
			throw new ExcessaoDXadrez("A pe�a escolhida n�o pode ser movida para o destino");
		}
	}
	
	private void ProxTurno() {
		turno++;
		jogador = (jogador == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	public void BotaNovaPeca(char coluna, int linha, XadrexPe�a peca) {
		tabuleiro.botaPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
		pecasNoTabu.add(peca);
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
