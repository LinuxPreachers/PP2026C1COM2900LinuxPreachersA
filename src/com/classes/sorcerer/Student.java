package com.classes.sorcerer;
import com.classes.spell.MagicType;

public class Student extends Wizard {

	public Student(String name, int magicLevel, int healthPoints) {
		super(name, magicLevel, healthPoints);
		knownMagicTypes.put(MagicType.OFFENSIVE, 0.7);
        knownMagicTypes.put(MagicType.DEFENSIVE, 0.8);
        knownMagicTypes.put(MagicType.HEALING, 0.9);
        
        learnSpells();
	}

}
