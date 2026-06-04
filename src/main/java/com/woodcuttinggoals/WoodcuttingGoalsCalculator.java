package com.woodcuttinggoals;

import net.runelite.api.Experience;

/**
 * Pure, client-free math for the plugin. Kept separate from the plugin so it
 * can be unit-tested without a live game client.
 */
final class WoodcuttingGoalsCalculator
{
	private WoodcuttingGoalsCalculator()
	{
	}

	/**
	 * XP remaining until the next <em>real</em> Woodcutting level, or 0 if the
	 * skill is already at level 99.
	 *
	 * @param currentXp the player's current total Woodcutting XP
	 */
	static int xpToNextLevel(int currentXp)
	{
		int level = Experience.getLevelForXp(currentXp);
		if (level >= Experience.MAX_REAL_LEVEL)
		{
			return 0;
		}
		return Experience.getXpForLevel(level + 1) - currentXp;
	}

	/**
	 * Number of trees needed to gain {@code remainingXp}, rounding up. Returns 0
	 * when nothing is remaining (already at the target) or the input is invalid.
	 *
	 * @param remainingXp XP still needed (to level or to goal)
	 * @param xpPerTree   canonical XP granted per log of the current tree type
	 */
	static int treesUntil(int remainingXp, double xpPerTree)
	{
		if (remainingXp <= 0 || xpPerTree <= 0)
		{
			return 0;
		}
		return (int) Math.ceil(remainingXp / xpPerTree);
	}
}
