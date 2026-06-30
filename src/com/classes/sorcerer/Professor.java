package com.classes.sorcerer;
import com.classes.spell.MagicType;

public class Professor extends Wizard {

	public Professor(String name, int magicLevel, int healthPoints) {
		super(name, magicLevel, healthPoints);
		knownMagicTypes.put(MagicType.OFFENSIVE, 1.75);
		knownMagicTypes.put(MagicType.DEFENSIVE, 1.5);
		knownMagicTypes.put(MagicType.HEALING, 1.5);
		knownMagicTypes.put(MagicType.CONTROL, 1.25);
		
		accuracy = 0.9;
		
		learnSpells();
	}

}
