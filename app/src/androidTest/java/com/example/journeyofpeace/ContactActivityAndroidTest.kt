package com.example.journeyofpeace

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ContactActivityAndroidTest {

    // This variable is global for all functions created here
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(ContactActivity::class.java)

    // Test if ContactActivity layout is visible to the user
    @Test
    fun checkActivityVisibility(){

        onView(ViewMatchers.withId(R.id.contact_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    // Test if text displayed on ContactActivity is visible to user
    @Test
    fun checkTextVisibility(){

        onView(ViewMatchers.withId(R.id.contact_us_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.address_title_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.address_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.email_title_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.email_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.phone_title_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.phone_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.social_media))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    // check if text in view is correct
    @Test
    fun textIsAsExpected(){

        onView(ViewMatchers.withId(R.id.contact_us_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.contact_us_text)))
        onView(ViewMatchers.withId(R.id.address_title_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.address_title)))
        onView(ViewMatchers.withId(R.id.address_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.address_text)))
        onView(ViewMatchers.withId(R.id.email_title_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.email_title)))
        onView(ViewMatchers.withId(R.id.email_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.email_text)))
        onView(ViewMatchers.withId(R.id.phone_title_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.phone_title)))
        onView(ViewMatchers.withId(R.id.phone_text))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.phone_text)))
        onView(ViewMatchers.withId(R.id.social_media))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.social_media_text)))

    }

    // Test to check marker is correct on map and contains appropriate description
    @Test
    fun isMarkerCorrect(){

        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Failte Feirste Thiar"))
        marker.click()

    }
}