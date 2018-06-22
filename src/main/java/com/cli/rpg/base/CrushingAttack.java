package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.cli.rpg.base.BattleLevel;
import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;
import com.cli.rpg.base.ProbabilityCheck;
import com.cli.rpg.base.Range;

public final class CrushingAttack<C extends GameCharacter> implements BattleAction<C>, Serializable {
    private static final ProbabilityCheck ATTACK_HIT_CHECK = new ProbabilityCheck(0.4);
    private static final Range DAMAGE_RANGE = new Range(100, 176);

    public CrushingAttack() {
    }

    @Override
    public Collection<Effect> perform(BattleLevel battleLevel, C source, Collection<GameCharacter> targets) {
        ActionUtils.validateAction(getTargetType(), source, targets);

        Collection<Effect> effects = new ArrayList<>();
        targets.forEach((target) -> {
            if (ATTACK_HIT_CHECK.pass()) {
                System.out.println(source.getName() + " lands a CRUSHING BLOW!");
                effects.addAll(ActionUtils.damage(source, target, DAMAGE_RANGE.getRandom()));
            } else {
                ActionUtils.displayFailure(source, target, BattleAction.Failure.FAIL, "crushing blow");
            }
        });
        return effects;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SINGULAR;
    }

    @Override
    public String toString() {
        return getStaticName();
    }

    public static String getStaticName() {
        return "Crushing Attack";
    }

}

