/**
 * Example class using CalorieTracker as representation.
 */
public class DietTracker {

    /**
     * Calorie tracker.
     */
    private CalorieTracker tracker;

    /**
     * Constructor.
     */
    public DietTracker() {
        this.tracker = new CalorieTracker1();
    }

    /**
     * Adds meal.
     *
     * @param name
     *            meal name
     * @param calories
     *            calories
     * @param date
     *            date
     */
    public void addMeal(String name, int calories, String date) {
        this.tracker.addEntry(name, calories, date);
    }

    /**
     * Reports calories for day.
     *
     * @param date
     *            date
     * @return calories
     */
    public int caloriesForDay(String date) {
        return this.tracker.totalCaloriesOn(date);
    }
}