package com.classes.spell;

import com.classes.effect.Effect;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.HealEffect;

public class VulneraSanenturSpell extends Spell {

	public final static int 
		BASE_HEAL = 40,
		REQUIRED_LEVEL = 25;

	public VulneraSanenturSpell() {
		super("Vulnera sanentur", MagicType.HEALING, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		double effectiveness = caster.getModifier(this.magicType);
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.heal((int) Math.round(BASE_HEAL * effectiveness));
		target.clearEffects(Effect.EffectPolarity.HARMFUL);
		target.addEffect(new HealEffect(effectiveness));	
		
		return true;
	}
	
}
