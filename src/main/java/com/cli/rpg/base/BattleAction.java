package com.cli.rpg.base;

import java.util.Collection;

import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;

public interface BattleAction<C extends GameCharacter> {
	Collection<Effect> perform(BattleLevel level, C source, Collection<GameCharacter> targets);

	TargetType getTargetType();

	enum TargetType {
		ALL(-1), SELF(0), SINGULAR(1), MULTIPLE(2);

		private int count;

		TargetType(int count) {
			this.count = count;
		}

		public final int getCount() {
			return count;
		}

		void setCount(int count) {
			this.count = count;
		}
	}

	enum Failure {
		FAIL, MISS, EVADE
	}

}