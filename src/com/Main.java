package com;

import com.classes.sorcerer.*;
import com.engine.Engine;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Main.battleCase1();
	}
	
	public static void superRandomCase() {
		Engine battle = new Engine();
		
		battle.generateWizards(new WizardCreator(), 5);	
		battle.generateDeathEaters(new DeathEaterCreator(), 5);
		
		battle.activePrintEvents();
		battle.autoBattle();
	}
	
	public static void superRandomCase2() {
		
		Engine battle = new Engine();
		
		WizardCreator wc = new WizardCreator();
		DeathEaterCreator dc = new DeathEaterCreator();

		Sorcerer wizard, deathEater;

		for (int i = 0; i < 5; i++) {
			wizard = wc.createSorcerer();
			battle.addWizard(wizard);
			
			deathEater = dc.createSorcerer();
			battle.addDeathEater(deathEater);
		}
		
		battle.activePrintEvents();
		battle.autoBattle();
		
	}

	public static void battleCase1() {
		
		Engine battle = new Engine();
		
		Wizard harry = new Auror("Harry Potter", 15, 100);
		Wizard ron = new Student("Ron Weasley", 15, 100);
		Wizard hermione = new Student("Hermione Granger", 15, 100);
		
		DeathEater minifollower1 = new Commander("Rat", 18, 100);
		DeathEater minifollower2 = new Follower("Rat1", 15, 100);
		DeathEater minifollower3 = new Follower("Rat2", 15, 100);

		ArrayList<Wizard> wizards = new ArrayList<>(List.of(harry, ron, hermione));
		ArrayList<DeathEater> deathEaters = new ArrayList<>(List.of(minifollower1, minifollower2, minifollower3));

		battle.activePrintEvents();
		Engine.autoBattleN(wizards, deathEaters, 1000);
		
	}
}
