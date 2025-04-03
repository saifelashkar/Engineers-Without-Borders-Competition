import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

class Objective {
    protected String Objectives;
    protected String Tasks;
    protected int Tasks_number;

    public Objective(String objectives, String tasks, int tasks_number) {
        this.Objectives = objectives;
        this.Tasks = tasks;
        this.Tasks_number = tasks_number;
    }
    public void startObjective() {
        System.out.println("Objective started: " + Objectives);
    }
}

class Task1_Building_Pipe extends Objective {
    private String Task1_content;
    private int Task1_Scraps;
    private int Task1_Dice;
    private int Task1_Questions_number;

    public Task1_Building_Pipe() {
        super("Build a Pipe", "Pipe Construction", 1);
    }

    public void AskQuestion() {}
    public void Check_answer() {}
    public void Retry_Questions() {}
    public void Rand_Dice() {}
    public void Roll_Dice() {}
    public void Retry_Dice() {}
    public void Check_Number_of_Scraps() {}
    public void Request100Scraps() {}
}

class Task2_Building_Tank extends Objective {
    private String Task2_content;
    private int Task2_Scraps;
    private int Task2_Dice;
    private int Task2_Questions_number;

    public Task2_Building_Tank() {
        super("Build a Tank", "Tank Construction", 2);
    }

    public void AskQuestion() {}
    public void Check_answer() {}
    public void Retry_Questions() {}
    public void Rand_Dice() {}
    public void Roll_Dice() {}
    public void Retry_Dice() {}
    public void Check_Number_of_Scraps() {}
    public void Request100Scraps() {}
}

class Task3_Building_Filter extends Objective {
    private String Task3_content;
    private int Task3_Scraps;
    private int Task3_Dice;
    private int Task3_Questions_number;

    public Task3_Building_Filter() {
        super("Build a Filter", "Filter Construction", 3);
    }

    public void AskQuestion() {}
    public void Check_answer() {}
    public void Retry_Questions() {}
    public void Rand_Dice() {}
    public void Roll_Dice() {}
    public void Retry_Dice() {}
    public void Check_Number_of_Scraps() {}
    public void Request100Scraps() {}
}

class Task4_Building_Pump extends Objective {
    private String Task4_content;
    private int Task4_Scraps;
    private int Task4_Dice;
    private int Task4_Questions_number;

    public Task4_Building_Pump() {
        super("Build a Pump", "Pump Construction", 4);
    }

    public void AskQuestion() {}
    public void Check_answer() {}
    public void Retry_Questions() {}
    public void Rand_Dice() {}
    public void Roll_Dice() {}
    public void Retry_Dice() {}
    public void Check_Number_of_Scraps() {}
    public void Request100Scraps() {}
}


abstract class Tiles {
    int index;
    String type;
    public Tiles(int index, String type) {
        this.index = index;
        this.type = type;
    }
    public abstract void activate(Player player);
}
class StartingTile extends Tiles {
    public StartingTile(int index, String type) {
        super(index, "start");
    }
    @Override
    public void activate(Player player) {
        player.addScraps(20);
        System.out.println(player.getName() + " Landed on starting tile " + index );
        System.out.println(player.getName() + " receives 20 scraps " );
    }
}
class QuestionTile extends Tiles {
    private static List<QuestionTile> QuestionPool = new ArrayList<>();
    private String question;
    private String[] options;
    private int correctAnswer;

