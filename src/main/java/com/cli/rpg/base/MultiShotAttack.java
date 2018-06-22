package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;



public final class MultiShotAttack<C extends GameCharacter> implements BattleAction<C>, Serializable {
    public MultiShotAttack() {
    }

    @Override
    public Collection<Effect> perform(BattleLevel level, C source, Collection<GameCharacter> targets) {
        ActionUtils.validateAction(getTargetType(), source, targets);

        Collection<Effect> effects = new ArrayList<>();
        System.out.println(source.getName() + " launches a volley of arrows!");
        targets.forEach((target) -> {
            if (target.isAlive()) {
                effects.addAll(ActionUtils.attemptAttack(source, target));
            }
        });
        return effects;
    }

    @Override
    public TargetType getTargetType() {
        TargetType t = TargetType.MULTIPLE;
        t.setCount(2);
        return t;
    }

    @Override
    public String toString() {
        return getStaticName();
    }

    public static String getStaticName() {
        return "Multi-Shot";
    }
}

