package com.classes.spell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.classes.effect.BurnEffect;
import com.classes.effect.HealEffect;
import com.classes.effect.PetrifyEffect;
import com.classes.effect.ProtectEffect;
import com.classes.effect.VampireEffect;
import com.classes.sorcerer.Commander;
import com.classes.sorcerer.Professor;
import com.classes.sorcerer.Sorcerer;
import com.classes.sorcerer.Student;

class SpellTests {

	Sorcerer harry, voldemort, minerva;
	Spell spell;
	boolean result;
	
	int harryInitialHP = 400;
	int voldemortInitialHP = 600;
	
	@BeforeEach
	void prepareTests() {
		harry = new Student("Harry Potter", 10, harryInitialHP);
		voldemort = new Commander("Voldemort", 80, voldemortInitialHP);
		minerva = new Professor("Minerva McGonagall", 80, 300);
	}
	
	@Test
	void expelliarmusCast() {
		
		// Arrange
		spell = SpellRepository.getByName(ExpelliarmusSpell.NAME);
		double effectiveness = harry.getModifier(spell.magicType);
		int damage = (int) Math.round(ExpelliarmusSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = spell.apply(harry, voldemort);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(voldemort.getHealthPoints(), voldemortInitialHP - damage, " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void avadaKedavraCast() {
		
		// Arrange
		spell = SpellRepository.getByName(AvadaKedavraSpell.NAME);
		
		// Act
		result = spell.apply(voldemort, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(harry.getHealthPoints(), 0, spell.getName() + " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void bombardaCast() {
		
		// Arrange
		spell = SpellRepository.getByName(BombardaSpell.NAME);
		double effectiveness = voldemort.getModifier(spell.magicType);
		int damage = (int) Math.round(BombardaSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = spell.apply(voldemort, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(harryInitialHP - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		assertFalse(harry.canAct(), spell.getName() + " no aplicó el efecto de petrificación.");
		
		// Annihilate
	}
	
	@Test
	void episkeyCast() {
		
		// Arrange
		harry.receiveDamage(350);
		int hpBeforeCast = harry.getHealthPoints();
		spell = SpellRepository.getByName(EpiskeySpell.NAME);
		double effectiveness = harry.getModifier(spell.magicType);
		int healAmount = (int) Math.round(EpiskeySpell.BASE_HEAL * effectiveness);
		
		// Act
		result = spell.apply(harry, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBeforeCast + healAmount, harry.getHealthPoints(), spell.getName() + " no realizó la curación correcta.");
		
		// Annihilate
	}
	
	@Test
	void incendioCast() {
		
		// Arrange
		spell = SpellRepository.getByName(IncendioSpell.NAME);
		double effectiveness = voldemort.getModifier(spell.magicType);
		int damage = (int) Math.round(IncendioSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = spell.apply(voldemort, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no realizó el daño correcto.");
		assertTrue(harry.hasEffect(BurnEffect.NAME), spell.getName() + " no aplicó el efecto correcto.");
		assertEquals(harryInitialHP - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void petrificusTotalusCast() {
		
		// Arrange
		spell = SpellRepository.getByName(PetrificusTotalusSpell.NAME);
		
		// Act
		result = spell.apply(voldemort, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertTrue(harry.hasEffect(PetrifyEffect.NAME), spell.getName() + " no aplicó el efecto correcto.");
		assertFalse(harry.canAct(), spell.getName() + " no aplicó el efecto de petrificación.");
		
		// Annihilate
	}
	
	@Test
	void protegoCast() {
		
		// Arrange
		spell = SpellRepository.getByName(ProtegoSpell.NAME);
		
		// Act
		result = spell.apply(minerva, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertTrue(harry.hasEffect(ProtectEffect.NAME), spell.getName() + " no aplicó el efecto correcto.");
		
		// Annihilate
	}
	
	@Test
	void sectumsempraCast() {
		
		// Arrange
		spell = SpellRepository.getByName(SectumsempraSpell.NAME);
		double effectiveness = voldemort.getModifier(spell.magicType);
		int damage = (int) Math.round(SectumsempraSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = spell.apply(voldemort, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(harryInitialHP - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		assertTrue(harry.hasEffect(VampireEffect.NAME), spell.getName() + " no aplicó el efecto correcto.");
		
		// Annihilate
	}
	
	@Test
	void vulneraSanenturCast() {
		
		// Arrange
		harry.receiveDamage(370);
		int hpBeforeCast = harry.getHealthPoints();
		spell = SpellRepository.getByName(VulneraSanenturSpell.NAME);
		double effectiveness = minerva.getModifier(spell.magicType);
		int healAmount = (int) Math.round(VulneraSanenturSpell.BASE_HEAL * effectiveness);
		
		// Act
		result = spell.apply(minerva, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(hpBeforeCast + healAmount, harry.getHealthPoints(), spell.getName() + " no realizó la curación correcta.");
		assertTrue(harry.hasEffect(HealEffect.NAME), spell.getName() + " no aplicó el efecto correcto.");
		
		// Annihilate
	}

}
