package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrex.ExcessaoDXadrez;
import Xadrex.Partida;
import Xadrex.PosicaoXadrez;
import Xadrex.XadrexPeça;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Tabuleiro t1 = new Tabuleiro(8, 8);
		Partida p1 = new Partida();
		List<XadrexPeça> captured = new ArrayList<>();

		while (!p1.getCheckMat()) {
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

				XadrexPeça refem = p1.Movimento(origem, destino);

				if (refem != null) {
					captured.add(refem);
				}

				if (p1.getPromoted() != null) {
					System.out.print("Entre com uma classe para promoção (B/C/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while(!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
						System.out.print("Classe invpalida! Entre com uma classe para promoção (B/C/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					p1.replacePromotedPiece(type);
				}

			} catch (ExcessaoDXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.LimpaTela();
		UI.printPartida(p1, captured);
	}
}
