package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;

public final class FireBlastSpell<C extends GameCharacter> implements BattleAction<C>, Serializable {
    private static final ProbabilityCheck SPELL_SUCCESS = new ProbabilityCheck(0.5);
    private static final ProbabilityCheck ATTACK_HIT_CHECK = new ProbabilityCheck(0.7);
    private static final Range DAMAGE_RANGE = new Range(40, 100);

    public FireBlastSpell() {
    }

    @Override
    public Collection<Effect> perform(BattleLevel level, C source, Collection<GameCharacter> targets) {
        ActionUtils.validateAction(getTargetType(), source, targets);

        Collection<Effect> effects = new ArrayList<>();
        System.out.println(source.getName() + " attempts to cast a massive fiery blast!");
        if (SPELL_SUCCESS.pass()) {
            targets.forEach((target) -> {
                if (ATTACK_HIT_CHECK.pass()) {
                    effects.addAll(ActionUtils.damage(source, target, DAMAGE_RANGE.getRandom()));
                } else {
                    ActionUtils.displayFailure(source, target, Failure.MISS, ActionUtils.ActionNoun.SPELL);
                }
            });
        } else {
            ActionUtils.displayFailure(source, null, Failure.FAIL, ActionUtils.ActionNoun.SPELL);
        }
        return effects;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.ALL;
    }

    @Override
    public String toString() {
        return getStaticName();
    }

    public static String getStaticName() {
        return "Fire Blast";
    }
}
