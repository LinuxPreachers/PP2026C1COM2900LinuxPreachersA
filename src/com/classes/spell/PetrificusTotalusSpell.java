package com.classes.spell;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.PetrifyEffect;

public class PetrificusTotalusSpell extends Spell {

	private final static int
		REQUIRED_LEVEL = 5,
		EFFECT_TURNS_DURATION = 3;
	
	private final static double HIT_RATE = 0.8d;
	public final static String NAME = "Petrificus totalus";

	public PetrificusTotalusSpell() {
		super(NAME, MagicType.CONTROL, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.addEffect(new PetrifyEffect(EFFECT_TURNS_DURATION));
		return true;
	}
}
