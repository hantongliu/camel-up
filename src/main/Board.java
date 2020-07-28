package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	
	private List<Position> positions;
	private boolean finished;

	
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	
	public boolean isFinished() {
		return finished;
	}

	public Board(List<Position> positions) {
		this.positions = positions;
	}

	// certain color move certain steps
	public void update(String color, int num) {
		for (Position p : this.positions) {
			if (p.getCot().contains(color)) {
				move(p, color, num);
				break;
			}
		}
	}

	private void move(Position p, String color, int num) {
		List<String> colors = p.getCot();
		int id = p.getId();
		int new_id = id + num;
		if(colors.get(colors.size() - 1).equals(color)) {
			moveAll(p, colors, new_id);
		} else {
			int index = colors.indexOf(color);
			p.setCot(colors.subList(index + 1, colors.size()));
			if(checkMatch(new_id)) {
				addToPositon(colors.subList(0, index+1), new_id);
			} else {
				positions.add(new Position(new_id, colors.subList(0, index+1)));
			}
			
		}
		if (new_id > 16)
			this.finished = true;

	}

	private void moveAll(Position p, List<String> colors, int new_id) {
		if(checkMatch(new_id)) {
			addToPositon(colors, new_id);
			positions.remove(p);
		} else {
			p.setId(new_id);
		}
		
	}

	private boolean checkMatch(int new_id) {
		return positions.stream().anyMatch(po -> po.getId() == new_id);
	}

	private void addToPositon(List<String> colors, int id) {
		Position p = positions.stream().filter(po -> po.getId() == id).findFirst().get();
		List<String> list = new ArrayList<>();
		list.addAll(colors);
		list.addAll(p.getCot());
		p.setCot(list);
	}

	public List<String> rank() {
		List<String> list = new ArrayList<>();
		Collections.sort(this.positions, Collections.reverseOrder());
		for(Position p:positions) {
			list.addAll(p.getCot());
		}
		return list;
	}

}
