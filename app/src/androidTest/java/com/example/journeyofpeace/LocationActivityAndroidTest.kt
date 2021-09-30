package com.example.journeyofpeace

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class LocationActivityAndroidTest{

    // This variable is global for all functions created here
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(LocationActivity::class.java)

    // Test if JourneyActivity layout is visible to the user
    @Test
    fun checkActivityVisibility(){

        onView(withId(R.id.layout_location))
            .check(matches(isDisplayed()))

    }

    // Test if text displayed on JourneyActivity is visible to user
    @Test
    fun checkTextVisibility(){

        onView(withId(R.id.journey_activity_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.radio_failte_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.radio_failte_text))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mural_wall_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mural_wall_text))
            .check(matches(isDisplayed()))
        onView(withId(R.id.bobby_sands_title))
            .perform(CustomScrollActions.nestedScrollTo())
            .check(matches(isCompletelyDisplayed()))

        // scroll view can't be reached here
    }

    @Test
    fun textScrollView(){
        onView(withId(R.id.scroll_view)).perform(swipeUp())
    }

    // check if text in view is correct
    @Test
    fun textIsAsExpected(){

        onView(withId(R.id.bobby_sands_title))
            .perform(CustomScrollActions.nestedScrollTo())
            .check(matches(isCompletelyDisplayed()))

    }

}