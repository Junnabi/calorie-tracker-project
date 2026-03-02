import components.map.Map;
import components.map.Map1L;

public class CalorieTracker {

    private static class FoodRecord {
        private final String name;
        private final int calories;

        FoodRecord(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }

        public String getName() {
            return this.name;
        }

        public int getCalories() {
            return this.calories;
        }
    }

    private Map<String, FoodRecord[]> recordsByDate;
    private Map<String, Integer> dateSizes;

    private static final int DEFAULT_CAPACITY = 4;

    private void createNewRep() {
        this.recordsByDate = new Map1L<>();
        this.dateSizes = new Map1L<>();
    }

    public CalorieTracker() {
        this.createNewRep();
    }

    public void addRecord(String name, int calories, String date) {

        if (!this.recordsByDate.hasKey(date)) {
            FoodRecord[] recordsForDate = new FoodRecord[DEFAULT_CAPACITY];
            recordsForDate[0] = new FoodRecord(name, calories);

            this.recordsByDate.add(date, recordsForDate);
            this.dateSizes.add(date, 1);
        } else {
            FoodRecord[] recordsForDate = this.recordsByDate.value(date);
            int spaceUsed = this.dateSizes.value(date);

            if (spaceUsed == recordsForDate.length) {
                FoodRecord[] biggerArray = new FoodRecord[recordsForDate.length
                        * 2];
                for (int i = 0; i < recordsForDate.length; i++) {
                    biggerArray[i] = recordsForDate[i];
                }
                recordsForDate = biggerArray;

                this.recordsByDate.remove(date);
                this.recordsByDate.add(date, recordsForDate);
            }

            recordsForDate[spaceUsed] = new FoodRecord(name, calories);

            this.dateSizes.remove(date);
            this.dateSizes.add(date, spaceUsed + 1);
        }
    }

    public boolean removeRecord(String name, String date) {

        boolean removed = false;

        if (this.recordsByDate.hasKey(date)) {
            FoodRecord[] recordsForDate = this.recordsByDate.value(date);
            int used = this.dateSizes.value(date);

            for (int i = 0; i < used && !removed; i++) {
                if (recordsForDate[i].getName().equals(name)) {

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

    public int totalCaloriesOn(String date) {

        int sum = 0;

        if (this.recordsByDate.hasKey(date)) {
            FoodRecord[] recordsForDate = this.recordsByDate.value(date);
            int used = this.dateSizes.value(date);

            for (int i = 0; i < used; i++) {
                sum += recordsForDate[i].getCalories();
            }
        }

        return sum;
    }

    public boolean isOverGoal(String date, int goalCalories) {

        boolean result = false;
        int total = this.totalCaloriesOn(date);

        if (total > goalCalories) {
            result = true;
        }

        return result;
    }

    public boolean isBelowGoal(String date, int goalCalories) {

        boolean result = false;
        int total = this.totalCaloriesOn(date);

        if (total < goalCalories) {
            result = true;
        }

        return result;
    }

    public static void main(String[] args) {

        CalorieTracker tracker = new CalorieTracker();

        String today = "2026-02-26";
        String yesterday = "2026-02-25";
        int dailyCalorieGoal = 1800;

        tracker.addRecord("Breakfast - Oatmeal", 350, today);
        tracker.addRecord("Snack - Banana", 100, today);
        tracker.addRecord("Lunch - Chicken Salad", 500, today);
        tracker.addRecord("Dinner - Pasta", 700, today);

        tracker.addRecord("Lunch - Burrito", 800, yesterday);
        tracker.addRecord("Snack - Chips", 200, yesterday);

        int todayTotal = tracker.totalCaloriesOn(today);
        System.out.println("Date: " + today);
        System.out.println("Total calories: " + todayTotal + " kcal");
        System.out.println("Daily goal: " + dailyCalorieGoal + " kcal");
        System.out.println("Over goal today? "
                + tracker.isOverGoal(today, dailyCalorieGoal));
        System.out.println("Below goal today? "
                + tracker.isBelowGoal(today, dailyCalorieGoal));
        System.out.println();

        int yesterdayTotal = tracker.totalCaloriesOn(yesterday);
        System.out.println("Date: " + yesterday);
        System.out.println("Total calories: " + yesterdayTotal + " kcal");
        System.out.println();

        System.out
                .println("Removing 'Snack - Chips' from " + yesterday + "...");
        boolean removed = tracker.removeRecord("Snack - Chips", yesterday);
        System.out.println("Removed? " + removed);

        int newYesterdayTotal = tracker.totalCaloriesOn(yesterday);
        System.out.println("New total for " + yesterday + ": "
                + newYesterdayTotal + " kcal");
        System.out.println();
    }
}