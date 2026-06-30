package com.classes.sorcerer;
import com.classes.spell.MagicType;

public class Follower extends DeathEater {

	public Follower(String name, int magicLevel, int healthPoints) {
		super(name, magicLevel, healthPoints);
        knownMagicTypes.put(MagicType.OFFENSIVE, 1.5);
        knownMagicTypes.put(MagicType.DARK_ARTS, 1.3);
        
        learnSpells();
    }

}
