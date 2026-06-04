package com.woodcuttinggoals;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class WoodcuttingGoalsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(WoodcuttingGoalsPlugin.class);
		RuneLite.main(args);
	}
}
