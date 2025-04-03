import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveBackTileTest {
    private Player player;
    private MoveBackTile moveBackTile;

    @BeforeEach
    void setUp() {
        player = new Player("Arya");
        player.setPosition(10);// Starting at tile 10
        moveBackTile = new MoveBackTile(5, "MoveBack");
    }

    @Test
    void testActivate() {
        // Initially, player's position is 10
        moveBackTile.activate(player);

        // The player's new position should be 8 (10 - 2)
        assertEquals(8, player.getPosition());
    }

    @Test
    void testPositionWrapAround() {
        // Let's test if position wraps around correctly at the start (e.g., player is on position 1)
        player.setPosition(1); // Setting player to tile 1
        moveBackTile.activate(player);

        // After moving back by 2, player's position should wrap around and be 39 (because 1 - 2 = -1, so we wrap around to 39)
        assertEquals(39, player.getPosition());
    }

    @Test
    void testEdgeCase() {
        // Test when player is on tile 0
        player.setPosition(0);
        moveBackTile.activate(player);

        // After moving back by 2, the player should wrap around and land on tile 38
        assertEquals(38, player.getPosition());
    }
}