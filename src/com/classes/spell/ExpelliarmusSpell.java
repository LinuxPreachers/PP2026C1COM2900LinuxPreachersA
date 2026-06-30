package com.classes.spell;

import com.classes.sorcerer.Sorcerer;

public class ExpelliarmusSpell extends Spell {

	public final static int 
		BASE_DAMAGE = 5,
		REQUIRED_LEVEL = 10;
	
	private final static double HIT_RATE = 0.95d;
	
	public ExpelliarmusSpell() {
		super("Expelliarmus", MagicType.OFFENSIVE, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.receiveDamage((int) Math.round(BASE_DAMAGE * caster.getModifier(this.magicType)));	
		return true;
	}
}