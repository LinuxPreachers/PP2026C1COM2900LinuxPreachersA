package com;

import com.classes.sorcerer.*;
import com.classes.spell.Spell;
import com.classes.spell.SpellRepository;
import com.engine.Engine;

public class Main {

	public static void main(String[] args) {
		
		Wizard harry = new Student("Harry Potter", 15, 100);
		Wizard ron = new Student("Ron Weasley", 12, 100);
		Wizard hermione = new Student("Hermione Granger", 20, 100);
		
		DeathEater minivoldemort = new Commander("Mini Voldemort", 20, 100);
		DeathEater minifollower = new Follower("Rat", 15, 100);
		
		Engine battle = new Engine();
		battle.addWizard(harry);
		battle.addWizard(ron);
		battle.addWizard(hermione);
		battle.addDeathEater(minivoldemort);
		battle.addDeathEater(minifollower);
		
		System.out.println(battle);
		
		battle.activePrintEvents();
		battle.autoBattle();
		
		/*
		Spell spell = SpellRepository.getByName("Expelliarmus");
		
		System.out.println(harry.cast(spell, minivoldemort));
		System.out.println(minivoldemort.getHealthPoints());
		*/
	}

}
