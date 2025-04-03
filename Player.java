import java.util.*;

public class Player {
    private String name;
    private int position;
    private int scraps;
    private boolean finished;
    private int waterBuckets;
    private boolean answeredQuestionCorrectly;
    private boolean completedDiceChallenge;
    private boolean passedstartwith100scraps;
    private String objective; // Each player has one objective as a string
    private int taskcompleted;
    private static List<String> assignedObjectives = new ArrayList<>();
    private List<QuestionTile> availableQuestions;

    // Static list of all possible objectives
    private static final String[] OBJECTIVE_POOL = {"Build Pipe", "Build Tank", "Build Filter", "Build Pump"};

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.scraps = 20;
        this.finished = false;
        this.waterBuckets = 10;
        this.answeredQuestionCorrectly = false;
        this.completedDiceChallenge = false;
        this.passedstartwith100scraps = false;
        this.taskcompleted = 0;
        this.availableQuestions = new ArrayList<>(QuestionTile.getQuestionPool());

        // Assign a unique objective to the player
        this.objective = assignUniqueObjective();
        System.out.println(name + " has been assigned the objective: " + this.objective);

        System.out.println(name + " needs to complete 3 tasks: Pass the start tile with 100 scraps, complete the dice challenge tile, complete the question tile correctly.");
    }

    // Static method to assign a unique objective
    private static String assignUniqueObjective() {
        // Randomize the order of objectives
        List<String> shuffledObjectives = new ArrayList<>(Arrays.asList(OBJECTIVE_POOL));
        Collections.shuffle(shuffledObjectives);

        // Assign the first available objective
        for (String obj : shuffledObjectives) {
            if (!assignedObjectives.contains(obj)) {
                assignedObjectives.add(obj);
                return obj;
            }
        }

        // If all objectives are assigned, reuse them (optional)
        return shuffledObjectives.get(assignedObjectives.size() % OBJECTIVE_POOL.length);
    }

    // Static method to reset the assigned objectives for a new game
    public static void resetAssignedObjectives() {
        assignedObjectives.clear();
    }

    // Getter for player name
    public String getName() {
        return name;
    }

    // Getter for player position
    public int getPosition() {
        return position;
    }

    // Setter for player position
    public void setPosition(int position) {
        this.position = position;
    }

    // Move the player by a number of steps
    public void move(int steps) {
        int previousPosition = this.position;
        this.position = (this.position + steps) % 40;
        System.out.println(name + " moved from position " + previousPosition + " to " + this.position);
        if (previousPosition > this.position) {
            System.out.println(name + " has passed the starting tile!");
            if (scraps >= 100) {
                passedstartwith100scraps = true;
                System.out.println(name + " has passed start with 100 scraps! ✅");
            } else {
                System.out.println(name + " passed start but does not have enough scraps yet.");
            }
        }
    }

    public List<QuestionTile> getAvailableQuestions() {
        return availableQuestions;
    }

    public void removeQuestion(QuestionTile question) {
        if (availableQuestions != null) {
            availableQuestions.remove(question);
        }
    }
    public void completeTask() {
        if (passedstartwith100scraps && !finished) {
            taskcompleted++;
            System.out.println(name + " task completed: " + objective);
            if (taskcompleted % 2 == 0) {
                System.out.println(name + " has completed a major milestone!");
            } else {
                System.out.println(name + " needs to pass start with 100 scraps.");
            }
        }
    }

    public boolean canCompleteChallenge() {
        return passedstartwith100scraps;
    }

    // Getter for scraps
    public int getTotalScraps() {
        return scraps;
    }

    // Add scraps to the player
    public void addScraps(int amount) {
        scraps += amount;
        System.out.println(name + " gained " + amount + " scraps. Total scraps: " + scraps);
    }

    // Subtract scraps from the player
    public void subtractScraps(int amount) {
        scraps -= amount;
        System.out.println(name + " lost " + amount + " scraps. Total scraps: " + scraps);
    }

    // Getter for water buckets
    public int getWaterBuckets() {
        return waterBuckets;
    }

    // Add water buckets to the player
    public void addWaterBucket(int amount) {
        waterBuckets += amount;
        System.out.println(name + " gained " + amount + " water buckets. Total water buckets: " + waterBuckets);
    }

    // Subtract water buckets from the player
    public void subtractWaterBucket(int amount) {
        waterBuckets -= amount;
        System.out.println(name + " lost " + amount + " water buckets. Total water buckets: " + waterBuckets);
    }

    // Check if the player has finished the game
    public boolean hasFinished() {
        return finished;
    }

    // Setter for finished status
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    // Check if the player has answered a question correctly
    public boolean hasAnsweredQuestionCorrectly() {
        return answeredQuestionCorrectly;
    }

    // Setter for answeredQuestionCorrectly
    public void setAnsweredQuestionCorrectly(boolean answeredQuestionCorrectly) {
        this.answeredQuestionCorrectly = answeredQuestionCorrectly;
    }

    // Check if the player has completed the dice challenge
    public boolean hasCompletedDiceChallenge() {
        return completedDiceChallenge;
    }

    // Setter for completedDiceChallenge
    public void setCompletedDiceChallenge(boolean completedDiceChallenge) {
        this.completedDiceChallenge = completedDiceChallenge;
    }

    public boolean hasCompletedAllTasks() {
        return passedstartwith100scraps && answeredQuestionCorrectly && completedDiceChallenge;
    }

    // Display the player's current status
    public void displayStatus() {
        System.out.println(name + " - Position: " + position + ", Scraps: " + scraps + ", Water Buckets: " + waterBuckets);
    }
}