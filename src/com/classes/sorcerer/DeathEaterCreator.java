package com.classes.sorcerer;

import java.util.List;

public class DeathEaterCreator extends SorcererCreator{
	private static final List<String> DEATH_EATER_NAMES = List.of(
            "Bellatrix", "Lucius", "Rodolphus", "Antonin", "Fenrir",
            "Draven", "Morven", "Selene", "Cassian", "Valeria"
    );
	
	@Override
	public Sorcerer createSorcerer() {
        switch (RANDOM.nextInt(2)) {
            case 0:
                return new Follower(
                    randomName(DEATH_EATER_NAMES),
                    randomLevel(20, 70),
                    MAX_LIFE_POINTS 
                );

            default:
                return new Commander(
                    randomName(DEATH_EATER_NAMES),
                    randomLevel(70, 99),
                    MAX_LIFE_POINTS 
                );
        }
    }
}
