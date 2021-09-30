package com.example.journeyofpeace

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class AboutActivityAndroidTest{

    // This variable is global for all functions created here
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(AboutActivity::class.java)

    // Test if ContactActivity layout is visible to the user
    @Test
    fun checkActivityVisibility(){

        Espresso.onView(ViewMatchers.withId(R.id.about_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    // Test if text displayed on ContactActivity is visible to user
    @Test
    fun checkTextVisibility() {

        Espresso.onView(ViewMatchers.withId(R.id.about_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.failte_info))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.failte_info1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // Test if text is as expected
    @Test
    fun textIsAsExpected() {

        Espresso.onView(ViewMatchers.withId(R.id.about_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.about_title)))

        Espresso.onView(ViewMatchers.withId(R.id.failte_info))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.failteInfo)))

        Espresso.onView(ViewMatchers.withId(R.id.failte_info1))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.failteInfo1)))

    }

    // Test if drawables are visible to user
    @Test
    fun isDrawableVisible() {

        Espresso.onView(ViewMatchers.withId(R.id.about_logos))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.about_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}