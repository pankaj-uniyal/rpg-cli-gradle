package com.cli.rpg.factories;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import com.cli.rpg.base.BasicAttack;
import com.cli.rpg.base.BattleAction;
import com.cli.rpg.base.BoostHPSpell;
import com.cli.rpg.base.CrushingAttack;
import com.cli.rpg.base.FireBlastSpell;
import com.cli.rpg.base.MultiShotAttack;
import com.cli.rpg.base.Range;
import com.cli.rpg.base.SurpriseAttack;
import com.cli.rpg.character.ActionComparator;
import com.cli.rpg.character.Saviour;

public class SaviourFactory {
	private final Hashtable<SaviourClass, Function<String, Saviour>> domain;
	
	public SaviourFactory(){
		domain = new Hashtable<>();
		populateSaviours();
	}

	
	private void populateSaviours() {
		
		ActionFactory actionFactory = ActionFactory.getInstance();

        Set<BattleAction<Saviour>> warriorActions = new TreeSet<>(new ActionComparator());
        warriorActions.add(actionFactory.getSaviourAttack(BasicAttack.getStaticName()));
        warriorActions.add(actionFactory.getSaviourAttack(CrushingAttack.getStaticName()));

        domain.put(SaviourClass.WARRIOR, (String name) ->
                new Saviour(name, 125, 4, 0.8, new Range(35, 60),
                        "swings a mighty sword at", 0.2, warriorActions));

        Set<BattleAction<Saviour>> sorceressActions = new TreeSet<>(new ActionComparator());
        sorceressActions.add(actionFactory.getSaviourAttack(BasicAttack.getStaticName()));
        sorceressActions.add(actionFactory.getSaviourAttack(BoostHPSpell.getStaticName()));

        domain.put(SaviourClass.SORCERESS, (String name) ->
                new Saviour(name, 75, 5, 0.7, new Range(25, 50),
                        "casts a spell of fireball at", 0.3, sorceressActions));

        Set<BattleAction<Saviour>> thiefActions = new TreeSet<>(new ActionComparator());
        thiefActions.add(actionFactory.getSaviourAttack(BasicAttack.getStaticName()));
        thiefActions.add(actionFactory.getSaviourAttack(SurpriseAttack.getStaticName()));

        domain.put(SaviourClass.THIEF, (String name) ->
                new Saviour(name, 100, 6, 0.8, new Range(20, 40),
                        "thrusts his dagger toward", 0.5, thiefActions));

        Set<BattleAction<Saviour>> mageActions = new TreeSet<>(new ActionComparator());
        mageActions.add(actionFactory.getSaviourAttack(BasicAttack.getStaticName()));
        mageActions.add(actionFactory.getSaviourAttack(FireBlastSpell.getStaticName()));

        domain.put(SaviourClass.MAGE, (String name) ->
                new Saviour(name, 50, 5, 0.6, new Range(5, 20),
                        "swings his staff at", 0.1, mageActions));

        Set<BattleAction<Saviour>> archerActions = new TreeSet<>(new ActionComparator());
        archerActions.add(actionFactory.getSaviourAttack(BasicAttack.getStaticName()));
        archerActions.add(actionFactory.getSaviourAttack(MultiShotAttack.getStaticName()));

        domain.put(SaviourClass.ARCHER, (String name) ->
                new Saviour(name, 100, 6, 0.7, new Range(30, 55),
                        "shoots an arrow at", 0.2, archerActions));
		
	}


	public Saviour createSaviour(SaviourClass saviourClass, String name) {
        if (name == null || name.isEmpty()) {
            return domain.get(saviourClass).apply("Zote the Mighty");
        }
        return domain.get(saviourClass).apply(name);
    }

    public enum SaviourClass {
        WARRIOR, SORCERESS, THIEF, MAGE, ARCHER
    }

}
