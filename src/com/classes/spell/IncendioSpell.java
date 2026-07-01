package com.classes.spell;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.BurnEffect;

public class IncendioSpell extends Spell {

	public final static int 
		BASE_DAMAGE = 10,
		REQUIRED_LEVEL = 20,
		TURNS_DURATION = 2;
	
	public final static double HIT_RATE = 0.7d;
	public final static String NAME = "Incendio";
	
	public IncendioSpell() {
		super(NAME, MagicType.OFFENSIVE, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
		double effectiveness = caster.getModifier(this.magicType);
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.receiveDamage((int) Math.round(BASE_DAMAGE * effectiveness));	
		target.addEffect(new BurnEffect(TURNS_DURATION, effectiveness));
		return true;
	}
}