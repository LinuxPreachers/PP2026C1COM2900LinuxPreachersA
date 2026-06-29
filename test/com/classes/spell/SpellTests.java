package com.classes.spell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.classes.sorcerer.Commander;
import com.classes.sorcerer.Sorcerer;
import com.classes.sorcerer.Student;

class SpellTests {

	Sorcerer harry, voldemort;
	Spell spell;
	boolean result;
	
	int voldemortInitialHP = 600;
	
	@BeforeEach
	void prepareTests() {
		harry = new Student("Harry Potter", 10, 400);
		voldemort = new Commander("Voldemort", 80, voldemortInitialHP);
		
		harry.learnSpells();
		voldemort.learnSpells();
	}
	
	@Test
	void expelliarmusCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Expelliarmus");
		double effectiveness = harry.getModifier(spell.magicType);
		int damage = (int) Math.round(ExpelliarmusSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = harry.cast(spell, voldemort);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(voldemort.getHealthPoints(), voldemortInitialHP - damage, " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void avadaKedavraCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Avada Kedavra");
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(harry.getHealthPoints(), 0, spell.getName() + " no realizó el daño correcto.");
		
		// Annihilate
	}

}
