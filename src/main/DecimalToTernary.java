package main;

public class DecimalToTernary {

	public static String asBase3(int num, int mi) {
		long ret = 0, factor = 1;
		while (num > 0) {
			ret += num % 3 * factor;
			num /= 3;
			factor *= 10;
		}
		String res = String.valueOf(ret);
		while (res.length() < mi) {
			res = "0" + res;
		}
		return res;
	}

}
