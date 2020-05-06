package de.hwrberlin.autovermietung.humans;

import java.util.ArrayList;
import java.util.List;

public class HumanManager {

	private List<Human> humans;
	
	public HumanManager() {
		this.humans = new ArrayList<Human>();
	}
	
	public List<Human> getHumans() {
		return this.humans;
	}
	
	public void addHuman(Human human) {
		this.humans.add(human);
	}
	
	public void removeHuman(Human human) {
		this.humans.remove(human);
	}
}
