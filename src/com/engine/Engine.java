package com.engine;

import com.classes.sorcerer.WizardCreator;
import com.classes.sorcerer.DeathEaterCreator;

public class Engine {
	private Team wizards;
	private Team deathEaters;
	boolean wizardsTurn = true;
	
	void generateWizards(WizardCreator wizardCreator, int members) {
		wizards.generateTeam(wizardCreator, members);
	}
	
	void generateDeathEaters(DeathEaterCreator deathEaterCreator, int members) {
		wizards.generateTeam(deathEaterCreator, members);
	}
	
	void playTurn() {
		if (this.isBattleFinished()) {
			return;
		}
		
		if (wizardsTurn) {
			wizards.attack(deathEaters);
			wizardsTurn = false;
		} else {
			deathEaters.attack(wizards);
			wizardsTurn = true;
		}
	}
	
	boolean isBattleFinished() {
		if (!wizards.hasHealthySorcerers() || !deathEaters.hasHealthySorcerers()) {
			return true;
		}
		return false;
	}
}
