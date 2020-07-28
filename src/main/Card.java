package main;

public class Card implements Comparable<Card> {
	private String name;
	private double points;
	

	public Card(String name, double points) {
		this.name = name;
		this.points = points;
	}


	public double getPoints() {
		return points;
	}



	public void setPoints(double points) {
		this.points = points;
	}



	public String getName() {
		return name;
	}

	

	@Override
	public String toString() {
		return "Card [name=" + name + ", points=" + points + "]";
	}


	@Override
	public int compareTo(Card c) {
		if((c.points - this.points) > 0) {
			return 1;
		} else if ((c.points - this.points) < 0) {
			return -1;
		}
		return 0;
	}

}
