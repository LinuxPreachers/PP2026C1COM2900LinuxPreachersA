package com.classes.effect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.classes.sorcerer.Commander;
import com.classes.sorcerer.Sorcerer;
import com.classes.sorcerer.Student;
import com.classes.spell.MagicType;

class EffectTests {

	Sorcerer harry, voldemort;
	Effect effect;
	
	int voldemortInitialHP = 600;
	int harryInitialHP = 400;
	
	@BeforeEach
	void prepareTests() {
		harry = new Student("Harry Potter", 10, harryInitialHP);
		voldemort = new Commander("Voldemort", 80, voldemortInitialHP);
	}
	
	@Test
	void burnEffectDamage() {
		
		// Arrange
		double effectiveness = voldemort.getModifier(MagicType.OFFENSIVE);
		int expectedDamagePerTurn = (int) Math.round(5 * effectiveness);
		int hpBefore = harry.getHealthPoints();
		effect = new BurnEffect(3, effectiveness);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertEquals(hpBefore - expectedDamagePerTurn, harry.getHealthPoints(), 
			"Quemadura no aplicó el daño correcto por turno.");
		assertFalse(effect.isExpired(), "Quemadura no debería expirar tras un solo turno (duracion 3).");
	}
	
	@Test
	void healEffectHealing() {
		
		// Arrange
		double effectiveness = harry.getModifier(MagicType.HEALING);
		int initialHP = harry.getHealthPoints();
		int receivedDamage = 50;
		harry.receiveDamage(receivedDamage);
		effect = new HealEffect(effectiveness);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertTrue(harry.getHealthPoints() > initialHP - receivedDamage, 
			"Curacion debería aumentar los puntos de vida del objetivo.");
	}
	
	@Test
	void petrifyEffectBlocksAttack() {
		
		// Arrange
		effect = new PetrifyEffect(2);
		harry.learnSpells();
		boolean canAttackBefore = harry.attack();
		
		// Act
		harry.addEffect(effect);
		boolean canAttackAfter = harry.attack();
		
		// Assert
		assertTrue(canAttackBefore, "El mago debería poder atacar sin efectos.");
		assertFalse(canAttackAfter, "El mago no debería poder atacar bajo petrificacion.");
	}
	
	@Test
	void protectEffectReducesDamage() {
		
		// Arrange
		double effectiveness = harry.getModifier(MagicType.DEFENSIVE);
		effect = new ProtectEffect(effectiveness);
		int incomingDamage = 100;
		
		// Act
		int filteredDamage = effect.filterReceivedDamage(incomingDamage);
		
		// Assert
		assertTrue(filteredDamage < incomingDamage, 
			"Proteccion debería reducir el danio recibido.");
		assertTrue(filteredDamage > 0, 
			"Proteccion no debería reducir el danio a cero completamente.");
	}
	
	@Test
	void vampireEffectDrainsLife() {
		
		// Arrange
		voldemort.receiveDamage(50);
		int voldemortHPBefore = voldemort.getHealthPoints();
		effect = new VampireEffect(voldemort);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertTrue(harry.getHealthPoints() < harryInitialHP, 
			"Vampirismo deberia daniar al objetivo.");
		assertTrue(voldemort.getHealthPoints() > voldemortHPBefore, 
			"Vampirismo deberia curar al lanzador.");
	}
}
