package com.woodcuttinggoals;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

class WoodcuttingGoalsOverlay extends OverlayPanel
{
	private final WoodcuttingGoalsPlugin plugin;
	private final WoodcuttingGoalsConfig config;

	@Inject
	private WoodcuttingGoalsOverlay(WoodcuttingGoalsPlugin plugin, WoodcuttingGoalsConfig config)
	{
		super(plugin);
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(PRIORITY_LOW);
		addMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Woodcutting Goals overlay");
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		final Tree tree = plugin.getCurrentTree();
		if (!config.showOverlay() || tree == null)
		{
			return null;
		}

		panelComponent.getChildren().add(TitleComponent.builder()
			.text("Woodcutting Goals")
			.build());

		panelComponent.getChildren().add(LineComponent.builder()
			.left("Cutting:")
			.right(tree.getDisplayName())
			.build());

		if (plugin.getTreesToNextLevel() > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Trees to level:")
				.right(Integer.toString(plugin.getTreesToNextLevel()))
				.build());
		}

		if (config.showXpToLevel() && plugin.getXpToNextLevel() > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("XP to level:")
				.right(Integer.toString(plugin.getXpToNextLevel()))
				.build());
		}

		if (config.showTreesToGoal() && plugin.getTreesToGoal() > 0)
		{
			panelComponent.getChildren().add(LineComponent.builder()
				.left("Trees to goal:")
				.right(Integer.toString(plugin.getTreesToGoal()))
				.build());
		}

		return super.render(graphics);
	}
}
