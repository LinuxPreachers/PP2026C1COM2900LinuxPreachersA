package com.engine;

import com.classes.sorcerer.DeathEater;
import com.classes.sorcerer.DeathEaterCreator;
import com.classes.sorcerer.Sorcerer;
import com.classes.sorcerer.Wizard;
import com.classes.sorcerer.WizardCreator;

import java.util.ArrayList;
import java.util.List;

public class Engine {
	public enum AI {
		RANDOM,
		STRATEGIC
	}
	
	private Team wizards = new Team();
	private Team deathEaters = new Team();
	private boolean printEvents = false;
	private static AI actualAI = AI.STRATEGIC;
	
	void generateWizards(WizardCreator wizardCreator, int members) {
		wizards.generateTeam(wizardCreator, members);
	}
	
	public void addWizard(Sorcerer w) {
		wizards.addSorcerer(w);
	}
	
	void generateDeathEaters(DeathEaterCreator deathEaterCreator, int members) {
		wizards.generateTeam(deathEaterCreator, members);
	}
	
	public void addDeathEater(Sorcerer de) {
		deathEaters.addSorcerer(de);
	}
	
	public static void setAI(AI newAI) {
		Engine.actualAI = newAI;
	}
	
	void playTurn() {
		if (this.isBattleFinished()) {
			return;
		}
		
		wizards.onTurnStart();
		deathEaters.onTurnStart();
		
		if (actualAI == AI.RANDOM) {
			wizards.attackRandom(deathEaters);
			deathEaters.attackRandom(wizards);
		} else {
			wizards.attackStrategic(deathEaters);
			deathEaters.attackStrategic(wizards);
		}
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
		
		if (printEvents) {			
			System.out.println("Winners: " + this.whoWin());
		}
		
	}
	
	static public void autoBattleN(List<Wizard> wizards, List<DeathEater> deathEaters, int n) {
		int w = 0, d = 0;
		
		for (int i=0; i<n; i++) {
			Engine e = new Engine();
			
			for (Wizard wiz : wizards) {
				wiz.resetForBattle();
				e.addWizard(wiz);
			}
			
			for (DeathEater dea : deathEaters) {
				dea.resetForBattle();
				e.addDeathEater(dea);
			}
			
			e.autoBattle();
			if (e.whoWin().equals("Wizards")) {
				w++;
			} else {
				d++;
			}
		}
		
		System.out.println("AUTOBATTLED N=" + n + " W:" + w + " D:" + d);
		
		if (w!=d) {
			System.out.println("Los " + (w > d ? "magos" : "mortifagos") + "han ganado mas batallas");
		} else {
			System.out.println("Los bandos han empatado!");
		}
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
