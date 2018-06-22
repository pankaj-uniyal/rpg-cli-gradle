package com.cli.rpg.character;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import com.cli.rpg.base.BattleAction;
import com.cli.rpg.base.ProbabilityCheck;
import com.cli.rpg.base.Range;
import com.cli.rpg.common.util.Utils;

public final class Vampire extends GameCharacter implements Serializable {
    private final ProbabilityCheck healCheck;
    private final Range healRange;
    private final Set<BattleAction<Vampire>> actions;

    public Vampire(String name, int hitPoints, int attackSpeed, double chanceToHit,
            Range damageRange, String basicAttackDescription, double chanceToHeal,
            Range healRange, Set<BattleAction<Vampire>> actions) {
        super(name, hitPoints, attackSpeed, chanceToHit, damageRange, basicAttackDescription);
        if (healRange == null || actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException("Invalid Vampire properties.");
        }
        this.healCheck = new ProbabilityCheck(chanceToHeal);
        this.healRange = healRange;
        this.actions = actions;
    }

    @Override
    public Optional<Effect> respondToAttack(GameCharacter attacker) {
        if (isAlive()) {
            Optional<HealEffect> effect = attemptToHeal();
            return effect.flatMap(Optional::ofNullable);
        }
        return Optional.empty();
    }

    private Optional<HealEffect> attemptToHeal() {
        if (healCheck.pass()) {
            int healPoints = healRange.getRandom();
            System.out.println(getName() + " healed itself for " + healPoints + " points.");
            Optional<HealEffect> effect = Optional.ofNullable(addHitPoints(healPoints, false));
            displayStatus();
            return effect;
        }
        return Optional.empty();
    }

    public Optional<BattleAction<Vampire>> getRandomAction() {
        return Utils.getRandomElement(actions);
    }
}