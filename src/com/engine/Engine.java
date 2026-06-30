package com.engine;

import com.classes.sorcerer.DeathEaterCreator;
import com.classes.sorcerer.WizardCreator;

public class Engine {
	private Team wizards;
	private Team deathEaters;
	
	void generateWizards(WizardCreator wizardCreator, int members) { // TODO Creator
		wizards.generateTeam(wizardCreator, members);
	}
	
	void generateDeathEaters(DeathEaterCreator deathEaterCreator, int members) { // TODO Creator
		wizards.generateTeam(deathEaterCreator, members);
	}
	
	void playTurn() {
		if (this.isBattleFinished()) {
			return;
		}
		
		wizards.onTurnStart();
		deathEaters.onTurnStart();
		
		wizards.attack(deathEaters);
		deathEaters.attack(wizards);
	}
	
	boolean isBattleFinished() {
		if (!wizards.hasHealthySorcerers() || !deathEaters.hasHealthySorcerers()) {
			return true;
		}
		return false;
	}
	
	public String whoWin() {
		if (!this.isBattleFinished()) {
			return "Todavia en batalla";
		}
		return wizards.hasHealthySorcerers() ? "Wizards" : "DeathEaters";
	}
}
