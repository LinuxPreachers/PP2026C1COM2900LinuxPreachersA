package com.classes.sorcerer;

import java.util.List;
import java.util.Random;

public abstract class SorcererCreator {
    protected static final Random RANDOM = new Random();
    protected static final int MAX_LIFE_POINTS = 100;
    
    
    protected static String randomName(List<String> names) {
        return names.get(RANDOM.nextInt(names.size()));
    }

    protected static int randomLevel(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
    
    public abstract Sorcerer createSorcerer();
}