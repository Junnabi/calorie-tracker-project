package components.calorietracker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for {@code CalorieTracker1}.
 */
public class CalorieTracker1Test {

    @Test
    public void testInitialEntryCount() {
        CalorieTracker test = new CalorieTracker1();

        assertEquals(0, test.entryCount());
    }

    @Test
    public void testAddEntryOne() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");

        assertEquals(1, test.entryCount());
        assertEquals(true, test.hasEntry("hamburger", "2026-04-24"));
        assertEquals(500, test.totalCaloriesOn("2026-04-24"));
    }

    @Test
    public void testAddEntryMultipleSameDate() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.addEntry("pizza", 200, "2026-04-24");

        assertEquals(2, test.entryCount());
        assertEquals(700, test.totalCaloriesOn("2026-04-24"));
    }

    @Test
    public void testAddEntryDifferentDates() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.addEntry("chicken", 335, "2026-04-25");

        assertEquals(2, test.entryCount());
        assertEquals(500, test.totalCaloriesOn("2026-04-24"));
        assertEquals(335, test.totalCaloriesOn("2026-04-25"));
    }

    @Test
    public void testRemoveEntryExisting() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.addEntry("pizza", 200, "2026-04-24");

        boolean expected = true;
        boolean result = test.removeEntry("hamburger", "2026-04-24");

        assertEquals(expected, result);
        assertEquals(1, test.entryCount());
        assertEquals(false, test.hasEntry("hamburger", "2026-04-24"));
        assertEquals(200, test.totalCaloriesOn("2026-04-24"));
    }

    @Test
    public void testRemoveEntryNotExisting() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");

        boolean expected = false;
        boolean result = test.removeEntry("pizza", "2026-04-24");

        assertEquals(expected, result);
        assertEquals(1, test.entryCount());
        assertEquals(500, test.totalCaloriesOn("2026-04-24"));
    }

    @Test
    public void testClear() {
        CalorieTracker test = new CalorieTracker1();

        test.addEntry("hamburger", 500, "2026-04-24");
        test.clear();

        assertEquals(0, test.entryCount());
        assertEquals(0, test.totalCaloriesOn("2026-04-24"));
    }

    @Test
    public void testNewInstance() {
        CalorieTracker test = new CalorieTracker1();
        CalorieTracker expected = test.newInstance();

        assertEquals(0, expected.entryCount());
    }

    @Test
    public void testTransferFrom() {
        CalorieTracker test = new CalorieTracker1();
        CalorieTracker expected = new CalorieTracker1();

        expected.addEntry("hamburger", 500, "2026-04-24");
        test.transferFrom(expected);

        assertEquals(1, test.entryCount());
        assertEquals(500, test.totalCaloriesOn("2026-04-24"));
        assertEquals(0, expected.entryCount());
    }
}