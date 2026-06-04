package com.woodcuttinggoals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TreeTest
{
	@Test
	public void normalTreeFromPluralMessage()
	{
		assertEquals(Tree.TREE, Tree.fromChatMessage("You get some logs."));
	}

	@Test
	public void willowFromPluralMessage()
	{
		assertEquals(Tree.WILLOW, Tree.fromChatMessage("You get some willow logs."));
	}

	@Test
	public void oakFromSingularMessage()
	{
		assertEquals(Tree.OAK, Tree.fromChatMessage("You get an oak log."));
	}

	@Test
	public void mahoganyFromMessage()
	{
		assertEquals(Tree.MAHOGANY, Tree.fromChatMessage("You get some mahogany logs."));
	}

	@Test
	public void arcticPineTwoWordToken()
	{
		assertEquals(Tree.ARCTIC_PINE, Tree.fromChatMessage("You get some arctic pine logs."));
	}

	@Test
	public void nonWoodcuttingMessageReturnsNull()
	{
		assertNull(Tree.fromChatMessage("You feel a little less agile."));
	}

	@Test
	public void unknownLogReturnsNull()
	{
		assertNull(Tree.fromChatMessage("You get some imaginary logs."));
	}

	@Test
	public void xpValuesAreCanonical()
	{
		assertEquals(25.0,  Tree.TREE.getXp(),        0.0001);
		assertEquals(37.5,  Tree.OAK.getXp(),         0.0001);
		assertEquals(67.5,  Tree.WILLOW.getXp(),      0.0001);
		assertEquals(85.0,  Tree.TEAK.getXp(),        0.0001);
		assertEquals(100.0, Tree.MAPLE.getXp(),       0.0001);
		assertEquals(125.0, Tree.MAHOGANY.getXp(),    0.0001);
		assertEquals(40.0,  Tree.ARCTIC_PINE.getXp(), 0.0001);
		assertEquals(175.0, Tree.YEW.getXp(),         0.0001);
		assertEquals(250.0, Tree.MAGIC.getXp(),       0.0001);
		assertEquals(380.0, Tree.REDWOOD.getXp(),     0.0001);
	}

	@Test
	public void nullMessageReturnsNull()
	{
		assertNull(Tree.fromChatMessage(null));
	}
}
