package Xadrex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrex.pe�as.Rei;
import Xadrex.pe�as.Torre;
import boardgame.Pe�a;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Partida {

	private int turno;
	private Cor jogador;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMat;
	
	
	private List<Pe�a> pecasNoTabu = new ArrayList<>();
	private List<Pe�a> capturedPecas = new ArrayList<>();

	public Partida() {
		tabuleiro = new Tabuleiro(8, 8);
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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMat() {
		return checkMat;
	}
	
	public XadrexPe�a[][] getPecas() {
		XadrexPe�a[][] mat = new XadrexPe�a[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int y = 0; y < tabuleiro.getColuna(); y++) {
				mat[i][y] = (XadrexPe�a) tabuleiro.peca(i, y);
			}
		}
		return mat;
	}

	public boolean[][] possibleMoves(PosicaoXadrez sourcePosition) {
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

		if (testeCheck(jogador)) {
			VoltaMov(origemPos, destinoPos, refem);
			throw new ExcessaoDXadrez("Voc� n�o pode se colocar em check");
		}

		check = (testeCheck(oponente(jogador))) ? true : false;
		
		if(testeCheckMat(oponente(jogador))) {
			checkMat = true;
		}else {
		
			ProxTurno();
		}
		
		return (XadrexPe�a) refem;
	}

	private Pe�a FazMov(Position origem, Position destino) {
		Pe�a p = tabuleiro.removePeca(origem);
		Pe�a refem = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, destino);

		if (refem != null) {
			pecasNoTabu.remove(refem);
			capturedPecas.add(refem);
		}

		return refem;
	}

	private void VoltaMov(Position origem, Position destino, Pe�a refem) {
		Pe�a p = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, origem);

		if (refem != null) {
			tabuleiro.botaPeca(refem, destino);
			capturedPecas.remove(refem);
			pecasNoTabu.add(refem);
		}

	}

	private void validarPosicaoOrig(Position position) {
		if (!tabuleiro.TemPeca(position)) {
			throw new ExcessaoDXadrez("N�o existe pe�a nessa posi��o");
		}
		if (jogador != ((XadrexPe�a) tabuleiro.peca(position)).getCor()) {
			throw new ExcessaoDXadrez("Essa pe�a n�o � da sua cor");
		}
		if (!tabuleiro.peca(position).TemMov()) {
			throw new ExcessaoDXadrez("N�o tem movimento poss�vel para esta pe�a");
		}
	}

	private void validarPosicaoDest(Position origem, Position destino) {
		if (!tabuleiro.peca(origem).possivelMov(destino)) {
			throw new ExcessaoDXadrez("A pe�a escolhida n�o pode ser movida para o destino");
		}
	}

	private void ProxTurno() {
		turno++;
		jogador = (jogador == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private XadrexPe�a rei(Cor cor) {
		List<Pe�a> list = pecasNoTabu.stream().filter(x -> ((XadrexPe�a) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Pe�a p : list) {
			if (p instanceof Rei) {
				return (XadrexPe�a) p;
			}
		}
		throw new IllegalStateException("N�o possu� rei da cor " + cor + "no tabuleiro");
	}

	private boolean testeCheck(Cor cor) {
		Position kingPos = rei(cor).getXadrezPosition().toPosition();
		List<Pe�a> oponentePecas = pecasNoTabu.stream().filter(x -> ((XadrexPe�a) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Pe�a p : oponentePecas) {
			boolean[][] mat = p.possivelMovs();
			if (mat[kingPos.getRow()][kingPos.getColuna()]) {
				return true;
			}

		}
		return false;
	}

	private boolean testeCheckMat(Cor cor) {
		if(!testeCheck(cor)) {
			return false;
		}
		List<Pe�a> robson = pecasNoTabu.stream().filter(x -> ((XadrexPe�a) x).getCor() == cor).collect(Collectors.toList());
			
		for(Pe�a p : robson) {
			boolean[][] mat = p.possivelMovs() ;
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColuna(); j++) {
					if(mat[i][j]) {
						Position origem = ((XadrexPe�a)p).getXadrezPosition().toPosition();
						Position destino = new Position(i,j);
						Pe�a refem = FazMov(origem,destino);
						boolean testeCheck = testeCheck(cor);
						VoltaMov(origem, destino, refem);
						if(!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public void BotaNovaPeca(char coluna, int linha, XadrexPe�a peca) {
		tabuleiro.botaPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
		pecasNoTabu.add(peca);
	}

	private void SetupInicial() {
		BotaNovaPeca('b', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('a', 8, new Rei(tabuleiro, Cor.PRETO));
		
		BotaNovaPeca('h', 7, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('d', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
	}

}
