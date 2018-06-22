package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;



final class ActionUtils implements Serializable {
    enum ActionNoun {
        ATTACK, SPELL
    }
    
    private ActionUtils() {
    }
    
    static Collection<Effect> attemptAttack(GameCharacter source, GameCharacter target) {
        if (source.hitSucceeded()) {
            return damage(source, target, source.rollDamage());
        } else {
            displayFailure(source, target, BattleAction.Failure.MISS, ActionNoun.ATTACK);
            return Collections.emptyList();
        }
    }

    static Collection<Effect> damage(GameCharacter source, GameCharacter target, int damage) {
        Collection<Effect> effects = new ArrayList<>();
        effects.add(target.subtractHitPoints(damage));
        target.displayStatus();
        target.respondToAttack(source).ifPresent(effects::add);
        return effects;
    }

    static void displayFailure(GameCharacter source, GameCharacter target, BattleAction.Failure type, ActionNoun actionNoun) {
        displayFailure(source, target, type, actionNoun.name().toLowerCase());
    }

    static void displayFailure(GameCharacter source, GameCharacter target, BattleAction.Failure type, String actionNoun) {
        switch (type) {
            case FAIL:
                System.out.println(source.getName() + "'s " + actionNoun + " failed!");
                break;
            case MISS:
                System.out.println(source.getName() + "'s " + actionNoun + " missed " + target.getName() + '!');
                break;
            case EVADE:
                System.out.println(target.getName() + " evaded " + source.getName() + "'s " + actionNoun + '!');
                break;
        }
    }

    static void validateAction(BattleAction.TargetType targetType, GameCharacter source, Collection<GameCharacter> targets) {
        validateSource(source);
        if (targetType.getCount() > 0) {
            validateTargets(targets, targetType.getCount());
        }
    }

    private static void validateSource(GameCharacter source) {
        if (source == null || source.isDead()) {
            throw new IllegalArgumentException("Null or dead source!");
        }
    }

    private static void validateTargets(Collection<GameCharacter> targets, int maxTargets) {
        if (targets == null || targets.isEmpty()) {
            throw new IllegalArgumentException("Attempting to act without a target!");
        }
        if (targets.size() > maxTargets) {
            throw new IllegalArgumentException("Attempting to act upon too many targets!");
        }
        targets.forEach(ActionUtils::validateTarget);
    }

    private static void validateTarget(GameCharacter target) {
        if (target == null || target.isDead()) {
            throw new IllegalArgumentException("Null or dead target!");
        }
    }


}
