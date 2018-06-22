package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;
import com.cli.rpg.character.Saviour;

public final class SurpriseAttack implements BattleAction<Saviour>, Serializable {
    private final static ProbabilityCheck SURPRISE_CHECK = new ProbabilityCheck(0.4);
    private final static ProbabilityCheck FAIL_CHECK = new ProbabilityCheck(0.1);
    private final BasicAttack<Saviour> attack;

    public SurpriseAttack(BasicAttack<Saviour> attack) {
        if (attack == null) {
            throw new IllegalArgumentException("Null attack!");
        }
        this.attack = attack;
    }

    @Override
    public Collection<Effect> perform(BattleLevel level, Saviour source, Collection<GameCharacter> targets) {
        ActionUtils.validateAction(getTargetType(), source, targets);

        Collection<Effect> effects = new ArrayList<>();
        targets.forEach((target) -> {
            switch (calculateEffectiveness()) {
                case SUCCESS:
                    System.out.println(source.getName() + " scores a surprise attack!");
                    level.addTurn();
                case NORMAL:
                    effects.addAll(attack.perform(level, source, targets));
                    break;
                default:
                    System.out.println("Uh oh! " + target.getName() + " saw " + source.getName() + " and blocked their attack!");
                    break;
            }
        });
        return effects;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SINGULAR;
    }

    private static Effectiveness calculateEffectiveness() {
        if (FAIL_CHECK.pass()) {
            return Effectiveness.FAILURE;
        } else if (SURPRISE_CHECK.pass()) {
            return Effectiveness.SUCCESS;
        } else {
            return Effectiveness.NORMAL;
        }
    }

    @Override
    public String toString() {
        return getStaticName();
    }

    public static String getStaticName() {
        return "Surprise Attack";
    }

    private enum Effectiveness {
        SUCCESS, NORMAL, FAILURE
    }
}
