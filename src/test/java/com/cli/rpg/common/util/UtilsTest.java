package com.cli.rpg.common.util;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({})
public class UtilsTest {
	
	private final String testString = "WARRIOR";
	@Test
    public void shouldReturnCamelCaseString() throws Exception{
		Utils utils = new Utils();
		String result = utils.toCamelCase(testString);
		
		assertEquals("Warrior",result);
	}
	
	@Test
	public void shouldReturnRandomElement()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.getRandomElement(collection);

		assertNotNull(result);
		assertEquals(false, result.isPresent());
		assertEquals("Optional.empty", result.toString());
	}
	
	@Test
	public void shouldReadBoolean()
		throws Exception {

		boolean result = Utils.readBoolean();

		assertEquals(false, result);
	}
	
	@Test
	public void shouldPromptForElement()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElement(collection);

		assertNotNull(result);
		assertEquals(false, result.isPresent());
		assertEquals("Optional.empty", result.toString());
	}
}
