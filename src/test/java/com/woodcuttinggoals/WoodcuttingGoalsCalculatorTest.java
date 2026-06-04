package com.woodcuttinggoals;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class WoodcuttingGoalsCalculatorTest
{
	@Test
	public void treesUntilRoundsUp()
	{
		// 83 XP to next level, willow = 67.5 XP -> 2 willows (ceil(1.23))
		assertEquals(2, WoodcuttingGoalsCalculator.treesUntil(83, 67.5));
	}

	@Test
	public void treesUntilExactDivisionDoesNotOverCount()
	{
		// 135 / 67.5 == 2.0 exactly -> 2, not 3
		assertEquals(2, WoodcuttingGoalsCalculator.treesUntil(135, 67.5));
	}

	@Test
	public void treesUntilOnePastExactRoundsUp()
	{
		assertEquals(3, WoodcuttingGoalsCalculator.treesUntil(136, 67.5));
	}

	@Test
	public void treesUntilZeroRemainingIsZero()
	{
		assertEquals(0, WoodcuttingGoalsCalculator.treesUntil(0, 25.0));
	}

	@Test
	public void treesUntilNegativeRemainingIsZero()
	{
		assertEquals(0, WoodcuttingGoalsCalculator.treesUntil(-10, 25.0));
	}

	@Test
	public void xpToNextLevelFromZero()
	{
		// Level 1 (0 XP); level 2 needs 83 XP.
		assertEquals(83, WoodcuttingGoalsCalculator.xpToNextLevel(0));
	}

	@Test
	public void xpToNextLevelMidLevel()
	{
		// 100 XP is still level 2 (83); level 3 needs 174 -> 74 remaining.
		assertEquals(74, WoodcuttingGoalsCalculator.xpToNextLevel(100));
	}

	@Test
	public void xpToNextLevelAtMaxIsZero()
	{
		// 13,034,431 XP == level 99; no further real level.
		assertEquals(0, WoodcuttingGoalsCalculator.xpToNextLevel(13_034_431));
	}

	@Test
	public void xpToNextLevelAboveMaxIsZero()
	{
		assertEquals(0, WoodcuttingGoalsCalculator.xpToNextLevel(200_000_000));
	}

	@Test
	public void treesUntilZeroXpPerTreeIsZero()
	{
		assertEquals(0, WoodcuttingGoalsCalculator.treesUntil(100, 0.0));
	}
}
