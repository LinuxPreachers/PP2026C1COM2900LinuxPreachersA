package com.classes.spell;

import com.classes.effect.BurnEffect;
import com.classes.effect.PetrifyEffect;
import com.classes.sorcerer.Sorcerer;

public class BombardaSpell extends Spell {

	public final static int 
		BASE_DAMAGE = 40,
		REQUIRED_LEVEL = 30,
		EFFECT_TURNS_DURATION = 1;
	
	public final static double 
		BURN_PROBABILITY = 0.2d,
		HIT_RATE = 0.5d;
	
	public final static String NAME = "Bombarda";
		

	public BombardaSpell() {
		super(NAME, MagicType.OFFENSIVE, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
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
