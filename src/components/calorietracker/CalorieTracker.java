package components.calorietracker;

/**
 * {@code CalorieTrackerKernel} enhanced with secondary methods.
 */
public interface CalorieTracker extends CalorieTrackerKernel {

    /**
     * Reports whether the total calories on a given date are greater than a
     * calorie goal.
     *
     * @param date
     *            the date being checked
     * @param goalCalories
     *            the calorie goal
     * @return true iff the total calories on {@code date} is greater than
     *         {@code goalCalories}
     * @requires date == valid and goalCalories >= 0
     * @ensures isOverGoal = (totalCaloriesOn(date) > goalCalories)
     */
    boolean isOverGoal(String date, int goalCalories);

    /**
     * Reports whether the total calories on a given date are less than a
     * calorie goal.
     *
     * @param date
     *            the date being checked
     * @param goalCalories
     *            the calorie goal
     * @return true iff the total calories on {@code date} is less than
     *         {@code goalCalories}
     * @requires date == valid and goalCalories >= 0
     * @ensures isBelowGoal = (totalCaloriesOn(date) < goalCalories)
     */
    boolean isBelowGoal(String date, int goalCalories);

}