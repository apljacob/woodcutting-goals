# Woodcutting Goals

A RuneLite plugin that shows how many trees of the type you are currently
cutting remain until your next Woodcutting level — and, if you set an XP
Tracker goal, until that goal. Inspired by the Agility plugin's
"laps until goal" counter.

## Features

- Detects the current tree type from the cut message (normal, oak, willow,
  teak, maple, mahogany, arctic pine, yew, magic, redwood).
- "Trees to level" counter using canonical XP-per-log values.
- Optional "Trees to goal" counter tied to your XP Tracker goal.
- Optional raw "XP to level" line.
- Overlay auto-hides after a configurable idle timeout.

## Development

- `./gradlew run`   — launch RuneLite in developer mode with this plugin loaded
- `./gradlew test`  — run unit tests
- `./gradlew build` — compile + test

## In-Game Testing

To test manually:
1. Run `./gradlew run`, log in to OSRS.
2. Enable **Woodcutting Goals** in the plugin list.
3. Chop any supported tree — the overlay appears showing tree type and trees-to-level count.
4. Right-click the XP orb → set a Woodcutting goal — the "Trees to goal" row appears.
5. Stop chopping; after the configured timeout the overlay disappears.
