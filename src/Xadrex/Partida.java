package Xadrex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrex.peças.Bispo;
import Xadrex.peças.Peão;
import Xadrex.peças.Rei;
import Xadrex.peças.Torre;
import boardgame.Peça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Partida {

	private int turno;
	private Cor jogador;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMat;
	
	
	private List<Peça> pecasNoTabu = new ArrayList<>();
	private List<Peça> capturedPecas = new ArrayList<>();

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
	
	public XadrexPeça[][] getPecas() {
		XadrexPeça[][] mat = new XadrexPeça[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int y = 0; y < tabuleiro.getColuna(); y++) {
				mat[i][y] = (XadrexPeça) tabuleiro.peca(i, y);
			}
		}
		return mat;
	}

	public boolean[][] possibleMoves(PosicaoXadrez sourcePosition) {
		Position position = sourcePosition.toPosition();
		validarPosicaoOrig(position);

		return tabuleiro.peca(position).possivelMovs();
	}

	public XadrexPeça Movimento(PosicaoXadrez origem, PosicaoXadrez destino) {
		Position origemPos = origem.toPosition();
		Position destinoPos = destino.toPosition();
		validarPosicaoOrig(origemPos);
		validarPosicaoDest(origemPos, destinoPos);
		Peça refem = FazMov(origemPos, destinoPos);

		if (testeCheck(jogador)) {
			VoltaMov(origemPos, destinoPos, refem);
			throw new ExcessaoDXadrez("Você não pode se colocar em check");
		}

		check = (testeCheck(oponente(jogador))) ? true : false;
		
		if(testeCheckMat(oponente(jogador))) {
			checkMat = true;
		}else {
		
			ProxTurno();
		}
		
		return (XadrexPeça) refem;
	}

	private Peça FazMov(Position origem, Position destino) {
		XadrexPeça p =(XadrexPeça)tabuleiro.removePeca(origem);
		p.SobeCount();
		Peça refem = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, destino);

		if (refem != null) {
			pecasNoTabu.remove(refem);
			capturedPecas.add(refem);
		}

		return refem;
	}

	private void VoltaMov(Position origem, Position destino, Peça refem) {
		XadrexPeça p = (XadrexPeça)tabuleiro.removePeca(destino);
		p.DesceCount();
		tabuleiro.botaPeca(p, origem);

		if (refem != null) {
			tabuleiro.botaPeca(refem, destino);
			capturedPecas.remove(refem);
			pecasNoTabu.add(refem);
		}

	}

	private void validarPosicaoOrig(Position position) {
		if (!tabuleiro.TemPeca(position)) {
			throw new ExcessaoDXadrez("Não existe peça nessa posição");
		}
		if (jogador != ((XadrexPeça) tabuleiro.peca(position)).getCor()) {
			throw new ExcessaoDXadrez("Essa peça não é da sua cor");
		}
		if (!tabuleiro.peca(position).TemMov()) {
			throw new ExcessaoDXadrez("Não tem movimento possível para esta peça");
		}
	}

	private void validarPosicaoDest(Position origem, Position destino) {
		if (!tabuleiro.peca(origem).possivelMov(destino)) {
			throw new ExcessaoDXadrez("A peça escolhida não pode ser movida para o destino");
		}
	}

	private void ProxTurno() {
		turno++;
		jogador = (jogador == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private XadrexPeça rei(Cor cor) {
		List<Peça> list = pecasNoTabu.stream().filter(x -> ((XadrexPeça) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peça p : list) {
			if (p instanceof Rei) {
				return (XadrexPeça) p;
			}
		}
		throw new IllegalStateException("Não possuí rei da cor " + cor + "no tabuleiro");
	}

	private boolean testeCheck(Cor cor) {
		Position kingPos = rei(cor).getXadrezPosition().toPosition();
		List<Peça> oponentePecas = pecasNoTabu.stream().filter(x -> ((XadrexPeça) x).getCor() == oponente(cor))
				.collect(Collectors.toList());
		for (Peça p : oponentePecas) {
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
		List<Peça> robson = pecasNoTabu.stream().filter(x -> ((XadrexPeça) x).getCor() == cor).collect(Collectors.toList());
			
		for(Peça p : robson) {
			boolean[][] mat = p.possivelMovs() ;
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColuna(); j++) {
					if(mat[i][j]) {
						Position origem = ((XadrexPeça)p).getXadrezPosition().toPosition();
						Position destino = new Position(i,j);
						Peça refem = FazMov(origem,destino);
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
	
	public void BotaNovaPeca(char coluna, int linha, XadrexPeça peca) {
		tabuleiro.botaPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
		pecasNoTabu.add(peca);
	}

	private void SetupInicial() {
		//PRETO
		BotaNovaPeca('a', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('b', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('c', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('d', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('e', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('f', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('g', 7, new Peão(tabuleiro, Cor.PRETO));
		BotaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		BotaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		BotaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		//BRANCO
		BotaNovaPeca('a', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('b', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('c', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('d', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('e', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('f', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('g', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('h', 2, new Peão(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
	}

}
