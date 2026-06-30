package com.engine;

import com.classes.sorcerer.DeathEater;
import com.classes.sorcerer.DeathEaterCreator;
import com.classes.sorcerer.Wizard;
import com.classes.sorcerer.WizardCreator;

public class Engine {
	private Team wizards = new Team();
	private Team deathEaters = new Team();
	private boolean printEvents = false;

	
	void generateWizards(WizardCreator wizardCreator, int members) {
		wizards.generateTeam(wizardCreator, members);
	}
	
	public void addWizard(Wizard w) {
		wizards.addSorcerer(w);
	}
	
	void generateDeathEaters(DeathEaterCreator deathEaterCreator, int members) {
		wizards.generateTeam(deathEaterCreator, members);
	}
	
	public void addDeathEater(DeathEater de) {
		deathEaters.addSorcerer(de);
	}
	
	void playTurn() {
		if (this.isBattleFinished()) {
			return;
		}
		
		wizards.onTurnStart();
		deathEaters.onTurnStart();
		
		wizards.attackAI(deathEaters);
		deathEaters.attackAI(wizards);
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
	
	public void activePrintEvents() {
		printEvents=true;
		wizards.activePrintEvents();
		deathEaters.activePrintEvents();
	}
	
	public void deactivePrintEvents() {
		printEvents=false;
		wizards.deactivePrintEvents();
		deathEaters.deactivePrintEvents();
	}
	
	public void autoBattle() {
		
		int turn = 0;
		
		while (!this.isBattleFinished()) {

			turn++;

			this.playTurn();
			
			if (printEvents) {
				System.out.print("\n========== TURN " + turn + " ==========");
				System.out.print(this.getOutputEvents());
				System.out.println("\n----Lifes----");
				System.out.print(this);
			}
		}
		
		System.out.println("Winners: " + this.whoWin());
		
	}
	
	public String toString() {
		String rv = "Battle";
		rv += wizards;
		rv += "\n-";
		rv += deathEaters;
		rv += "\n-\n";
		return rv;
	}
	
	public String getOutputEvents() {
		String wiz, dee;
		wiz = wizards.consumeEvents();
		dee = deathEaters.consumeEvents();
		return wiz + dee;
	}
}
