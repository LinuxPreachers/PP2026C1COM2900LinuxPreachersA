package com.classes.sorcerer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.classes.spell.AvadaKedavraSpell;
import com.classes.spell.ExpelliarmusSpell;
import com.classes.spell.Spell;
import com.classes.spell.SpellRepository;

class SorcererTests {

	Sorcerer harry, voldemort;
	Spell spell;
	boolean result;
	
	int voldemortInitialHP = 600;
	int harryInitialHP = 400;
	
	int harryLevel = 10;
	
	@BeforeEach
	void prepareTests() {
		harry = new Student("Harry Potter", harryLevel, harryInitialHP);
		voldemort = new Commander("Voldemort", 80, voldemortInitialHP);
	}
	
	@Test
	void sorcererInitialState() {
		
		// Assert
		assertEquals("Harry Potter", harry.getName(), "El nombre del mago no es correcto.");
		assertEquals(harryLevel, harry.getLevel(), "El nivel del mago no es correcto.");
		assertEquals(harryInitialHP, harry.getHealthPoints(), "Los puntos de vida iniciales no son correctos.");
		assertFalse(harry.getLearnedSpells().isEmpty(), "El mago debería tener hechizos aprendidos al inicio.");
	}
	
	@Test
	void learnSpellsKnowledge() {
		
		// Assert
		assertTrue(harry.getLearnedSpells().size() > 0, harry.getName() + " debería haber aprendido al menos un hechizo.");
		assertTrue(
			voldemort.getLearnedSpells().size() > harry.getLearnedSpells().size(), 
			voldemort.getName() + " (nivel 80) debería conocer más hechizos que " + harry.getName() + " (nivel 10)."
		);
		
		spell = SpellRepository.getByName(ExpelliarmusSpell.NAME);
		assertTrue(harry.getLearnedSpells().contains(spell), "Harry debería conocer Expelliarmus (nivel 10, ofensivo).");
		
		spell = SpellRepository.getByName(AvadaKedavraSpell.NAME);
		assertFalse(harry.getLearnedSpells().contains(spell), "Harry no debería conocer Avada Kedavra (no conoce artes oscuras).");
		assertTrue(voldemort.getLearnedSpells().contains(spell), "Voldemort debería conocer Avada Kedavra (nivel 80, artes oscuras).");
	}
	
	@Test
	void receiveDamageReducesHP() {
		
		// Arrange
		int damage = 50;
		
		// Act
		harry.receiveDamage(damage);
		
		// Assert
		assertEquals(harryInitialHP - damage, harry.getHealthPoints(), "El daño no se aplicó correctamente.");
	}
	
	@Test
	void receiveLethalDamage() {
		
		// Arrange
		int damage = harryInitialHP;
		boolean result2;
		
		// Act
		result = harry.receiveDamage(damage);
		result2 = harry.receiveDamage(damage);
		
		// Assert
		assertEquals(0, harry.getHealthPoints(), "El daño letal debería dejar al mago con 0 puntos de vida.");
		assertTrue(result, "El metodo debería retornar true si el mago esta vivo al recibir daño.");
		assertFalse(result2, "El metodo debería retornar false si el mago esta muerto al recibir daño.");
	}
	
	@Test
	void healIncreasesHP() {
		
		// Arrange
		int damage = 350;
		harry.receiveDamage(damage);
		int hpAfterDamage = harry.getHealthPoints();
		int healAmount = 20;
		
		// Act
		harry.heal(healAmount);
		
		// Assert
		assertEquals(hpAfterDamage + healAmount, harry.getHealthPoints(), "La curacion no aplicó la cantidad correcta.");
	}
	
	@Test
	void castUnlearnedSpell() {
		
		// Arrange
		harry.learnSpells();
		spell = SpellRepository.getByName("Avada Kedavra");
		
		// Act
		result = harry.cast(spell, voldemort);
		
		// Assert
		assertFalse(result, harry.getName() + " no debería poder lanzar un hechizo que no ha aprendido.");
	}
	
	@Test
	void instantKillKills() {
		
		// Act
		harry.instantKill();
		
		// Assert
		assertEquals(0, harry.getHealthPoints(), "instantKill debería establecer los puntos de vida a cero.");
	}
}
