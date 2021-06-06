package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.XadrexPeça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Peão extends XadrexPeça{

	public Peão(Tabuleiro board, Cor cor) {
		super(board, cor);
		
	}

	@Override
	public boolean[][] possivelMovs() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

		Position p = new Position(0, 0);
		if(getCor() == Cor.BRANCO) {
			p.setValores(position.getRow() - 1, position.getColuna());
			if(getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
			p.setValores(position.getRow() - 2, position.getColuna());
			Position p2 = new Position(position.getRow() - 1, position.getColuna());
			if(getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p) && getBoard().PosicaoExiste(p2) && !getBoard().TemPeca(p2) && getCount() == 0) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
			p.setValores(position.getRow() - 1, position.getColuna() - 1);
			if(getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
			p.setValores(position.getRow() - 1, position.getColuna() + 1);
			if(getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
		}else {
			p.setValores(position.getRow() + 1, position.getColuna());
			if(getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
			p.setValores(position.getRow() + 2, position.getColuna());
			Position p2 = new Position(position.getRow() + 1, position.getColuna());
			if(getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p) && getBoard().PosicaoExiste(p2) && !getBoard().TemPeca(p2) && getCount() == 0) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
			p.setValores(position.getRow() + 1, position.getColuna() + 1);
			if(getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
			
			p.setValores(position.getRow() + 1, position.getColuna() - 1);
			if(getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
		}
		
		return mat;
	}
	@Override
	public String toString() {
		return "p";
	}
	
	
	
}
