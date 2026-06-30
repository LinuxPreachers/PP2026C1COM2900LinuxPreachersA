package com;

import java.util.ArrayList;
import java.util.List;

import com.classes.sorcerer.*;
import com.classes.spell.Spell;
import com.classes.spell.SpellRepository;
import com.engine.Engine;
import com.engine.Engine.AI;

public class Main {

	public static void main(String[] args) {
		
		Wizard harry = new Auror("Harry Potter", 15, 100);
		Wizard ron = new Student("Ron Weasley", 15, 100);
		Wizard hermione = new Student("Hermione Granger", 15, 100);
		
		DeathEater minifollower1 = new Follower("Rat", 18, 100);
		DeathEater minifollower2 = new Follower("Rat1", 15, 100);
		DeathEater minifollower3 = new Follower("Rat2", 15, 100);
		DeathEater minifollower4 = new Follower("Rat3", 15, 100);
		/*
		ArrayList<Wizard> wizards = new ArrayList<>(List.of(harry, ron, hermione));
		ArrayList<DeathEater> deathEaters = new ArrayList<>(List.of(minifollower1, minifollower2, minifollower3, minifollower4));
		
		Engine.autoBattleN(wizards, deathEaters, 1000);
		*/
		
		Engine battle = new Engine();

		battle.addWizard(harry);
		battle.addWizard(ron);
		battle.addWizard(hermione);
		battle.addDeathEater(minifollower1);
		battle.addDeathEater(minifollower2);
		battle.addDeathEater(minifollower3);
		battle.addDeathEater(minifollower4);
		
		System.out.println(battle);
		
		battle.activePrintEvents();
		///battle.setAI(AI.RANDOM);
		
		battle.autoBattle();
		
		/*
		Spell spell = SpellRepository.getByName("Expelliarmus");
		
		System.out.println(harry.cast(spell, minivoldemort));
		System.out.println(minivoldemort.getHealthPoints());
		*/
	}

}
