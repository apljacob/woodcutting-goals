package com.woodcuttinggoals;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Units;

@ConfigGroup("woodcuttinggoals")
public interface WoodcuttingGoalsConfig extends Config
{
	@ConfigItem(
		keyName = "showOverlay",
		name = "Show overlay",
		description = "Show the woodcutting goals overlay while chopping",
		position = 0
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showXpToLevel",
		name = "Show XP to level",
		description = "Show the raw XP remaining until the next level",
		position = 1
	)
	default boolean showXpToLevel()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showTreesToGoal",
		name = "Show trees to goal",
		description = "Show trees remaining until your XP Tracker goal (set a goal via the XP orb)",
		position = 2
	)
	default boolean showTreesToGoal()
	{
		return true;
	}

	@Units(Units.MINUTES)
	@ConfigItem(
		keyName = "overlayTimeout",
		name = "Overlay timeout",
		description = "Hide the overlay after this many minutes of not chopping",
		position = 3
	)
	default int overlayTimeout()
	{
		return 5;
	}
}
