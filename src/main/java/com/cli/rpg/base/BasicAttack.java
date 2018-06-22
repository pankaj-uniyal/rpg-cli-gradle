package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;

public final class BasicAttack<C extends GameCharacter> implements BattleAction<C>, Serializable {

	@Override
	public Collection<Effect> perform(BattleLevel level, C source, Collection<GameCharacter> targets) {
		ActionUtils.validateAction(getTargetType(), source, targets);
		Collection<Effect> effects = new ArrayList<>();
        targets.forEach((target) -> {
            System.out.println(source.getName() + ' ' + source.getBasicAttackDescription() + ' ' + target.getName() + '.');
            effects.addAll(ActionUtils.attemptAttack(source, target));
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
        return "Basic Attack";
    }

}
