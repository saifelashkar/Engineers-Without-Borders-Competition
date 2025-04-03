import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ResourceTileTest {


    private Player player;
    private ResourceTile resourceTile;

    @BeforeEach
    void setUp() {
        // Create a new player with 0 scraps initially
        player = new Player("Saif");
        player.addScraps(0);
        // Create a new ResourceTile with an index and type
        resourceTile = new ResourceTile(1, "Resource");
    }

    @Test
    void testActivate() {
        // Activate the resource tile and check the scraps
        int initialScraps = player.getTotalScraps();

        // Activate the tile
        resourceTile.activate(player);

        // After activation, player should have 10 more scraps
        assertEquals(initialScraps + 10, player.getTotalScraps());
    }

    @Test
    void testActivateWithDifferentPlayer() {
        // Create another player with a different initial scraps value
        Player newPlayer = new Player("Arya");
        player.addScraps(5);
        ResourceTile newResourceTile = new ResourceTile(2, "Resource");

        // Initial scraps of the new player
        int initialScraps = newPlayer.getTotalScraps();

        // Activate the tile for the new player
        newResourceTile.activate(newPlayer);

        // Check that the new player has 10 more scraps
        assertEquals(initialScraps + 10, newPlayer.getTotalScraps());
    }
}