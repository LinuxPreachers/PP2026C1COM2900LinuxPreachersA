package com.classes.spell;

import com.classes.effect.Effect;
import com.classes.sorcerer.Sorcerer;

public class ExpelliarmusSpell extends Spell {

	private final static int 
		BASE_DAMAGE = 5,
		REQUIRED_LEVEL = 10;
	
	public ExpelliarmusSpell() {
		super("Expelliarmus", MagicType.OFFENSIVE, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.receiveDamage((int) Math.round(BASE_DAMAGE * caster.getModifier(this.magicType)));	
		return true;
	}
}