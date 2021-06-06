package Xadrex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Xadrex.peças.Bispo;
import Xadrex.peças.Cavalo;
import Xadrex.peças.Peão;
import Xadrex.peças.Rainha;
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
	private XadrexPeça enPassant;
	private XadrexPeça promoted;

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

	public XadrexPeça getPromoted() {
		return promoted;
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

	public XadrexPeça getEnPassant() {
		return enPassant;
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

		XadrexPeça movedPiece = (XadrexPeça) tabuleiro.peca(destinoPos);

		// #specialmove promotion
		promoted = null;
		if (movedPiece instanceof Peão) {
			if ((movedPiece.getCor() == Cor.BRANCO && destinoPos.getRow() == 0)
					|| (movedPiece.getCor() == Cor.PRETO && destinoPos.getRow() == 7)) {
				promoted = (XadrexPeça) tabuleiro.peca(destinoPos);
				promoted = replacePromotedPiece("Q");
			}
		}

		check = (testeCheck(oponente(jogador))) ? true : false;

		if (testeCheckMat(oponente(jogador))) {
			checkMat = true;
		} else {

			ProxTurno();
		}

		// Movimento especial
		if (movedPiece instanceof Peão
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassant = movedPiece;
		} else {
			enPassant = null;
		}

		return (XadrexPeça) refem;
	}

	public XadrexPeça replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("Não tem peça para ser promovid");
		}
		if (!type.equals("B") && !type.equals("C") && !type.equals("R") & !type.equals("Q")) {
			return promoted;
		}

		Position pos = promoted.getXadrezPosition().toPosition();
		Peça p = tabuleiro.removePeca(pos);
		pecasNoTabu.remove(p);

		XadrexPeça newPiece = newPiece(type, promoted.getCor());
		tabuleiro.botaPeca(newPiece, pos);
		pecasNoTabu.add(newPiece);

		return newPiece;
	}

	private XadrexPeça newPiece(String type, Cor cor) {
		if (type.equals("B"))
			return new Bispo(tabuleiro, cor);
		if (type.equals("C"))
			return new Cavalo(tabuleiro, cor);
		if (type.equals("Q"))
			return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}

	private Peça FazMov(Position origem, Position destino) {
		XadrexPeça p = (XadrexPeça) tabuleiro.removePeca(origem);
		p.SobeCount();
		Peça refem = tabuleiro.removePeca(destino);
		tabuleiro.botaPeca(p, destino);

		if (refem != null) {
			pecasNoTabu.remove(refem);
			capturedPecas.add(refem);
		}

		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Position sourceT = new Position(origem.getRow(), origem.getColuna() + 3);
			Position targetT = new Position(origem.getRow(), origem.getColuna() + 1);
			XadrexPeça rook = (XadrexPeça) tabuleiro.removePeca(sourceT);
			tabuleiro.botaPeca(rook, targetT);
			rook.SobeCount();
		}

		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Position sourceT = new Position(origem.getRow(), origem.getColuna() - 4);
			Position targetT = new Position(origem.getRow(), origem.getColuna() - 1);
			XadrexPeça rook = (XadrexPeça) tabuleiro.removePeca(sourceT);
			tabuleiro.botaPeca(rook, targetT);
			rook.SobeCount();
		}

		// #specialmove en passant
		if (p instanceof Peão) {
			if (origem.getColuna() != destino.getColuna() && refem == null) {
				Position pawnPosition;
				if (p.getCor() == Cor.BRANCO) {
					pawnPosition = new Position(destino.getRow() + 1, destino.getColuna());
				} else {
					pawnPosition = new Position(destino.getRow() - 1, destino.getColuna());
				}
				refem = tabuleiro.removePeca(pawnPosition);
				capturedPecas.add(refem);
				pecasNoTabu.remove(refem);
			}
		}

		return refem;
	}

	private void VoltaMov(Position origem, Position destino, Peça refem) {
		XadrexPeça p = (XadrexPeça) tabuleiro.removePeca(destino);
		p.DesceCount();
		tabuleiro.botaPeca(p, origem);

		if (refem != null) {
			tabuleiro.botaPeca(refem, destino);
			capturedPecas.remove(refem);
			pecasNoTabu.add(refem);
		}

		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Position sourceT = new Position(origem.getRow(), origem.getColuna() + 3);
			Position targetT = new Position(origem.getRow(), origem.getColuna() + 1);
			XadrexPeça rook = (XadrexPeça) tabuleiro.removePeca(targetT);
			tabuleiro.botaPeca(rook, sourceT);
			rook.DesceCount();
		}

		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Position sourceT = new Position(origem.getRow(), origem.getColuna() - 4);
			Position targetT = new Position(origem.getRow(), origem.getColuna() - 1);
			XadrexPeça rook = (XadrexPeça) tabuleiro.removePeca(targetT);
			tabuleiro.botaPeca(rook, sourceT);
			rook.DesceCount();
		}

		// #specialmove en passant
		if (p instanceof Peão) {
			if (origem.getColuna() != destino.getColuna() && refem == enPassant) {
				XadrexPeça pawn = (XadrexPeça) tabuleiro.removePeca(destino);
				Position pawnPosition;
				if (p.getCor() == Cor.BRANCO) {
					pawnPosition = new Position(3, destino.getColuna());
				} else {
					pawnPosition = new Position(4, destino.getColuna());
				}
				tabuleiro.botaPeca(pawn, pawnPosition);
			}
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
		if (!testeCheck(cor)) {
			return false;
		}
		List<Peça> robson = pecasNoTabu.stream().filter(x -> ((XadrexPeça) x).getCor() == cor)
				.collect(Collectors.toList());

		for (Peça p : robson) {
			boolean[][] mat = p.possivelMovs();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColuna(); j++) {
					if (mat[i][j]) {
						Position origem = ((XadrexPeça) p).getXadrezPosition().toPosition();
						Position destino = new Position(i, j);
						Peça refem = FazMov(origem, destino);
						boolean testeCheck = testeCheck(cor);
						VoltaMov(origem, destino, refem);
						if (!testeCheck) {
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
		// PRETO
		BotaNovaPeca('a', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('b', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('c', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('d', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('e', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('f', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('g', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('h', 7, new Peão(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		BotaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		BotaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		BotaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		BotaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		BotaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		BotaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		// BRANCO
		BotaNovaPeca('a', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('b', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('c', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('d', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('e', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('f', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('g', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('h', 2, new Peão(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		BotaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		BotaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
	}

}
