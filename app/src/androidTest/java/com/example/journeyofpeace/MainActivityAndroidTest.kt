package com.example.journeyofpeace

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class will test the UI components involved in the [MainActivity] class
 * All Strings will be checked to identify if ID matches text.
 * All buttons tested to trigger correct activity from click and the activity is then visible.
 * This class uses Espresso UI Testing as guided via youtube video link [https://www.youtube.com/watch?v=deXEAAaznVY]
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityAndroidTest {

    // This variable is global for all functions created here
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        Intents.init()
    }

    // Test if MainActivity layout is visible to the user
    @Test
    fun checkActivityVisibility(){

        onView(withId(R.id.layout_main_activity)).check(matches(isDisplayed()))

    }

    // Test if text displayed on MainActivity is visible to user
    @Test
    fun checkTextVisibility(){

        onView(withId(R.id.welcome)).check(matches(isDisplayed()))
        onView(withId(R.id.info_block)).check(matches(isDisplayed()))

    }

    // check if text in view is correct
    @Test
    fun textIsAsExpected(){

        onView(withId(R.id.welcome)).check(matches(withText(R.string.welcome_text)))
        onView(withId(R.id.info_block)).check(matches(withText(R.string.info_block_text)))

    }

    // Test if journey button clicked opens correct activity. Check if activity opens, it is visible
    @Test
    fun navigateToJourneyActivity(){
        onView(withId(R.id.journey)).perform(click())
        onView(withId(R.id.layout_map)).check(matches(isDisplayed()))
    }

    // Test if location button clicked opens correct activity. Check if activity opens, it is visible
    @Test
    fun navigateToLocationActivity(){
        onView(withId(R.id.locations)).perform(click())
        onView(withId(R.id.layout_location)).check(matches(isDisplayed()))
    }

    // Test if Home item clicked opens correct activity. Check if activity opens, it is visible
    @Test
    fun navigationToolbarActivity(){
        onView(withId(R.id.drawerLayout))
            .check(matches(isClosed()))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.Home)).perform(click())
        onView(withId(R.id.layout_main_activity)).check(matches(isDisplayed()))
    }

    // Test if about item clicked opens correct activity. Check if activity opens, it is visible
    @Test
    fun navigationToolbarAboutActivity(){
        onView(withId(R.id.drawerLayout))
            .check(matches(isClosed()))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.about_us)).perform(click())
        onView(withId(R.id.about_activity)).check(matches(isDisplayed()))
    }

    // Test if contact item clicked opens correct activity. Check if activity opens, it is visible
    @Test
    fun navigationToolbarContactActivity(){
        onView(withId(R.id.drawerLayout))
            .check(matches(isClosed()))
            .perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.contact_us)).perform(click())
        onView(withId(R.id.contact_activity)).check(matches(isDisplayed()))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        Intents.release()
    }
}
