package com.classes.sorcerer;

public class Commander extends DeathEater{
	public Commander(String name, int magicLevel, int healthPoints) {
		super(name, magicLevel, healthPoints);
		knownMagicTypes.put(MagicType.OFFENSIVE, 1.75);
		knownMagicTypes.put(MagicType.CONTROL, 1.75);
		knownMagicTypes.put(MagicType.DARK_ARTS, 1.75);
	}
}
