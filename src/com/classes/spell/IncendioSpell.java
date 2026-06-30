package com.classes.spell;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.BurnEffect;

public class IncendioSpell extends Spell {

	public final static int 
		BASE_DAMAGE = 10,
		REQUIRED_LEVEL = 20,
		TURNS_DURATION = 2;
	
	public IncendioSpell() {
		super("Incendio", MagicType.OFFENSIVE, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		double effectiveness = caster.getModifier(this.magicType);
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.receiveDamage((int) Math.round(BASE_DAMAGE * effectiveness));	
		target.addEffect(new BurnEffect(TURNS_DURATION, effectiveness));
		return true;
	}
}