    public static List<QuestionTile> getQuestionPool() {
        return new ArrayList<>(QuestionPool); // Return a copy of the static QuestionPool
    }
    static {
        // Initialize the question pool with some questions
        QuestionPool.add(new QuestionTile("What is the main reason for water cutoffs in Johannesburg?", new String[]{"Lack of rainfall", "Unstable electricity grid", "Overpopulation", "Seasonal changes"}, 1));
        QuestionPool.add(new QuestionTile("Why is rainwater collection beneficial for households?", new String[]{"Reduces electricity consumption", "Provides an alternative water source during outages", "Prevents flooding in low-lying areas", "Improves water taste"}, 1));
        QuestionPool.add(new QuestionTile("What is a common cause of water loss in Johannesburg’s old infrastructure?", new String[]{"Illegal water tapping", "Water leaks", "Poor filtration systems", "Excessive water usage"}, 1));
        QuestionPool.add(new QuestionTile("Which of the following methods can purify rainwater for drinking?", new String[]{"Sand filtration", "Solar disinfection", "Gravel filtration", "Boiling"}, 3));
        QuestionPool.add(new QuestionTile("What percentage of Johannesburg’s water is lost due to leaks and theft?", new String[]{"25%", "35%", "45%", "55%"}, 2));
        QuestionPool.add(new QuestionTile("What does a domestic Rain Cycle Collector primarily rely on?", new String[]{"Groundwater pumps", "Roof rainwater collection", "Public water systems", "Underground reservoirs"}, 1));
        QuestionPool.add(new QuestionTile("What is one challenge faced by low-income neighborhoods in accessing water?", new String[]{"Droughts", "High water tariffs", "Illegal water tapping", "Contaminated rivers"}, 1));
        QuestionPool.add(new QuestionTile("How can sand and gravel filters be used in rainwater collection?", new String[]{"To desalinate water", "To remove dirt and debris", "To heat the water", "To store water"}, 1));
        QuestionPool.add(new QuestionTile("Which of these is optional in a domestic Rain Cycle Collector?", new String[]{"Water tank", "Solar disinfection", "Filtration system", "Rainwater collection"}, 1));
        QuestionPool.add(new QuestionTile("Why are water protests common in Johannesburg?", new String[]{"Lack of rainfall", "Dissatisfaction with municipal services", "Overpopulation in urban areas", "High rainfall variability"}, 1));
        QuestionPool.add(new QuestionTile("What is the primary environmental challenge affecting Johannesburg’s water supply?", new String[]{"Lack of rivers", "Climate change", "Overuse of groundwater", "Inefficient water tanks"}, 1));
        QuestionPool.add(new QuestionTile("What is one feature of a Public Rain Cycle Collector?", new String[]{"Personal water storage", "Sand and gravel filtration", "Underground reservoirs", "Portable water tanks"}, 2));
        QuestionPool.add(new QuestionTile("What happens when residents do not pay for water in Johannesburg?", new String[]{"Reduced water quality", "Water shutoffs", "Increased water theft", "Higher taxes"}, 1));
        QuestionPool.add(new QuestionTile("What economic challenge does Johannesburg face regarding water?", new String[]{"Lack of water demand", "High water costs", "Excessive rainfall", "Export of water resources"}, 1));
    }

    public QuestionTile(int index, String type) { // Constructor for board initialization
        super(index, type);
        QuestionTile template = getRandomQuestionTile();
        this.question = template.question;
        this.options = template.options;
        this.correctAnswer = template.correctAnswer;
    }

