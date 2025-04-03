import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HazardTileTest {
    private Player player;
    private HazardTile hazardTile;

    @BeforeEach
    void setUp() {
        player = new Player("Arya");
        player.addWaterBucket(0);// Assuming player starts with 3 water buckets
        hazardTile = new HazardTile(5, "Hazard"); // Position 5, type "Hazard"
    }

    @Test
    void testActivate() {
        hazardTile.activate(player);
        assertEquals(4, player.getWaterBuckets(), "Player should have 4 water buckets after landing on a Hazard Tile.");
    }
}