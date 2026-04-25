package components.calorietracker;

import components.map.Map;
import components.map.Map1L;

/**
 * {@code CalorieTracker} represented by a map from dates to arrays of food
 * records.
 *
 * @convention recordsByDate and dateSizes are not null. Each date has a
 *             corresponding array and size. The size value for each date is
 *             between 0 and the array length. All stored food records are not
 *             null and have calories >= 0.
 *
 * @correspondence Each date maps to a list of food records. Each record
 *                 corresponds to one food entry with a name, calories, and
 *                 date.
 *
 * @author Jun Jin
 */
public class CalorieTracker1 extends CalorieTrackerSecondary {

    private static final class FoodEntry {
        private final String name;
        private final int calories;

        /**
         * Creates a food record.
         *
         * @param name
         *            the food name
         * @param calories
         *            the calories
         */
        private FoodEntry(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }
    }

    private static final int DEFAULT_CAPACITY = 4;

    private Map<String, FoodEntry[]> recordsByDate;
    private Map<String, Integer> dateSizes;

    private void createNewRep() {
        this.recordsByDate = new Map1L<>();
        this.dateSizes = new Map1L<>();
    }

    /*
     * Constructor
     */

    public CalorieTracker1() {
        this.createNewRep();
    }

    /*
     * Standard methods
     */

    @Override
    public final CalorieTracker newInstance() {
        return new CalorieTracker1();
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(CalorieTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof CalorieTracker1 : "Violation of: source is of dynamic type CalorieTracker1";

        CalorieTracker1 localSource = (CalorieTracker1) source;

        this.recordsByDate = localSource.recordsByDate;
        this.dateSizes = localSource.dateSizes;

        localSource.createNewRep();
    }

    /*
     * Kernel methods
     */

    @Override
    public void addEntry(String name, int calories, String date) {
        assert name != null : "Violation of: name is not null";
        assert calories >= 0 : "Violation of: calories >= 0";
        assert date != null : "Violation of: date is not null";

        if (!this.recordsByDate.hasKey(date)) {
            FoodEntry[] recordsForDate = new FoodEntry[DEFAULT_CAPACITY];
            recordsForDate[0] = new FoodEntry(name, calories);

            this.recordsByDate.add(date, recordsForDate);
            this.dateSizes.add(date, 1);
        } else {
            FoodEntry[] recordsForDate = this.recordsByDate.value(date);
            int spaceUsed = this.dateSizes.value(date);

            if (spaceUsed == recordsForDate.length) {
                FoodEntry[] biggerArray = new FoodEntry[recordsForDate.length
                        * 2];
                for (int i = 0; i < recordsForDate.length; i++) {
                    biggerArray[i] = recordsForDate[i];
                }
                recordsForDate = biggerArray;

                this.recordsByDate.remove(date);
                this.recordsByDate.add(date, recordsForDate);
            }

            recordsForDate[spaceUsed] = new FoodEntry(name, calories);

            this.dateSizes.remove(date);
            this.dateSizes.add(date, spaceUsed + 1);
        }
    }

    @Override
    public boolean removeEntry(String name, String date) {
        assert name != null : "Violation of: name is not null";
        assert date != null : "Violation of: date is not null";

        boolean removed = false;

        if (this.recordsByDate.hasKey(date)) {
            FoodEntry[] recordsForDate = this.recordsByDate.value(date);
            int used = this.dateSizes.value(date);

            for (int i = 0; i < used && !removed; i++) {
                if (recordsForDate[i].name.equals(name)) {

                    for (int j = i; j < used - 1; j++) {
                        recordsForDate[j] = recordsForDate[j + 1];
                    }

                    recordsForDate[used - 1] = null;

                    this.dateSizes.remove(date);
                    this.dateSizes.add(date, used - 1);

                    removed = true;
                }
            }
        }

        return removed;
    }

    @Override
    public int totalCaloriesOn(String date) {
        assert date != null : "Violation of: date is not null";

        int sum = 0;

        if (this.recordsByDate.hasKey(date)) {
            FoodEntry[] recordsForDate = this.recordsByDate.value(date);
            int used = this.dateSizes.value(date);

            for (int i = 0; i < used; i++) {
                sum += recordsForDate[i].calories;
            }
        }

        return sum;
    }

    @Override
    public boolean hasEntry(String name, String date) {
        assert name != null : "Violation of: name is not null";
        assert date != null : "Violation of: date is not null";

        boolean found = false;

        if (this.recordsByDate.hasKey(date)) {
            FoodEntry[] entriesForDate = this.recordsByDate.value(date);
            int used = this.dateSizes.value(date);

            for (int i = 0; i < used && !found; i++) {
                if (entriesForDate[i].name.equals(name)) {
                    found = true;
                }
            }
        }

        return found;
    }

    @Override
    public int entryCount() {
        int result = 0;

        Map<String, Integer> temp = this.dateSizes.newInstance();

        while (this.dateSizes.size() > 0) {
            Map.Pair<String, Integer> pair = this.dateSizes.removeAny();

            result += pair.value();
            temp.add(pair.key(), pair.value());
        }

        this.dateSizes.transferFrom(temp);

        return result;
    }

}