//Author: Sushi
//A simple program for generating list of targets for Spoon Assassin

import java.util.ArrayList;

public class Spoon<E> {

	ArrayList<E> player = new ArrayList<E>();
	ArrayList<E> targetList = new ArrayList<E>();
	private E newTarget = null;

	//randomly assign targets
	public ArrayList<E> randomAssignment(ArrayList<E> player) {
		for (int i = 0; i < player.size(); i++) {
			generateNewTarget();
			//check if the new target generated is duplicated or itself
			if (!checkDuplicateTarget(i, newTarget) && notSelf(i, newTarget)) {
				targetList.add(newTarget);
			}
			//if it is duplicated or itself, set it as null for now
			else {
				targetList.add(null);
			}
			//handle duplicate targets and self targets
			while (checkDuplicateTarget(i, newTarget) || !notSelf(i, newTarget)) {
				generateNewTarget();
				targetList.set(i, newTarget);
			}
		}
		return targetList;
	}
	
	//return the target list generated as formatted string
	public String assignment(ArrayList<E> player) {
		ArrayList<E> list = randomAssignment(player);
		String result = "";
		for (int i = 0; i < player.size(); i++) {
			result = result + "Player: " + player.get(i) + "  Target: " + list.get(i) + "\n";
		}
		return result;
	}
	
	//randomly generate a new player in the range of player's list
	private void generateNewTarget() {
		newTarget = player.get((int)(player.size() * Math.random()));
	}

	//return true if there is duplicates of target 
	//false if target is all clear to assign
	private boolean checkDuplicateTarget(int current, E currentTarget) {
		boolean duplicated = false;
		for (int i = 0; i < current; i++) {
			if (currentTarget == targetList.get(i)) {
				duplicated = true;
			}
		}
		return duplicated;
	}
	
	//make sure target is not itself
	//return true if currentTarget is not player themself
	private boolean notSelf(int current, E currentTarget) {
		return currentTarget != player.get(current);
	}
	

	//Test algorithm in main
	public static void main(String [] args) {
		Spoon<String> assassins = new Spoon<String>();

		assassins.player.add("A");
		assassins.player.add("B");
		assassins.player.add("C");
		assassins.player.add("D");
		assassins.player.add("E");
		assassins.player.add("F");
		assassins.player.add("G");
		assassins.player.add("H");
		assassins.player.add("I");
		assassins.player.add("J");

		assassins.targetList = assassins.randomAssignment(assassins.player);

		for (int i = 0; i < assassins.targetList.size(); i++) {
			System.out.println("Player: " + assassins.player.get(i) + "  Target: " + assassins.targetList.get(i));
		}
	}
}
