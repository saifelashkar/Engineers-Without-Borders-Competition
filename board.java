import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class board {
    private List<Tiles> tiles; // List of tiles on the board
    private List<Player> players; // List of players (used for tiles like DonationTile)

    public board(int size, List<Player> players) {
        this.players = players; // Initialize the players list
        tiles = new ArrayList<>(size); // Initialize the tiles list
        initializeTiles(size, players); // Populate the board with tiles
    }

    // Initialize the board with tiles at specific positions
    private void initializeTiles(int size, List<Player> players) {
        for (int i = 0; i < size; i++) {
            switch (i) {
                case 0:
                    tiles.add(new StartingTile(i, "start")); // Starting tile
                    break;
                case 1:
                case 4:
                case 9:
                case 11:
                case 14:
                case 19:
                case 21:
                case 24:
                case 29:
                case 31:
                case 34:
                case 39:
                    tiles.add(new ResourceTile(i, "resource")); // Resource tiles
                    break;
                case 2:
                case 6:
                case 12:
                case 26:
                case 32:
                    tiles.add(new HazardTile(i, "hazard")); // Hazard tiles
                    break;
                case 3:
                case 17:
                case 23:
                case 37:
                    tiles.add(new NeutralTile(i, "neutral")); // Neutral tiles
                    break;
                case 5:
                case 15:
                case 25:
                case 35:
                    tiles.add(new DonationTile(i, "donation", players)); // Donation tiles
                    break;
                case 7:
                case 27:
                    tiles.add(new MoveForwardTile(i, "moveForward")); // Move forward tiles
                    break;
                case 8:
                case 18:
                case 28:
                    tiles.add(new QuestionTile(i, "question")); // Question tiles
                    break;
                case 10:
                    if (players.size() > 0) {
                        tiles.add(new WaterStationTile(i, "waterStation", players.get(0)));
                    }
                    break;
                case 20:
                    if (players.size() > 1) {
                        tiles.add(new WaterStationTile(i, "waterStation", players.get(1)));
                    }else {
                        tiles.add(new NeutralTile(i, "neutral"));
                    }
                    break;
                case 30:
                    if (players.size() > 2) {
                        tiles.add(new WaterStationTile(i, "waterStation", players.get(2)));
                    }else {
                        tiles.add(new NeutralTile(i, "neutral"));
                    }
                    break;
                case 36:
                    if (players.size() > 3) {
                    tiles.add(new WaterStationTile(i, "waterStation", players.get(3)));
                     }else {
                    tiles.add(new NeutralTile(i, "neutral"));
                }// Water station tiles
                    break;
                case 13:
                case 33:
                    tiles.add(new MoveBackTile(i, "moveBack")); // Move back tiles
                    break;
                case 38:
                case 16:
                case 22:
                    tiles.add(new DiceChallengeTile(i, "diceChallenge")); // Dice challenge tile
                    break;
                default:
                    tiles.add(new NeutralTile(i, "neutral")); // Default to neutral tile
                    break;
            }
        }
    }

    // Get the tile at a specific position
    public Tiles getTile(int position) {
        return tiles.get(position % tiles.size()); // Use modulo to handle wrapping around the board
    }

    // Static method to get a tile from the board
    public static Tiles getTilesFromBoard(board board, int position) {
        return board.getTile(position);
    }

    // Check if the game should end for a player
    public boolean checkEnd(List<Player> players) {
        // Check if all players have completed their tasks
        boolean allPlayersCompleted = true;
        for (Player player : players) {
            if (!player.hasCompletedAllTasks()) {
                allPlayersCompleted = false;
                break;
            }
        }

        if (allPlayersCompleted) {
            System.out.println("All players have completed their tasks! The game is over.");
            return true;
        }

        // Check if any player has run out of water buckets
        for (Player player : players) {
            if (player.getWaterBuckets() <= 0) {
                System.out.println(player.getName() + " has run out of water buckets! Game over!");
                return true;
            }
        }

        return false; // Game continues
    }
}