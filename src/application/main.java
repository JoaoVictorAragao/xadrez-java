package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrex.ExcessaoDXadrez;
import Xadrex.Partida;
import Xadrex.PosicaoXadrez;
import Xadrex.XadrexPeça;
import boardgame.Tabuleiro;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Tabuleiro t1 = new Tabuleiro(8, 8);
		Partida p1 = new Partida();

		while (true) {
			try {
				UI.LimpaTela();
				UI.printBoard(p1.getPecas());
				System.out.println();
				System.out.println("Origem: ");
				PosicaoXadrez origem = UI.LerPosicao(sc);
				
				boolean[][] possibleMoves = p1.possibleMoves(origem);
				UI.LimpaTela();
				UI.printBoard(p1.getPecas(), possibleMoves);
				
				System.out.println();
				System.out.println("Destino: ");
				PosicaoXadrez destino = UI.LerPosicao(sc);

				XadrexPeça refem = p1.Movimento(origem, destino);
			} catch (ExcessaoDXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
