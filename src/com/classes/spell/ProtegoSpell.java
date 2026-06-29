package com.classes.spell;

import com.classes.effect.ProtectEffect;
import com.classes.sorcerer.Sorcerer;

public class ProtegoSpell extends Spell {
	
	private final static int REQUIRED_LEVEL = 20;
	
	public ProtegoSpell() {
		super("Protego", MagicType.DEFENSIVE, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		double effectiveness = caster.getModifier(this.magicType); 
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.addEffect(new ProtectEffect(effectiveness));
		return true;
	}
	
}
