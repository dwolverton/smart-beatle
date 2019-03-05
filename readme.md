# Smart Little Beetle

Smart Little Beetle is a programming game that provides an environment for building and rating simple AIs.

## Getting Started
#### 1. Download and install the JARs.

#### 2. Create an AI
An AI is a class that implements the `BeetleAi` interface. It must also have a default or no-arg constructor.

You can also add a main method, as in the example below, that starts a new game with your AI.

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
To run the game, simply instantiate a new instance of `Game` and pass it your AI class. This will open a window that displays a random game field.

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

## Game Rules
The game consists of a 20 x 20 field of spaces identified by x,y coordinates. (0,0 is the top left corner space.) It is populated with the following elements.

##### Characters

* *Beetle* Our protagonist, a robotic beetle that must keeps its battery charged while fighting off ants and avoiding the spider. The goals is to keep this little bug alive as long as possible.
* *The Spider* The spider is unstoppable, but slow. It doesn't move every turn. Check its nextMove property to see how many turns until it moves again--one means it will be this turn, immediately after the beetle's action.
* *Ants* Ants always move toward the beetle. They move one space per turn, just like the beetle. They can be stopped by shooting beads (ammo) at them, but only when they are directly adjacent to the beetle in one of the four cardinal directions. Ants spawn from the ant hill at intervals.

##### Terrain Features

* *Charging Pads* The beetle receives charge when it ends its turn on one of these.
* *The Ant Hill* Ants periodically appear from this hill. The frequency increases as the game goes on. Check its `nextMove` property to see how many turns until the next ant--one means it will be this turn, immediately after the beetle's action.
* *Beads* The beetle can collect beads to be used as ammunition against ants. There is a constant total number of beads in the game. (The beetle's ammo + the beads on the field.

### Turns
Each turn starts with the `BeatleAI` having a chance to examine the `GameState` and responding by selecting and returning one of three actions:

* `Action.move(Direction)` - The beetle moves 1 space in the given direction (one of four options--N, S, E, or W).
* `Action.shoot(Direction)` - The beetle shoots a bead in the given direction (N, S, E, or W). If an ant right next to the beetle in that direction, it will be "killed" (removed from the game).
* `Action.stay()` - Take no action.

Once the beetle's action has been performed, its battery charge is decremented and the remaining game elements make their moves. Charging pads increase the beetle's charge. Spiders and ants move toward the beetle. The ant hill may spawn an ant. And a new bead may appear to replace one that beetle has shot.

### The End
If the beetle runs out of charge or a spider or ant reaches the beetle's space, the game is over. The game also ends if the beetle attempts to move out of bounds or an exception is thrown.

## Building an AI
Your job is to implement the `turn` method. It is called at the beginning of every turn and is passed the current state of the game. Your method should return an action.

You can explore the `GameState` object to see what information it provides. Here are some other classes that are useful to be familiar with:

* `Coord` An x, y coordinate pair. Coord also serves as the base class for all game elements, which means that each game element has all of Coord's methods (inheritance) and can be used anywhere a Coord is called for (polymorphism).
  * `Coord` has many useful methods, such as `	rightAngleDistanceFrom` for measuring distance, `directionTo​` for finding direction toward an element, and `isAt` to see if you're going to run into something.
* `Direction` An enum of the cardinal directions. It has several useful methods, too, such as `relativeTo` which finds the coordinate this direction of another element or coordinate. There is also a `NONE` direction, which just stays put.

To give an example, here's an AI that moves toward the nearest charging pad.

```java
@Override
public Action turn(GameState state) {
	int pad1Dist = state.getChargingPads().get(0).rightAngleDistanceFrom(state.getBeetle());
	int pad2Dist = state.getChargingPads().get(1).rightAngleDistanceFrom(state.getBeetle());
	
	ChargingPad pad;
	if (pad1Dist <= pad2Dist) {
		pad = state.getChargingPads().get(0);
	} else {
		pad = state.getChargingPads().get(1);
	}
	
	Direction directionToPad = state.getBeetle().directionTo(pad);
	
	return Action.move(directionToPad);
}
```

It is also possible keep variables on your AI. A new instance of your AI class is created at the beginning of each game and reused for each turn.

Additionally, your AI can optionally implement the `init` method which is called just once before the first turn. It includes the initial board state and the game settings. The game `Settings` let you know the exact specs various aspects of the game such as maximum charge of your beetle, how fast the spider is, and how often ants will come.

Here's an example of an AI that uses `init` and also keeps track of information between turns.

```java
public class RunInCirclesAi implements BeetleAi {
	
	private Direction direction;
	private int distance;

	@Override
	public void init(GameState state, Settings settings) {
		// set initial direction toward middle
		direction = state.getBeetle().directionTo(new Coord(10, 10));
		distance = 3;
	}

	@Override
	public Action turn(GameState state) {
		if (--distance == 0) {
			distance = 3;
			direction = direction.nextClockwise();
		}
		return Action.move(direction);
	}

}
```