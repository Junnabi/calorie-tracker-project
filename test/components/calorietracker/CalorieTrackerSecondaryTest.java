package components.calorietracker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for secondary methods of {@code CalorieTracker}.
 */
public class CalorieTrackerSecondaryTest {

    @Test
    public void testIsOverGoalTrue() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.addEntry("pizza", 200, "2026-04-24");

        boolean expected = true;

        assertEquals(expected, test.isOverGoal("2026-04-24", 650));
    }

    @Test
    public void testIsOverGoalFalse() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.addEntry("pizza", 200, "2026-04-24");

        boolean expected = false;

        assertEquals(expected, test.isOverGoal("2026-04-24", 700));
    }

    @Test
    public void testIsBelowGoalTrue() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");

        boolean expected = true;

        assertEquals(expected, test.isBelowGoal("2026-04-24", 600));
    }

    @Test
    public void testIsBelowGoalFalse() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.addEntry("pizza", 200, "2026-04-24");

        boolean expected = false;

        assertEquals(expected, test.isBelowGoal("2026-04-24", 700));
    }

    @Test
    public void testIsOverGoalEmptyDate() {
        CalorieTracker test = new CalorieTracker1();

        boolean expected = false;

        assertEquals(expected, test.isOverGoal("2026-04-24", 0));
    }

    @Test
    public void testIsBelowGoalEmptyDate() {
        CalorieTracker test = new CalorieTracker1();

        boolean expected = true;

        assertEquals(expected, test.isBelowGoal("2026-04-24", 100));
    }

    @Test
    public void testToString() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");

        String expected = "CalorieTracker with 1 entries";

        assertEquals(expected, test.toString());
    }

    @Test
    public void testEqualsSameEntryCount() {
        CalorieTracker test = new CalorieTracker1();
        CalorieTracker expected = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        expected.addEntry("pizza", 200, "2026-04-25");

        assertEquals(true, test.equals(expected));
    }

    @Test
    public void testEqualsDifferentEntryCount() {
        CalorieTracker test = new CalorieTracker1();
        CalorieTracker expected = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");

        assertEquals(false, test.equals(expected));
    }
}