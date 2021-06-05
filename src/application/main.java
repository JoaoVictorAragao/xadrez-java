package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrex.ExcessaoDXadrez;
import Xadrex.Partida;
import Xadrex.PosicaoXadrez;
import Xadrex.XadrexPe�a;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Tabuleiro t1 = new Tabuleiro(8, 8);
		Partida p1 = new Partida();
		List<XadrexPe�a> captured = new ArrayList<>();
		
		
		
		while (true) {
			try {
				UI.LimpaTela();
				UI.printPartida(p1, captured);
				System.out.println();
				System.out.println("Origem: ");
				PosicaoXadrez origem = UI.LerPosicao(sc);
				
				boolean[][] possibleMoves = p1.possibleMoves(origem);
				UI.LimpaTela();
				UI.printBoard(p1.getPecas(), possibleMoves);
				
				System.out.println();
				System.out.println("Destino: ");
				PosicaoXadrez destino = UI.LerPosicao(sc);

				XadrexPe�a refem = p1.Movimento(origem, destino);
				
				if(refem != null) {
					captured.add(refem);
				}
				
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
