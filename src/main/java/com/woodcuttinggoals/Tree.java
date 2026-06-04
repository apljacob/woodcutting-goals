package com.woodcuttinggoals;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Getter;

/**
 * Each tree the plugin understands, carrying the chat token used to detect it
 * and the canonical Woodcutting XP granted per log.
 *
 * Detection is by chat message ("You get some willow logs.") rather than XP
 * delta, because fractional XP values (oak = 37.5) cannot be reliably
 * recovered from the integer in-game XP counter.
 */
@Getter
enum Tree
{
	TREE("", "Tree", 25.0),
	OAK("oak", "Oak", 37.5),
	WILLOW("willow", "Willow", 67.5),
	TEAK("teak", "Teak", 85.0),
	MAPLE("maple", "Maple", 100.0),
	MAHOGANY("mahogany", "Mahogany", 125.0),
	ARCTIC_PINE("arctic pine", "Arctic pine", 40.0),
	YEW("yew", "Yew", 175.0),
	MAGIC("magic", "Magic", 250.0),
	REDWOOD("redwood", "Redwood", 380.0);

	/** Lowercase wood-type token as it appears in the cut message; "" for a normal tree. */
	private final String token;
	/** Human-readable name shown in the overlay. */
	private final String displayName;
	/** Canonical Woodcutting XP per log. */
	private final double xp;

	Tree(String token, String displayName, double xp)
	{
		this.token = token;
		this.displayName = displayName;
		this.xp = xp;
	}

	private static final Map<String, Tree> BY_TOKEN = new HashMap<>();

	static
	{
		for (Tree tree : values())
		{
			BY_TOKEN.put(tree.token, tree);
		}
	}

	// Captures the wood-type token between "some"/"a"/"an" and "log"/"logs".
	// "You get some willow logs." -> group(1) = "willow "
	// "You get some logs."        -> group(1) = ""
	// "You get an oak log."       -> group(1) = "oak "
	private static final Pattern CUT_PATTERN =
		Pattern.compile("^You get (?:some|an?) ([a-z ]*?)logs?\\.$");

	/**
	 * Parse a woodcutting cut message into the matching {@link Tree}, or
	 * {@code null} if the message is not a recognised cut message.
	 */
	@Nullable
	static Tree fromChatMessage(String message)
	{
		Matcher matcher = CUT_PATTERN.matcher(message);
		if (!matcher.matches())
		{
			return null;
		}

		String token = matcher.group(1).trim();
		return BY_TOKEN.get(token);
	}
}
