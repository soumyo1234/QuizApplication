package com.example.quizapplication

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test for SharedPreferences score persistence
 */
@RunWith(AndroidJUnit4::class)
class SharedPreferencesTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        // Clear SharedPreferences before each test
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    @After
    fun tearDown() {
        // Clean up after test
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    @Test
    fun testScoreSaveToSharedPreferences() {
        // Test saving a score
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        
        val testScore = 9
        val testCategory = "SOFTWARE"
        val timestamp = System.currentTimeMillis()
        val scoreEntry = "$testCategory - $testScore/10 - 2026-03-28 10:30:45"
        
        editor.putString("score_$timestamp", scoreEntry)
        editor.apply()
        
        // Verify score was saved
        val savedScore = prefs.getString("score_$timestamp", null)
        assertNotNull("Score should be saved", savedScore)
        assertEquals("Saved score should match", scoreEntry, savedScore)
    }

    @Test
    fun testMultipleScoresSaved() {
        // Test saving multiple scores
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        
        // Save 3 different scores
        editor.putString("score_1", "SOFTWARE - 10/10 - 2026-03-28 10:00:00")
        editor.putString("score_2", "DS - 8/10 - 2026-03-28 11:00:00")
        editor.putString("score_3", "SOFTWARE - 5/10 - 2026-03-28 12:00:00")
        editor.apply()
        
        // Verify all scores are saved
        val all = prefs.all
        assertEquals("Should have 3 scores", 3, all.size)
        
        assertTrue("Should contain score_1", all.containsKey("score_1"))
        assertTrue("Should contain score_2", all.containsKey("score_2"))
        assertTrue("Should contain score_3", all.containsKey("score_3"))
    }

    @Test
    fun testScoreRetrieval() {
        // Test retrieving all saved scores
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        
        val scores = mapOf(
            "score_1000" to "SOFTWARE - 9/10 - 2026-03-28 10:00:00",
            "score_2000" to "DS - 7/10 - 2026-03-28 11:00:00",
            "score_3000" to "SOFTWARE - 6/10 - 2026-03-28 12:00:00"
        )
        
        scores.forEach { (key, value) ->
            editor.putString(key, value)
        }
        editor.apply()
        
        // Retrieve and verify
        val allScores = prefs.all
        scores.forEach { (key, expectedValue) ->
            val actualValue = allScores[key]
            assertEquals("Score should match for $key", expectedValue, actualValue)
        }
    }

    @Test
    fun testScoreClear() {
        // Test clearing all scores
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        
        // Save some scores
        editor.putString("score_1", "SOFTWARE - 10/10 - 2026-03-28 10:00:00")
        editor.putString("score_2", "DS - 8/10 - 2026-03-28 11:00:00")
        editor.apply()
        
        // Verify they exist
        val beforeClear = prefs.all
        assertTrue("Should have scores before clear", beforeClear.isNotEmpty())
        
        // Clear all
        editor.clear().apply()
        
        // Verify they're gone
        val afterClear = prefs.all
        assertTrue("Should be empty after clear", afterClear.isEmpty())
    }

    @Test
    fun testScoreDataPersistence() {
        // Test that data persists across multiple accesses
        val prefs1 = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor1 = prefs1.edit()
        
        editor1.putString("score_persist", "SOFTWARE - 9/10 - 2026-03-28 10:00:00")
        editor1.apply()
        
        // Access SharedPreferences again (simulating app restart)
        val prefs2 = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val retrieved = prefs2.getString("score_persist", null)
        
        assertNotNull("Data should persist", retrieved)
        assertEquals("Data should be intact", "SOFTWARE - 9/10 - 2026-03-28 10:00:00", retrieved)
    }

    @Test
    fun testScoreSortingByValue() {
        // Test that we can retrieve and sort scores
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        
        editor.putString("score_1", "SOFTWARE - 10/10 - 2026-03-28 10:00:00")
        editor.putString("score_2", "DS - 5/10 - 2026-03-28 11:00:00")
        editor.putString("score_3", "SOFTWARE - 8/10 - 2026-03-28 12:00:00")
        editor.apply()
        
        // Retrieve all scores
        val allScores = prefs.all
        val scoreList = allScores.values.filterIsInstance<String>()
        
        // Verify we can sort them
        val sortedScores = scoreList.sortedByDescending { 
            it.substringAfterLast(" ").substringBefore("/").toIntOrNull() ?: 0 
        }
        
        assertTrue("Should have 3 scores", sortedScores.size == 3)
        assertTrue("Highest score should be 10", sortedScores[0].contains("10/10"))
    }

    @Test
    fun testEmptyPreferences() {
        // Test behavior when no scores are saved
        val prefs = context.getSharedPreferences("Scores", Context.MODE_PRIVATE)
        
        val all = prefs.all
        assertTrue("Should be empty initially", all.isEmpty())
        
        val retrieved = prefs.getString("nonexistent", "default")
        assertEquals("Should return default", "default", retrieved)
    }
}
