package com.classes.spell;
import com.classes.sorcerer.Sorcerer;

public class AvadaKedavraSpell extends Spell {
	
	private final static int REQUIRED_LEVEL = 50;
	private final static double HIT_RATE = 0.4d;
	
	public AvadaKedavraSpell() {
		super("Avada Kedavra", MagicType.DARK_ARTS, REQUIRED_LEVEL, HIT_RATE);
	}
	
	@Override
	public boolean apply(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.instantKill();	
		return true;
	}
}
