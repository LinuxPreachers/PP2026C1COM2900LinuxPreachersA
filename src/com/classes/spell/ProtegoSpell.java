package com.classes.spell;

import com.classes.effect.ProtectEffect;
import com.classes.sorcerer.Sorcerer;

public class ProtegoSpell extends Spell {
	
	public final static int REQUIRED_LEVEL = 20;
	public final static double HIT_RATE = 0.75d;
	public final static String NAME = "Protego";
	
	public ProtegoSpell() {
		super(NAME, MagicType.DEFENSIVE, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
		double effectiveness = caster.getModifier(this.magicType); 
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.addEffect(new ProtectEffect(effectiveness));
		return true;
	}
	
}
