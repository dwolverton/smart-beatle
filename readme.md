# Smart Little Beetle

Smart Little Beetle is a programming game that provides a simple environment for building and rating simple AIs.

## Getting Started
#### 1. Download and install the JARs.

#### 2. Create an AI
An AI is a class that implements the `BeetleAi` interface. It must also have a default or no-arg constructor.

You can also add a main method, as in the example below, that starts a new game with your AI when run.

```java
import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.actions.Action;

public class MyAi implements BeetleAi {
	
	public static void main(String[] args) {
		new Game(MyAi.class);
	}

	@Override
	public Action turn(MyAi state) {
		return Action.stay();
	}

}
```

Within the `turn` method, write the logic for your AI. The turn method is called automatically by the game at the beginning of each turn. The current game state is passed in as a parameter, and the method should return one of three actions to perform this turn: `move`, `shoot`, or `stay`.

#### 3. Run the game with your AI
To run the game, simply instantiate a new instance of `Game` and pass it your AI class. This will open a window that displays a random game board.

You can play with the game directly using the following keys on your keyboard:
- LEFT, RIGHT, UP, and DOWN arrows: move the beetle.
- SHIFT + LEFT, RIGHT, UP, and DOWN arrows: shoot in the specified direction.
- SPACE: stay in the same space for a turn.

Or set you AI in action by clicking "Run AI".

#### 4. Score your AI
When the game is running, click "Score AI". This will run your AI for 100 different games in the background. Each game is rated for how many turns the beetle survives. Then average, best, and worst survival statistics are reported.

Alternatively, get the statistics for you AI directly by running the following.

```java
Stats stats = AiStatsRunner.runAiStats(MyAi.class, new Settings());
System.out.println(stats);
```

Or compare multiple AIs head to head. (You can list as many as you want.)

```java
List<Stats> allStats = AiStatsRunner.runAndCompareAis(new Settings(), MyAi.class, YourAi.class, ThisAi.class, ThatAi.class);
for (Stats stats : allStats) {
    System.out.println(stats);
}
```