    public QuestionTile(String question, String[] options, int correctAnswer) { // Constructor for question pool
        super(0, "question");
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public static QuestionTile getRandomQuestionTile() {
        Random random = new Random();
        return QuestionPool.get(random.nextInt(QuestionPool.size()));
    }

    @Override
    public void activate(Player player) {
        List<QuestionTile> availableQuestions = player.getAvailableQuestions();
        if (availableQuestions == null || availableQuestions.isEmpty()) {
            System.out.println("No more questions available for " + player.getName());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("You landed on a question tile!");

        // Get a random question from the player's available questions
        Random random = new Random();
        QuestionTile questionTile = availableQuestions.get(random.nextInt(availableQuestions.size()));
        System.out.println(questionTile.question);
        for (int i = 0; i < questionTile.options.length; i++) {
            System.out.println((i + 1) + ". " + questionTile.options[i]);
        }
        System.out.print("Your answer is: ");
        int playerAnswer = scanner.nextInt();

        if (playerAnswer == questionTile.correctAnswer) {
            System.out.println("Correct! You gain +1 bucket water");
            System.out.println("You have finished the Question Task!");
            player.addWaterBucket(1);
            player.setAnsweredQuestionCorrectly(true);
        } else {
            System.out.println("Incorrect! You lose -1 bucket water");
            player.subtractWaterBucket(1);
        }

        // Remove the question from the player's available questions
        player.removeQuestion(questionTile);
    }
}


class WaterStationTile extends Tiles {
    private final int waterBucketPrice;
    private Player owner;

    public WaterStationTile(int index, String type, Player owner) { // Accept two arguments
        super(index, type); // Pass index and type to the superclass
        this.waterBucketPrice = 40;
        this.owner = owner;
    }

    @Override
    public void activate(Player player) {
        if (owner != player) {
            System.out.println(player.getName() + " has landed on a water station tile that is not theirs, nothing happens!");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println(player.getName() + " landed on the Water Station Tile!");
        System.out.println("You have " + player.getTotalScraps() + " Scraps.");
        System.out.println("Each Bucket of Water costs 40 Scraps.");

        if (player.getTotalScraps() < waterBucketPrice) {
            System.out.println("You don't have enough Scraps to buy a bucket of water.");
            return;
        }

        System.out.print("Do you want to buy 1 Bucket of Water? (1 = Yes, 0 = No): ");
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice == 0 || choice == 1) break;
            } catch (Exception e) {
                scanner.nextLine();
            }
            System.out.println("Invalid input! Please enter 1 or 0.");
        }

        if (choice == 1) {
            player.subtractScraps(waterBucketPrice);
            player.addWaterBucket(1);
            System.out.println(player.getName() + " bought 1 Bucket of Water!");
        } else {
            System.out.println(player.getName() + " chose not to buy any Bucket of Water.");
        }

        System.out.println(player.getName() + " now has " + player.getWaterBuckets()+ " Buckets of Water.");
        System.out.println(player.getName() + " now has " + player.getTotalScraps() + " Scraps left.");
    }
}

class ResourceTile extends Tiles {
    private final int ResourceScraps;

    public ResourceTile(int index, String type) { // Accept two arguments
        super(index, type); // Pass index and type to the superclass
        this.ResourceScraps = 10;
    }

    @Override
    public void activate(Player player) {
        System.out.println(player.getName() + " landed on a Resource Tile!");
        System.out.println("You have " + player.getTotalScraps() + " Scraps.");
        System.out.println("You will get 10 Scraps from the Resource Tile!");
        player.addScraps(ResourceScraps);
        System.out.println(player.getName() + " now has " + player.getTotalScraps() + " Scraps.");
    }
}

class HazardTile extends Tiles {
    private final int hazardWaterBuckets;

    public HazardTile(int position, String type) {
        super(position, type);
        this.hazardWaterBuckets = 1;
    }

    @Override
    public void activate(Player player) {
        System.out.println(player.getName() + " landed on a Hazard Tile!");
        System.out.println("You have " + player.getWaterBuckets() + " water buckets."); // Use getWaterBuckets()
        System.out.println("You will lose 1 water bucket from the Hazard Tile!");
        player.subtractWaterBucket(hazardWaterBuckets);
        System.out.println(player.getName() + " now has " + player.getWaterBuckets() + " water buckets."); // Use getWaterBuckets()
    }
}

class NeutralTile extends Tiles {
    public NeutralTile(int index, String type) { // Accept two arguments
        super(index, type); // Pass index and type to the superclass
    }

    @Override
    public void activate(Player player) {
        System.out.println(player.getName() + " landed on a Neutral Tile. Nothing happens.");
    }
}


class DonationTile extends Tiles {
    private final int donationAmount;
    private List<Player> players; // Store the players list

    public DonationTile(int index, String type, List<Player> players) { // Accept three arguments
        super(index, type); // Pass index and type to the superclass
        this.donationAmount = 10;
        this.players = players; // Initialize the players list
    }

    @Override
    public void activate(Player player) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(player.getName() + " landed on a Donation Tile.");

        if (player.getTotalScraps() < donationAmount) {
            System.out.println(player.getName() + " does not have enough Scraps to donate.");
            return;
        }

        System.out.println("Choose a player to donate " + donationAmount + " Scraps to:");
        int index = 1;
        for (Player otherPlayer : players) { // Iterate over the players list
            if (!otherPlayer.getName().equals(player.getName())) { // Skip the current player
                System.out.println(index + ". " + otherPlayer.getName());
                index++;
            }
        }

        System.out.print("Enter the number of the player to donate to: ");
        int choice = scanner.nextInt();

