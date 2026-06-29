package com;

import com.classes.sorcerer.*;
import com.classes.spell.Spell;
import com.classes.spell.SpellRepository;

public class Main {

	public static void main(String[] args) {
		
		Sorcerer harry = new Student("Harry Potter", 10, 400);
		Sorcerer voldemort = new Commander("Voldemort", 80, 600);
		
		harry.learnSpells();
		voldemort.learnSpells();
		
		System.out.println(harry.getLearnedSpells());
		
		Spell spell = SpellRepository.getByName("Expelliarmus");
		
		System.out.println(harry.cast(spell, voldemort));
		System.out.println(voldemort.getHealthPoints());
		
	}

}
