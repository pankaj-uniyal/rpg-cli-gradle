package com.cli.rpg.factories;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

import com.cli.rpg.base.BasicAttack;
import com.cli.rpg.base.BattleAction;
import com.cli.rpg.base.Range;
import com.cli.rpg.character.ActionComparator;
import com.cli.rpg.character.Vampire;

public final class VampireFactory {
    private final Hashtable<VampireType, Supplier<Vampire>> domain;

    public VampireFactory() {
        domain = new Hashtable<>();
        populateMonsters();
    }

    private void populateMonsters() {
        ActionFactory actionFactory = ActionFactory.getInstance();
        Set<BattleAction<Vampire>> vampireActions = new TreeSet<>(new ActionComparator());
        vampireActions.add(actionFactory.getVampireAttack(BasicAttack.getStaticName()));

        domain.put(VampireType.GREMLIN, () ->
                new Vampire("Gnarltooth the Gremlin", 70, 5, 0.8,
                        new Range(15, 30), "jabs its kris at",
                        0.4, new Range(20, 40), vampireActions));
        domain.put(VampireType.OGRE, () ->
                new Vampire("Oscar the Ogre", 200, 2, 0.6,
                        new Range(30, 60), "slowly swings a club toward",
                        0.1, new Range(30, 60), vampireActions));
        domain.put(VampireType.SKELETON, () ->
                new Vampire("Skeletor the Skeleton", 100, 3, 0.8,
                        new Range(30, 50), "slices its rusty blade at",
                        0.3, new Range(30, 50), vampireActions));
        domain.put(VampireType.SHADE, () ->
                new Vampire("Sargath the Shade", 125, 4, 1.0,
                        new Range(10, 40), "extends shadowy tendrils toward",
                        0.2, new Range(20, 30), vampireActions));
        domain.put(VampireType.SLIME, () ->
                new Vampire("Glob the Slime", 50, 3, 0.5,
                        new Range(5, 10), "launches itself at",
                        0.9, new Range(10, 20), vampireActions));
    }

    public Vampire createRandomMonster() {
        int choice = (int) (Math.random() * VampireType.values().length);
        return domain.get(VampireType.values()[choice]).get();
    }

    private enum VampireType {
        GREMLIN, OGRE, SKELETON, SHADE, SLIME
    }
}
