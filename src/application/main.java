package application;

import java.util.Scanner;

import Xadrex.Partida;
import Xadrex.PosicaoXadrez;
import Xadrex.XadrexPeça;
import boardgame.Tabuleiro;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Tabuleiro t1 = new Tabuleiro(8, 8);
		Partida p1 = new Partida();
		
		while (true) {
			UI.printBoard(p1.getPecas());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez origem = UI.LerPosicao(sc);
			
			System.out.println();
			System.out.println("Destino: ");
			PosicaoXadrez destino = UI.LerPosicao(sc);
			
			XadrexPeça refem = p1.Movimento(origem, destino); 
		}
	}

}
