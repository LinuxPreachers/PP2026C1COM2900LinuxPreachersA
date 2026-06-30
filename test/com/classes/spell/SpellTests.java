package com.classes.spell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.classes.sorcerer.Commander;
import com.classes.sorcerer.Professor;
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
	
	@Test
	void bombardaCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Bombarda");
		double effectiveness = voldemort.getModifier(spell.magicType);
		int damage = (int) Math.round(BombardaSpell.BASE_DAMAGE * effectiveness);
		int hpBefore = harry.getHealthPoints();
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBefore - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		assertFalse(harry.attack(), spell.getName() + " no aplicó el efecto de petrificación.");
		
		// Annihilate
	}
	
	@Test
	void episkeyCast() {
		
		// Arrange
		harry.receiveDamage(350);
		int hpBeforeCast = harry.getHealthPoints();
		spell = SpellRepository.getByName("Episkey");
		double effectiveness = harry.getModifier(spell.magicType);
		int healAmount = (int) Math.round(EpiskeySpell.BASE_HEAL * effectiveness);
		
		// Act
		result = harry.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBeforeCast + healAmount, harry.getHealthPoints(), spell.getName() + " no realizó la curación correcta.");
		
		// Annihilate
	}
	
	@Test
	void incendioCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Incendio");
		double effectiveness = voldemort.getModifier(spell.magicType);
		int directDamage = (int) Math.round(IncendioSpell.BASE_DAMAGE * effectiveness);
		int hpBefore = harry.getHealthPoints();
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBefore - directDamage, harry.getHealthPoints(), spell.getName() + " no realizó el daño directo correcto.");
		
		harry.onTurnStart();
		assertTrue(harry.getHealthPoints() < hpBefore - directDamage, spell.getName() + " no aplicó el efecto de quemadura.");
		
		// Annihilate
	}
	
	@Test
	void petrificusTotalusCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Petrificus totalus");
		boolean canAttackBefore = harry.attack();
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertTrue(canAttackBefore, "Harry debería poder atacar antes de ser petrificado.");
		assertFalse(harry.attack(), spell.getName() + " no aplicó el efecto de petrificación.");
		
		// Annihilate
	}
	
	@Test
	void protegoCast() {
		
		// Arrange
		Sorcerer minerva = new Professor("Minerva McGonagall", 80, 300);
		minerva.learnSpells();
		spell = SpellRepository.getByName("Protego");
		int hpBefore = harry.getHealthPoints();
		
		// Act
		result = minerva.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		
		int incomingDamage = 100;
		harry.receiveDamage(incomingDamage);
		int actualDamage = hpBefore - harry.getHealthPoints();
		assertTrue(actualDamage < incomingDamage, spell.getName() + " no redujo el daño recibido.");
		assertTrue(actualDamage > 0, spell.getName() + " redujo el daño a cero completamente.");
		
		// Annihilate
	}
	
	@Test
	void sectumsempraCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Sectumsempra");
		double effectiveness = voldemort.getModifier(spell.magicType);
		int damage = (int) Math.round(SectumsempraSpell.BASE_DAMAGE * effectiveness);
		int hpBefore = harry.getHealthPoints();
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBefore - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void vulneraSanenturCast() {
		
		// Arrange
		Sorcerer minerva = new Professor("Minerva McGonagall", 80, 300);
		minerva.learnSpells();
		harry.receiveDamage(370);
		int hpBeforeCast = harry.getHealthPoints();
		spell = SpellRepository.getByName("Vulnera sanentur");
		double effectiveness = minerva.getModifier(spell.magicType);
		int healAmount = (int) Math.round(VulneraSanenturSpell.BASE_HEAL * effectiveness);
		
		// Act
		result = minerva.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBeforeCast + healAmount, harry.getHealthPoints(), spell.getName() + " no realizó la curación correcta.");
		
		// Annihilate
	}

}
