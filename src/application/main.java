package application;

import boardgame.Position;
import boardgame.Tabuleiro;
import Xadrex.Partida;

public class main {

	public static void main(String[] args) {
		Tabuleiro t1 = new Tabuleiro(8, 8);
		Partida p1 = new Partida();
		UI.printBoard(p1.getPecas());
		
	}

}