        Player recipient = null;
        index = 1;
        for (Player otherPlayer : players) { // Iterate over the players list
            if (!otherPlayer.getName().equals(player.getName())) { // Skip the current player
                if (index == choice) {
                    recipient = otherPlayer;
                    break;
                }
                index++;
            }
        }

        if (recipient != null) {
            player.subtractScraps(donationAmount);
            recipient.addScraps(donationAmount);
            System.out.println(player.getName() + " donated " + donationAmount + " Scraps to " + recipient.getName() + "!");
        } else {
            System.out.println("Invalid choice. Donation cancelled.");
        }
    }
}
class MoveForwardTile extends Tiles {
    private static final int moveForward = 3;

    public MoveForwardTile(int index, String type) { // Accept two arguments
        super(index, type); // Pass index and type to the superclass
    }

    @Override
    public void activate(Player player) {
        System.out.println(player.getName() + " landed on a Move Forward Tile! Moving forward " + moveForward + " spaces.");
        player.setPosition((player.getPosition() + moveForward) % 40);
        System.out.println(player.getName() + " is now on tile " + player.getPosition());
    }
}

class MoveBackTile extends Tiles {
    private static final int moveBack = 2;

    public MoveBackTile(int index, String type) { // Accept two arguments
        super(index, type); // Pass index and type to the superclass
    }

    @Override
    public void activate(Player player) {
        System.out.println(player.getName() + " landed on a Move back Tile! Moving back " + moveBack + " spaces.");
        int newPosition = (player.getPosition() - moveBack) % 40;

        if (newPosition < 0) {
            newPosition += 40;
        }
        player.setPosition(newPosition);
        System.out.println(player.getName() + " is now on tile " + player.getPosition());
    }
}
class DiceChallengeTile extends Tiles {
    Random random;

    public DiceChallengeTile(int index, String type) {
        super(index, "dice");
        this.random = new Random();
    }

    @Override
    public void activate(Player player) {
        System.out.println(player.getName() + " landed on a Dice Challenge Tile!");
        System.out.println("You must roll a 6 to leave this tile.");

        int rollCount = 0;
        int diceRoll;

        do {
            diceRoll = random.nextInt(6) + 1; // Roll the dice (1 to 6)
            rollCount++;
            System.out.println("Roll " + rollCount + ": You rolled a " + diceRoll + ".");
        } while (diceRoll != 6); // Continue rolling until a 6 is rolled

        System.out.println("Congratulations! You rolled a 6 in " + rollCount + " attempts.");
        System.out.println("You are now free to leave the Dice Challenge Tile.");
        System.out.println("You have finished the Dice Challenge Tile.");
    }
}
public class Main {
    private ArrayList<Player> players;
    private Scanner scanner;

    public Main() {
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Game Started!");
        System.out.println("Makers Valley faces water shortages, " +
                "and players must complete their objectives to build parts of a " +
                "water filtration system, which will be combined once all are finished.");
        int numPlayers = getNumberPlayers();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + " name: ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
        System.out.println("Players registered, starting game!");

        // Create the board and pass the players list
        board board = new board(40, players);

        // Pass the board to playGame
        playGame(board);
    }
    private int getNumberPlayers(){
        int numPlayers;
        do {
            System.out.println("Enter number of players: ");
            numPlayers = scanner.nextInt();
            scanner.nextLine();
            if (numPlayers < 1 || numPlayers > 4){
                System.out.println("Number of players must be between 1 and 4!");
            }

        }while(numPlayers < 1 || numPlayers > 4);
        return numPlayers;
    }
    private boolean allPlayersCompletedTasks(List<Player> players) {
        for (Player player : players) {
            if (!player.hasCompletedAllTasks()) {
                return false; // At least one player hasn't completed their tasks
            }
        }
        return true; // All players have completed their tasks
    }

