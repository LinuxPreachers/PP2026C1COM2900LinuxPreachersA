package com.classes.spell;

import com.classes.effect.VampireEffect;
import com.classes.sorcerer.Sorcerer;

public class SectumsempraSpell extends Spell {
	
	private final static int 
		BASE_DAMAGE = 25,
		REQUIRED_LEVEL = 30;
	
	public SectumsempraSpell() {
		super("Sectumsempra", MagicType.OFFENSIVE, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.receiveDamage((int) Math.round(BASE_DAMAGE * caster.getModifier(this.magicType)));	
		target.addEffect(new VampireEffect(caster));
		
		return true;
	}
		
}
