package com.classes.spell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.classes.effect.ProtectEffect;
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
		
		// Act
		result = voldemort.cast(spell, harry);
		
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
		int damage = (int) Math.round(IncendioSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(harryInitialHP - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void petrificusTotalusCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Petrificus totalus");
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertFalse(harry.canAct(), spell.getName() + " no aplicó el efecto de petrificación.");
		
		// Annihilate
	}
	
	@Test
	void protegoCast() {
		
		// Arrange
		int actualDamage, incomingDamage = 100;
		spell = SpellRepository.getByName("Protego");
		double effectiveness = minerva.getModifier(spell.magicType);
		
		double protectionPercentage = 
    			Math.clamp(ProtectEffect.BASE_PROTECTION_PERCENTAGE * effectiveness, 0d, 1d);
		
		// Act
		result = minerva.cast(spell, harry);
		harry.receiveDamage(incomingDamage);
		actualDamage = (int) Math.round(incomingDamage * (1d - protectionPercentage));
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(actualDamage, harryInitialHP - harry.getHealthPoints(), spell.getName() + " no redujo el daño recibido.");
		
		// Annihilate
	}
	
	@Test
	void sectumsempraCast() {
		
		// Arrange
		spell = SpellRepository.getByName("Sectumsempra");
		double effectiveness = voldemort.getModifier(spell.magicType);
		int damage = (int) Math.round(SectumsempraSpell.BASE_DAMAGE * effectiveness);
		
		// Act
		result = voldemort.cast(spell, harry);
		
		// Assert
		assertTrue(result, spell.getName() + " no se ejecutó correctamente.");
		assertEquals(harryInitialHP - damage, harry.getHealthPoints(), spell.getName() + " no realizó el daño correcto.");
		
		// Annihilate
	}
	
	@Test
	void vulneraSanenturCast() {
		
		// Arrange
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
