package main;

import java.util.List;


public class Main {

	public static void main(String[] args) throws Exception {
		String[] colors = new String[] {"orange", "yellow", "green", "blue", "white" };
//		String[] colors = new String[] {"white", "yellow", "blue", "green" };
//		String[] colors = new String[] {"blue", "green", "white" };
//		String[] colors = new String[] {"orange", "blue"};
//		String[] colors = new String[] {"green"};

		// 120 for 5 colors
		List<String[]> orders = Permutation.defineOrder(colors);
//		Permutation.print(orders);
		
		// 29160 for 5 colors
		List<String[]> moves = Permutation.addMoves(orders, colors.length);
//		Permutation.print(moves);

		List<Position> positions = Position.loadPositionsFromFile();

		List<List<String>> results = Calculation.raceAll(positions, moves);
		
		Calculation.suggestCard(results);
				
	}

}
