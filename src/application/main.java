package application;

import boardgame.Position;
import boardgame.Tabuleiro;
import Xadrex.Partida;

public class main {

	public static void main(String[] args) {
		Partida p1 = new Partida();
		UI.printBoard(p1.getPecas());
		
	}

}
