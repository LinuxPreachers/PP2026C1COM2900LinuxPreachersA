package com.classes.sorcerer;
import com.classes.spell.MagicType;

public class Auror extends Wizard{

	public Auror(String name, int magicLevel, int healthPoints) {
		super(name, magicLevel, healthPoints);
        knownMagicTypes.put(MagicType.OFFENSIVE, 1.3);
        knownMagicTypes.put(MagicType.DEFENSIVE, 1.25);
        knownMagicTypes.put(MagicType.CONTROL, 1.25);
        
        accuracy = 0.8;
        
        learnSpells();
	}

}
