import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoveForwardTileTest {
    private MoveForwardTile moveForwardTile;
    private Player player;

    @BeforeEach
    void setUp() {
        moveForwardTile = new MoveForwardTile(5, "MoveForward");
        player = new Player("TestPlayer"); // Only passing name, assuming position is set separately
        player.setPosition(10); // Set initial position
    }

    @Test
    void testActivate() {
        moveForwardTile.activate(player);
        assertEquals(13, player.getPosition(), "Player should move forward 3 spaces");
    }

    @Test
    void testActivateWrapAround() {
        player.setPosition(38); // Near board limit
        moveForwardTile.activate(player);
        assertEquals(1, player.getPosition(), "Player should wrap around when exceeding board size");
    }
}