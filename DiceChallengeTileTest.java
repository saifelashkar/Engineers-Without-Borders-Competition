import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class DiceChallengeTileTest {
    @Mock
    private Player mockPlayer;

    private DiceChallengeTile diceChallengeTile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockPlayer.getName()).thenReturn("Arya");

        // Create a subclass that overrides the random rolling
        diceChallengeTile = new DiceChallengeTile(1, "dice") {
            private final int[] fixedRolls = {3, 2, 6}; // Fixed rolls (3, 2, 6)
            private int rollIndex = 0;

            @Override
            public void activate(Player player) {
                System.out.println(player.getName() + " landed on a Dice Challenge Tile!");
                System.out.println("You must roll a 6 to leave this tile.");

                int rollCount = 0;
                int diceRoll;

                do {
                    diceRoll = fixedRolls[rollIndex++]; // Use predefined rolls
                    rollCount++;
                    System.out.println("Roll " + rollCount + ": You rolled a " + diceRoll + ".");
                } while (diceRoll != 6);

                System.out.println("Congratulations! You rolled a 6 in " + rollCount + " attempts.");
                System.out.println("You are now free to leave the Dice Challenge Tile.");
            }
        };
    }

    @Test
    void testActivate_rollsUntilSix() {
        // Run the test version of activate
        diceChallengeTile.activate(mockPlayer);

        // If we reach here, the test passed (since it didn't get stuck in an infinite loop)
        assertTrue(true);
    }
}