    private void playGame(board board) {
        boolean gameRunning = true;

        while (gameRunning) {
            for (Player player : players) {
                if (!player.hasFinished()) {
                    player.displayStatus();
                    System.out.println(player.getName() + ", are you ready to roll? (Y/N)");
                    String input = scanner.nextLine().trim().toUpperCase();
                    while (!input.equals("Y") && !input.equals("N")) {
                        System.out.println("Invalid input! Use Y or N.");
                        input = scanner.nextLine().trim().toUpperCase();
                    }

                    if (input.equals("N")) {
                        System.out.println(player.getName() + " has decided to stop playing.");
                        System.out.println("2025: The filtration system remains incomplete, leaving the village vulnerable to water shortages.");
                        System.out.println("2026: Residents continue to face unstable water access, and health issues persist.");
                        gameRunning = false;
                        System.exit(0);
                    }

                    if (input.equals("Y")) {
                        int diceRoll = dice();
                        System.out.println(player.getName() + " rolled a " + diceRoll);
                        player.move(diceRoll);

                        Tiles currentTile = board.getTile(player.getPosition());
                        if (currentTile instanceof DiceChallengeTile || currentTile instanceof QuestionTile) {
                            if (player.canCompleteChallenge()) {
                                currentTile.activate(player);
                                if (currentTile instanceof DiceChallengeTile) {
                                    player.setCompletedDiceChallenge(true);
                                } else if (currentTile instanceof QuestionTile) {
                                    player.setAnsweredQuestionCorrectly(true);
                                }
                            } else {
                                System.out.println(player.getName() + " needs to pass the start tile with 100 scraps to attempt this challenge.");
                            }
                        } else {
                            currentTile.activate(player);
                        }

                        if (player.getPosition() == 0 && player.getTotalScraps() >= 100) {
                            player.completeTask();
                        }

                        // Check if all players have completed their tasks
                        if (allPlayersCompletedTasks(players)) {
                            System.out.println("All players have completed their tasks! The game is over.");
                            System.out.println("Success Epilogue: ");
                            System.out.println("2025: The completed water filtration system is installed across the village, providing a stable and clean water supply.");
                            System.out.println("2026: Households begin to rely on filtered rainwater, reducing dependence on the central water system.");
                            System.out.println("2027: Public health improves, and the community becomes more self-sufficient.");

                            // Display final stats for all players
                            for (Player p : players) {
                                System.out.println(p.getName() + " has " + p.getTotalScraps() + " scraps and " + p.getWaterBuckets() + " water buckets.");
                            }

                            gameRunning = false;
                            System.exit(0);
                        }

                        // Check if any player has run out of water buckets
                        if (board.checkEnd(players)) {
                            System.out.println(player.getName() + " has run out of water buckets! Game over!");
                            System.out.println("2025: The filtration system remains incomplete, leaving the village vulnerable to water shortages.");
                            System.out.println("2026: Residents continue to face unstable water access, and health issues persist.");
                            gameRunning = false;
                            System.exit(0);
                        }

                        if (player.getPosition() >= 40) {
                            player.setFinished(true);
                            System.out.println(player.getName() + " has finished the game!");
                        }
                    }
                }
            }
            gameRunning = players.stream().anyMatch(p -> !p.hasFinished());
        }
        System.out.println("Game over! All players has finished the game!");
        System.out.println("Success Epilogue: ");
        System.out.println("2025: The completed water filtration system is installed across the village, providing a stable and clean water supply.");
        System.out.println("2026: Households begin to rely on filtered rainwater, reducing dependence on the central water system");
        System.out.println("2027: Public health improves, and the community becomes more self-sufficient.");
        if (players.size() > 0) {
            System.out.println(players.get(0).getName() + " has " + players.get(0).getTotalScraps() + " scraps and " + players.get(0).getWaterBuckets() + " water buckets." );
        } else if (players.size() > 1) {
            System.out.println(players.get(1).getName() + " has " + players.get(2).getTotalScraps() + " scraps and " + players.get(1).getWaterBuckets() + " water buckets." );
        } else if (players.size() > 2) {
            System.out.println(players.get(2).getName() + " has " + players.get(2).getTotalScraps() + " scraps and " + players.get(2).getWaterBuckets() + " water buckets." );
        } else if (players.size() > 3) {
            System.out.println(players.get(3).getName() + " has " + players.get(3).getTotalScraps() + " scraps and " + players.get(3).getWaterBuckets() + " water buckets." );
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }

    public static int dice(){
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
        return dice;
    }
}

