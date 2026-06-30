package com;

import com.classes.sorcerer.*;
import com.engine.Engine;
import com.engine.Engine.AI;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Engine battle = new Engine();
		
		/*
		ArrayList<Wizard> wizards = new ArrayList<>());
		ArrayList<DeathEater> deathEaters = new ArrayList<>());
		
		WizardCreator wc = new WizardCreator();
		DeathEaterCreator dc = new DeathEaterCreator();

		Sorcerer wizard, deathEater;

		for (int i = 0; i < 5; i++) {
			wizard = wc.createSorcerer();
			battle.addWizard(wizard);
			
			deathEater = dc.createSorcerer();
			battle.addDeathEater(deathEater);
		*/

		
		battle.generateWizards(new WizardCreator(), 5);	
		battle.generateDeathEaters(new DeathEaterCreator(), 5);
		
		battle.activePrintEvents();
		battle.autoBattle();
		
		/*
		Wizard harry = new Auror("Harry Potter", 15, 100);
		Wizard ron = new Student("Ron Weasley", 15, 100);
		Wizard hermione = new Student("Hermione Granger", 15, 100);
		
		DeathEater minifollower1 = new Commander("Rat", 18, 100);
		DeathEater minifollower2 = new Follower("Rat1", 15, 100);
		DeathEater minifollower3 = new Follower("Rat2", 15, 100);

		ArrayList<Wizard> wizards = new ArrayList<>(List.of(harry, ron, hermione));
		ArrayList<DeathEater> deathEaters = new ArrayList<>(List.of(minifollower1, minifollower2, minifollower3));
		*/

		/*
		ArrayList<Wizard> wizards = new ArrayList<>();
		ArrayList<DeathEater> deathEaters = new ArrayList<>();
		
		wizards.add(new Auror("Harry Potter", 15, 100));
		wizards.add(new Student("Ron Weasley", 15, 100));
		wizards.add(new Student("Hermione Granger", 15, 100));
		
		deathEaters.add(new Commander("Rat", 18, 100));
		deathEaters.add(new Follower("Rat1", 15, 100));
		deathEaters.add(new Follower("Rat2", 15, 100));
		
		Engine.setAI(AI.RANDOM);
		
		Engine.autoBattleN(wizards, deathEaters, 350);
		*/
		
		/*
		Engine battle = new Engine();

		battle.addWizard(harry);
		battle.addWizard(ron);
		battle.addWizard(hermione);
		battle.addDeathEater(minifollower1);
		battle.addDeathEater(minifollower2);
		battle.addDeathEater(minifollower3);
		
		System.out.println(battle);
		
		battle.activePrintEvents();
		///battle.setAI(AI.RANDOM);
		
		battle.autoBattle();
		*/
		
		/*
		Spell spell = SpellRepository.getByName("Expelliarmus");
		
		System.out.println(harry.cast(spell, minivoldemort));
		System.out.println(minivoldemort.getHealthPoints());
		*/
	}

}
