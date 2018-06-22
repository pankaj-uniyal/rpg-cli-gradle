package com.cli.rpg.character;

public final class DamageEffect implements Effect {
    private final GameCharacter target;
    private final int damage;

    public DamageEffect(GameCharacter target, int damage) {
        this.target = target;
        this.damage = damage;
    }

    @Override
    public void undo() {
        target.addHitPoints(damage, false);
    }
}