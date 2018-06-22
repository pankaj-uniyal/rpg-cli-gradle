package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;



public final class BoostHPSpell<C extends GameCharacter> implements BattleAction<C>, Serializable {
    private static final Range HEAL_RANGE = new Range(25, 50);

    public BoostHPSpell() {
    }

    @Override
    public Collection<Effect> perform(BattleLevel level, C source, Collection<GameCharacter> targets) {
        ActionUtils.validateAction(getTargetType(), source, targets);

        Collection<Effect> effects = new ArrayList<>();
        int amountToAdd = HEAL_RANGE.getRandom();
        effects.add(source.addHitPoints(amountToAdd));
        source.displayStatus();
        return effects;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SELF;
    }

    @Override
    public String toString() {
        return getStaticName();
    }

    public static String getStaticName() {
        return "Boost HP";
    }
}

