package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Xadrex.Cor;
import Xadrex.Partida;
import Xadrex.PosicaoXadrez;
import Xadrex.XadrexPeça;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void LimpaTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosicaoXadrez LerPosicao(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao instanciar tabuleiro, valores devem ir de a1 a h8");
		}
	}

	public static void printPartida(Partida partida, List<XadrexPeça> captured) {
		printBoard(partida.getPecas());
		System.out.println();
		printPecasComidas(captured);
		System.out.println();
		System.out.println("Turno: " + partida.getTurno());
		if (!partida.getCheckMat()) {
			System.out.println("Esperando a jogada de: " + partida.getJogador());
			if (partida.getCheck()) {
				System.out.println("CHECK!");
			}
		}else {
			System.out.println("CHECKMATE!!!");
			System.out.println("Vencedor: "+partida.getJogador());
		}
	}

	public static void printBoard(XadrexPeça[][] peca) {
		for (int i = 0; i < peca.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < peca.length; j++) {
				printPeca(peca[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printBoard(XadrexPeça[][] peca, boolean[][] possibleMoves) {
		for (int i = 0; i < peca.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < peca.length; j++) {
				printPeca(peca[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPeca(XadrexPeça peca, boolean fundo) {
		if (fundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca == null) {
				System.out.print("-");
			} else {
				if (peca.getCor() == Cor.BRANCO) {
					System.out.print(ANSI_WHITE + peca + ANSI_RESET);
				} else {
					System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
				}
			}
		}
		System.out.print(" ");
	}

	private static void printPecasComidas(List<XadrexPeça> captured) {
		List<XadrexPeça> BRANCA = captured.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<XadrexPeça> PRETO = captured.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		System.out.println("Peças comidas: ");
		System.out.println("Brancas: ");
		System.out.println(ANSI_WHITE);
		System.out.println(Arrays.toString(BRANCA.toArray()));
		System.out.println(ANSI_RESET);

		System.out.println("Pretas: ");
		System.out.println(ANSI_YELLOW);
		System.out.println(Arrays.toString(PRETO.toArray()));
		System.out.println(ANSI_RESET);

	}

}
