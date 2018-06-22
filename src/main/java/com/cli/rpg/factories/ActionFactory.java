package com.cli.rpg.factories;

import java.util.Hashtable;

import com.cli.rpg.base.BasicAttack;
import com.cli.rpg.base.BattleAction;
import com.cli.rpg.base.BoostHPSpell;
import com.cli.rpg.base.CrushingAttack;
import com.cli.rpg.base.FireBlastSpell;
import com.cli.rpg.base.MultiShotAttack;
import com.cli.rpg.base.SurpriseAttack;
import com.cli.rpg.character.Saviour;
import com.cli.rpg.character.Vampire;



public class ActionFactory {
    private static ActionFactory instance;
    private final Hashtable<String, BattleAction<Saviour>> saviourActions;
    private final Hashtable<String, BattleAction<Vampire>> vampireActions;

    private ActionFactory() {
    	saviourActions = new Hashtable<>();
        BasicAttack<Saviour> basic = new BasicAttack<>();
        saviourActions.put(BasicAttack.getStaticName(), basic);
        saviourActions.put(CrushingAttack.getStaticName(), new CrushingAttack<>());
        saviourActions.put(SurpriseAttack.getStaticName(), new SurpriseAttack(basic));
        saviourActions.put(BoostHPSpell.getStaticName(), new BoostHPSpell<>());
        saviourActions.put(FireBlastSpell.getStaticName(), new FireBlastSpell<>());
        saviourActions.put(MultiShotAttack.getStaticName(), new MultiShotAttack<>());

        vampireActions = new Hashtable<>();
        vampireActions.put(BasicAttack.getStaticName(), new BasicAttack<>());
    }

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionFactory();
        }

        return instance;
    }

    public BattleAction<Saviour> getSaviourAttack(String type) {
        return saviourActions.get(type);
    }

    public BattleAction<Vampire> getVampireAttack(String type) {
        return vampireActions.get(type);
    }
}