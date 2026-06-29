package com.classes.spell;
import com.classes.sorcerer.Sorcerer;

public class AvadaKedavraSpell extends Spell {
	
	private final static int REQUIRED_LEVEL = 50;
	
	public AvadaKedavraSpell() {
		super("Avada Kedavra", MagicType.DARK_ARTS, REQUIRED_LEVEL);
	}
	
	@Override
	public boolean cast(Sorcerer caster, Sorcerer target) {
		
		if (target.getHealthPoints() <= 0)
			return false;
		
		target.instantKill();	
		return true;
	}
}
