package com.classes.spell;
import com.classes.sorcerer.Sorcerer;

public interface Spell {
    String getName();
    int getMagicLevelRequired();
    void execute(Sorcerer caster, Sorcerer target);
}