package components.calorietracker;

/**
 * Layered implementations of secondary methods for {@code CalorieTracker}.
 */

public abstract class CalorieTrackerSecondary implements CalorieTracker {
    /*
     * Public members ---------------------------------------------------------
     */

    /*
     * Common methods (from Object) -------------------------------------------
     */

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean isOverGoal(String date, int goalCalories) {
        assert date != null : "Violation of: date is not null";
        assert goalCalories >= 0 : "Violation of: goalCalories >= 0";

        int total = this.totalCaloriesOn(date);
        boolean isOver = false;

        if (total > goalCalories) {
            isOver = true;
        }

        return isOver;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public boolean isBelowGoal(String date, int goalCalories) {
        assert date != null : "Violation of: date is not null";
        assert goalCalories >= 0 : "Violation of: goalCalories >= 0";

        int total = this.totalCaloriesOn(date);
        boolean isBelow = false;

        if (total < goalCalories) {
            isBelow = true;
        }

        return isBelow;
    }

}