package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Position implements Cloneable, Comparable<Position> {
	private int id;
	private List<String> cots;

	public Position(int id, List<String> cots) {
		this.id = id;
		this.cots = cots;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getCot() {
		return cots;
	}

	public void setCot(List<String> cots) {
		this.cots = cots;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", cots=" + cots + "]";
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int compareTo(Position p) {
		return this.getId() - p.getId();
	}

	public static List<Position> loadPositionsFromFile() throws IOException {
		List<Position> list = new ArrayList<>();
		System.out.println("Read from file:");
		File file = new File("/home/hliu/eclipse-workspace/camel-up/src/main/position");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String st;
			while ((st = br.readLine()) != null) {
				System.out.println(st);
				String id = st.split(":")[0];
				String[] cots = st.split(":")[1].split(",");
				list.add(new Position(Integer.valueOf(id), Arrays.asList(cots)));
			}
		}
		return list;
	}
}
