package com.classes.spell;

import com.classes.effect.BurnEffect;
import com.classes.effect.PetrifyEffect;
import com.classes.sorcerer.Sorcerer;

public class BombardaSpell extends Spell {

	private final static int 
		BASE_DAMAGE = 40,
		REQUIRED_LEVEL = 30,
		EFFECT_TURNS_DURATION = 1;
	
	private final static double BURN_PROBABILITY = 0.2d;
		

	public BombardaSpell() {
		super("Bombarda", MagicType.OFFENSIVE, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		double effectiveness = caster.getModifier(this.magicType);
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.receiveDamage((int) Math.round(BASE_DAMAGE * effectiveness));	
		target.addEffect(new PetrifyEffect(EFFECT_TURNS_DURATION));
		
		if (Double.compare(Math.random(), BURN_PROBABILITY) <= 0) {
			caster.addEffect(new BurnEffect(EFFECT_TURNS_DURATION, effectiveness));
		}
		
		return true;
	}
	
}
