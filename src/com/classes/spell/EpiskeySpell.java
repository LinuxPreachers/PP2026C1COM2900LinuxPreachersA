package com.classes.spell;

import com.classes.sorcerer.Sorcerer;

public class EpiskeySpell extends Spell {

	public final static int 
		BASE_HEAL = 15,
		REQUIRED_LEVEL = 10;
	
	private final static double HIT_RATE = 0.95d;

	public EpiskeySpell() {
		super("Episkey", MagicType.HEALING, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.heal((int) Math.round(BASE_HEAL * caster.getModifier(this.magicType)));	
		return true;
	}
	
}
