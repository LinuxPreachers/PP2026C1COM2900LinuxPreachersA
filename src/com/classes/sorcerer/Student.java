package com.classes.sorcerer;

public class Student extends Wizard{

	public Student(String name, int magicLevel, int healthPoints) {
		super(name, magicLevel, healthPoints);
        knownMagicTypes.put(MagicType.DEFENSIVE, 0.8);
        knownMagicTypes.put(MagicType.HEALING, 0.9);
	}

}
