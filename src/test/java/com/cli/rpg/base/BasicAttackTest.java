package com.cli.rpg.base;

import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
import org.junit.Test;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest({})
public class BasicAttackTest {
	
	@Test
	public void shouldReturnStaticName()
		throws Exception {

		String result = BasicAttack.getStaticName();

		assertEquals("Basic Attack", result);
	}
	
}
