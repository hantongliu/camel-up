package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {
	/**
	 * 
	 * @param colors Get an array of colors
	 * @return a list of in different permute orders
	 */
	public static List<String[]> defineOrder(String[] colors) {
		if (colors == null || colors.length == 0) {
			return null;
		}
		List<String[]> list = new ArrayList<>();
		permute(Arrays.asList(colors), 0, list);
		return list;
	}

	static void permute(List<String> arr, int k, List<String[]> list) {
		for (int i = k; i < arr.size(); i++) {
			Collections.swap(arr, i, k);
			permute(arr, k + 1, list);
			Collections.swap(arr, k, i);
		}
		if (k == arr.size() - 1) {
			list.add((String[]) arr.toArray());
		}
	}

	/**
	 * 
	 * @param orders permutation orders, 120 for 5 colors
	 * @param num    number of colors, 1 to 5
	 * @return list of permutations + moves 120 * 3^5 for 5 colors
	 */
	public static List<String[]> addMoves(List<String[]> orders, int num) {
		List<String[]> list = new ArrayList<>();
		if (orders == null || orders.size() == 0) {
			return null;
		}
		List<String> mi = getMi(num);
		for (String[] order : orders) {
			for (String n : mi) {
				String[] res = new String[order.length];
				for (int i = 0; i < order.length; i++) {
					res[i] = order[i] + ":" + n.charAt(i);
				}
				list.add(res);
			}
		}

		return list;
	}

	private static List<String> getMi(int num) {
		List<String> list = new ArrayList<>();
		if (num <= 0) {
			System.out.println("color numer at least 1.");
			return list;
		}
		int mi = (int) Math.pow(3, num);
		for (int i = 0; i < mi; i++) {
			String s = DecimalToTernary.asBase3(i, num);
			StringBuilder sb = new StringBuilder();
			for (char c : s.toCharArray()) {
				sb.append(Integer.valueOf(String.valueOf(c)) + 1);
			}
			list.add(sb.toString());
		}
		return list;
	}

	public static void print(List<String[]> list) {
		for (String[] ss : list) {
			System.out.println(Arrays.toString(ss));
		}
	}
}
