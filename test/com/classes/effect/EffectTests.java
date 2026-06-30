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
	
	int harryInitialHP = 400;
	
	@BeforeEach
	void prepareTests() {
		harry = new Student("Harry Potter", 10, harryInitialHP);
		voldemort = new Commander("Voldemort", 80, 600);
	}
	
	@Test
	void effectExpires() {
		
		// Arrange
		int turnsDuration = 5;
		
		// No importa el efecto que sea, aplica igual para todos.
		effect = new PetrifyEffect(turnsDuration); 
		harry.addEffect(effect);
		
		// Act
		for (int i = 0; i < turnsDuration; i++)
			effect.act(harry);
		
		// Assert
		assertTrue(effect.isExpired(), "El efecto no expiró pasada su duración.");
	}
	
	@Test
	void burnEffectDamages() {
		
		// Arrange
		double effectiveness = voldemort.getModifier(MagicType.OFFENSIVE);
		int expectedDamagePerTurn = (int) Math.round(BurnEffect.DAMAGE_PER_TURN * effectiveness);
		int hpBefore = harry.getHealthPoints();
		effect = new BurnEffect(3, effectiveness);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertEquals(
			hpBefore - expectedDamagePerTurn, 
			harry.getHealthPoints(), effect.getName() + " no aplicó el daño correcto por turno."
		);
	}
	
	@Test
	void healEffectHeals() {
		
		// Arrange
		double effectiveness = harry.getModifier(MagicType.HEALING);
		int expectedHealPoints = (int) Math.round(HealEffect.HEALING_PER_TURN * effectiveness);
		int receivedDamage = 50;
				
		harry.receiveDamage(receivedDamage);
		effect = new HealEffect(effectiveness);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertEquals(
			harry.getHealthPoints(), 
			harryInitialHP - receivedDamage + expectedHealPoints, 
			effect.getName() + " no aumenta correctamente los puntos de vida del objetivo."
		);
	}
	
	@Test
	void petrifyEffectBlocksAction() {
		
		// Arrange
		int turnsDuration = 2;
		effect = new PetrifyEffect(turnsDuration);
		boolean canAct = harry.canAct();
		
		harry.addEffect(effect);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertTrue(canAct, "El mago debería poder atacar sin petrificación.");
		assertFalse(harry.canAct(), "El mago no debería poder atacar bajo petrificación.");
	}
	
	@Test
	void protectEffectReducesDamage() {
		
		// Arrange
		int actualDamage, incomingDamage = 100;
		double effectiveness = harry.getModifier(MagicType.DEFENSIVE);
		
		double protectionPercentage = 
	    	Math.clamp(ProtectEffect.BASE_PROTECTION_PERCENTAGE * effectiveness, 0d, 1d);
		
		actualDamage = (int) Math.round(incomingDamage * (1d - protectionPercentage));
		
		effect = new ProtectEffect(effectiveness);
		harry.addEffect(effect);
		
		// Act
		harry.receiveDamage(incomingDamage);
		
		// Assert
		assertEquals(actualDamage, harryInitialHP - harry.getHealthPoints(), effect.getName() + " no redujo el daño recibido.");
	}
	
	@Test
	void protectEffectBlocksHarmfulEffects() {
		
		// Arrange
		double effectiveness = harry.getModifier(MagicType.DEFENSIVE);
		double defaultEffectiveness = 1;
		int turnsDuration = 1;
		boolean result;
		
		effect = new ProtectEffect(effectiveness);
		Effect burnEffect = new BurnEffect(turnsDuration, defaultEffectiveness);
		
		harry.addEffect(effect);
		
		// Act
		result = harry.addEffect(burnEffect);
		
		// Assert
		assertFalse(result, effect.getName() + " debería bloquear los efectos perjudiciales.");
	}
	
	@Test
	void vampireEffectDrainsHP() {
		
		// Arrange
		voldemort.receiveDamage(50);
		int voldemortHPBefore = voldemort.getHealthPoints();
		int drainedHP = (int) Math.round(VampireEffect.DRAIN_PER_TURN * voldemort.getModifier(MagicType.HEALING));
		
		effect = new VampireEffect(voldemort);
		
		// Act
		effect.act(harry);
		
		// Assert
		assertEquals(
			harryInitialHP - drainedHP, 
			harry.getHealthPoints(), 
			"Vampirismo deberia dañar al objetivo."
		);
		
		assertEquals(
			voldemortHPBefore + drainedHP, 
			voldemort.getHealthPoints(), 
			"Vampirismo deberia curar al lanzador."
		);
	}
}
