package com.cli.rpg.character;

public final class HealEffect implements Effect {
    private final GameCharacter target;
    private final int amount;

    public HealEffect(GameCharacter target, int amount) {
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void undo() {
        target.subtractHitPoints(amount, false);
    }
}
