package com.woodcuttinggoals;

import com.google.inject.Provides;
import java.time.Duration;
import java.time.Instant;
import javax.annotation.Nullable;
import javax.inject.Inject;
import lombok.Getter;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.xptracker.XpTrackerService;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Woodcutting Goals",
	description = "Shows how many trees of the current type until your next level or goal",
	tags = {"woodcutting", "skilling", "xp", "level", "goal", "trees"}
)
public class WoodcuttingGoalsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private WoodcuttingGoalsConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WoodcuttingGoalsOverlay overlay;

	@Inject
	private XpTrackerService xpTrackerService;

	// ---- State read by the overlay (package-private getters via Lombok) ----

	@Getter
	@Nullable
	private Tree currentTree;

	@Getter
	private int xpToNextLevel;

	@Getter
	private int treesToNextLevel;

	@Getter
	private int treesToGoal;

	@Nullable
	private Instant lastChopTime;

	@Provides
	WoodcuttingGoalsConfig provideConfig(net.runelite.client.config.ConfigManager configManager)
	{
		return configManager.getConfig(WoodcuttingGoalsConfig.class);
	}

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
		reset();
	}

	private void reset()
	{
		currentTree = null;
		xpToNextLevel = 0;
		treesToNextLevel = 0;
		treesToGoal = 0;
		lastChopTime = null;
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() != ChatMessageType.SPAM
			&& event.getType() != ChatMessageType.GAMEMESSAGE)
		{
			return;
		}

		Tree tree = Tree.fromChatMessage(event.getMessage());
		if (tree == null)
		{
			return;
		}

		currentTree = tree;
		lastChopTime = Instant.now();
		recompute();
	}

	@Subscribe
	public void onStatChanged(StatChanged event)
	{
		if (event.getSkill() == Skill.WOODCUTTING && currentTree != null)
		{
			recompute();
		}
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		if (currentTree == null || lastChopTime == null)
		{
			return;
		}

		Duration timeout = Duration.ofMinutes(config.overlayTimeout());
		if (Duration.between(lastChopTime, Instant.now()).compareTo(timeout) >= 0)
		{
			reset();
		}
	}

	private void recompute()
	{
		final int currentXp = client.getSkillExperience(Skill.WOODCUTTING);
		final double xpPerTree = currentTree.getXp();

		xpToNextLevel = WoodcuttingGoalsCalculator.xpToNextLevel(currentXp);
		treesToNextLevel = WoodcuttingGoalsCalculator.treesUntil(xpToNextLevel, xpPerTree);

		final int goalXp = xpTrackerService.getEndGoalXp(Skill.WOODCUTTING);
		final int goalRemaining = goalXp - currentXp;
		treesToGoal = WoodcuttingGoalsCalculator.treesUntil(goalRemaining, xpPerTree);
	}
}
