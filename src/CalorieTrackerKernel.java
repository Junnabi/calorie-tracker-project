package components.calorietracker;

import components.standard.Standard;

/**
 * Calorie tracker kernel component with primary methods.
 *
 * @mathdefinitions <pre>
 * RECORD is tuple of
 *  (name: string, calories: integer, date: string)
 * </pre>
 *
 * @mathmodel type CalorieTrackerKernel is modeled by string of RECORD
 *
 * @initially <pre>
 * ensures
 *  this = <>
 * </pre>
 */
public interface CalorieTrackerKernel extends Standard<CalorieTracker> {

    /**
     * Adds a food record to {@code this}.
     *
     * @param name
     *            name of the food
     * @param calories
     *            the calories in the food
     * @param date
     *            the date of the record
     * @updates this
     * @requires name == valid, calories >= 0 and date == valid
     * @ensures this = #this * <(name, calories, date)>
     */
    void addRecord(String name, int calories, String date);

    /**
     * Removes the first matching food record with the given name on the given
     * date, if such a record exists.
     *
     * @param name
     *            name of the food to remove
     * @param date
     *            the date from which to remove the record
     * @return true iff a matching record was removed
     * @updates this
     * @requires name == valid and date == valid
     * @ensures <pre>
     * removeRecord = true if a matching record was removed,
     *                false otherwise and
     * this = #this with the first record having
     *        name = name and date = date removed
     * </pre>
     */
    boolean removeRecord(String name, String date);

    /**
     * Reports the total calories recorded on a given date.
     *
     * @param date
     *            the date being checked
     * @return the total calories on {@code date}
     * @ensures totalCaloriesOn = sum of calories of all records in this whose
     *          date = date
     */
    int totalCaloriesOn(String date);

}