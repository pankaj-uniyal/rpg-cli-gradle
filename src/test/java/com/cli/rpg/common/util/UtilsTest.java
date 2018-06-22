package com.cli.rpg.common.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>UtilsTest</code> contains tests for the class <code>{@link Utils}</code>.
 *
 * @generatedBy CodePro at 6/21/18 5:55 PM
 * @author me
 * @version $Revision: 1.0 $
 */
public class UtilsTest {
	/**
	 * Run the Optional<Object> getRandomElement(Collection<E>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testGetRandomElement_1()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.getRandomElement(collection);

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.isPresent());
		assertEquals("Optional.empty", result.toString());
	}

	/**
	 * Run the Optional<Object> promptForElement(Collection<E>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testPromptForElement_1()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElement(collection);

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.isPresent());
		assertEquals("Optional.empty", result.toString());
	}

	/**
	 * Run the Optional<Object> promptForElement(Collection<E>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testPromptForElement_2()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElement(collection);

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.isPresent());
		assertEquals("Optional.empty", result.toString());
	}

	/**
	 * Run the Optional<Object> promptForElement(Collection<E>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testPromptForElement_3()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElement(collection);

		// add additional test code here
		assertNotNull(result);
		assertEquals(false, result.isPresent());
		assertEquals("Optional.empty", result.toString());
	}

	/**
	 * Run the Optional<Object> promptForElementWithAlternative(Collection<E>,T) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testPromptForElementWithAlternative_1()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElementWithAlternative(collection, null);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: Alternative option cannot be null!
		//       at com.cli.rpg.common.util.Utils.promptForElementWithAlternative(Utils.java:46)
		assertNotNull(result);
	}

	/**
	 * Run the Optional<Object> promptForElementWithAlternative(Collection<E>,T) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testPromptForElementWithAlternative_2()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElementWithAlternative(collection, null);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: Alternative option cannot be null!
		//       at com.cli.rpg.common.util.Utils.promptForElementWithAlternative(Utils.java:46)
		assertNotNull(result);
	}

	/**
	 * Run the Optional<Object> promptForElementWithAlternative(Collection<E>,T) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testPromptForElementWithAlternative_3()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElementWithAlternative(collection, null);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalArgumentException: Alternative option cannot be null!
		//       at com.cli.rpg.common.util.Utils.promptForElementWithAlternative(Utils.java:46)
		assertNotNull(result);
	}

	/**
	 * Run the Optional<Object> promptForElementWithAlternative(Collection<E>,T) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPromptForElementWithAlternative_4()
		throws Exception {
		Collection<Object> collection = new LinkedList();

		Optional<Object> result = Utils.promptForElementWithAlternative(collection, null);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the boolean readBoolean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testReadBoolean_1()
		throws Exception {

		boolean result = Utils.readBoolean();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean readBoolean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testReadBoolean_2()
		throws Exception {

		boolean result = Utils.readBoolean();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the boolean readBoolean() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
	@Test
	public void testReadBoolean_3()
		throws Exception {

		boolean result = Utils.readBoolean();

		// add additional test code here
		assertEquals(false, result);
	}

	/**
	 * Run the char readChar() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 6/21/18 5:55 PM
	 */
}