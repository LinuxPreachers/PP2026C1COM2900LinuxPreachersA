package com.classes.effect;
import com.classes.character.Character;

public interface Effect {
    void onTurnStart(Character target);
    int filterReceivedDamage(int damagePoints);
    boolean canAttack();
    boolean isExpired();
    String getName();
}
