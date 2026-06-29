package com.classes.spell;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.PetrifyEffect;

public class PetrificusTotalusSpell extends Spell {

	private final static int
		REQUIRED_LEVEL = 5,
		EFFECT_TURNS_DURATION = 3;

	public PetrificusTotalusSpell() {
		super("Petrificus totalus", MagicType.CONTROL, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.addEffect(new PetrifyEffect(EFFECT_TURNS_DURATION));
		return true;
	}
}
