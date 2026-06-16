package com.classes.spell;
import com.classes.character.Character;

public interface Spell {
    String getName();
    int getMagicLevelRequired();
    void execute(Character caster, Character target);
}