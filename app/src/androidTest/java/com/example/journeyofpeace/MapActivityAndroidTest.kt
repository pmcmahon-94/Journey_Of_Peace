package com.example.journeyofpeace

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MapActivityAndroidTest {

    // This variable is global for all functions created here
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MapActivity::class.java)

    // Test if ContactActivity layout is visible to the user
    @Test
    fun checkActivityVisibility(){

        Espresso.onView(ViewMatchers.withId(R.id.layout_map))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    // The following tests to check marker is correct on map and contains appropriate description
    @Test
    fun isRadioFailteMarkerCorrect(){

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Radio Failte"))
        marker.click()

    }

    @Test
    fun isSandsMuralMarkerCorrect(){

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Bobby Sands Mural"))
        marker.click()

    }

    @Test
    fun isClonardMarkerCorrect(){

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Clonard Monastery"))
        marker.click()

    }

    @Test
    fun isSolidarityWallMarkerCorrect(){

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Solidarity Wall"))
        marker.click()

    }

    @Test
    fun isConnollyCentreMarkerCorrect(){

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Aras Ui Chongaile"))
        marker.click()

    }

    @Test
    fun isMilltownMarkerCorrect(){

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Milltown Cemetery"))
        marker.click()

    }

}