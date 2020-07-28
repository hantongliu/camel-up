package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Calculation {

	// moves all rounds, move one round
	public static List<List<String>> raceAll(List<Position> positions, List<String[]> moves)
			throws CloneNotSupportedException {
		List<List<String>> list = new ArrayList<>();
		for (String[] move : moves) {
			list.add(race(new Board(clonePosition(positions)), move));
		}
		return list;
	}

	private static List<Position> clonePosition(List<Position> positions) throws CloneNotSupportedException {
		List<Position> list = new ArrayList<>();
		for (Position p : positions) {
			Position p2 = (Position) p.clone();
			list.add(p2);
		}
		return list;
	}

	// move = [orange:3, yellow:3, green:3, blue:3, white:3]
	private static List<String> race(Board board, String[] move) {
		for (String m : move) {
			String color = m.split(":")[0];
			int num = Integer.valueOf(m.split(":")[1]);
			board.update(color, num);
			if (board.isFinished())
				break;
		}
		return board.rank();
	}

	public static void suggestCard(List<List<String>> results) {

		// 0:orange, 1:yellow, 2:green, 3:blue, 4:white
		int[] first = new int[6];
		int[] second = new int[6];
		for (List<String> list : results) {
			first[findColor(list.get(0))]++;
			second[findColor(list.get(1))]++;
		}
		System.out.println(Arrays.toString(first));
		System.out.println(Arrays.toString(second));
		
		List<Card> cards = new ArrayList<>();
		for(int i=0; i<5; i++) {
			cards.add(new Card(findColor(i)+"5", calPoints(i, first, second, results.size(), 5)));
			cards.add(new Card(findColor(i)+"3", calPoints(i, first, second, results.size(), 3)));
			cards.add(new Card(findColor(i)+"2", calPoints(i, first, second, results.size(), 2)));
		}
		Collections.sort(cards);
		for(Card c:cards) {
			System.out.println(c.toString());
		}
	}
	
	private static double calPoints(int i, int[] first, int[] second, int total, int point) {
		return (double)(point * first[i] + second[i] - (total - first[i] - second[i])) / total;
	}

	private static int findColor(String string) {
		if(string.equals("orange"))
			return 0;
		if(string.equals("yellow"))
			return 1;
		if(string.equals("green"))
			return 2;
		if(string.equals("blue"))
			return 3;
		if(string.equals("white"))
			return 4;
		return 5;
			
	}
	
	private static String findColor(int num) {
		if(num==0) {
			return "orange";
		}
		if(num==1) {
			return "yellow";
		}
		if(num==2) {
			return "green";
		}
		if(num==3) {
			return "blue";
		}
		if(num==4) {
			return "white";
		}
		return null;
	}
}
