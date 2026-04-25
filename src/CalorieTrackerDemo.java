/**
 * Demo for CalorieTracker.
 */
public final class CalorieTrackerDemo {

    /**
     * No argument constructor.
     */
    private CalorieTrackerDemo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        CalorieTracker tracker = new CalorieTracker1();

        tracker.addEntry("hamburger", 500, "2026-04-24");
        tracker.addEntry("pizza", 200, "2026-04-24");
        tracker.addEntry("chicken", 335, "2026-04-25");

        System.out.println("Entries: " + tracker.entryCount());
        System.out.println("Calories on 2026-04-24: "
                + tracker.totalCaloriesOn("2026-04-24"));
        System.out.println(
                "Is over goal? " + tracker.isOverGoal("2026-04-24", 650));
    }
}