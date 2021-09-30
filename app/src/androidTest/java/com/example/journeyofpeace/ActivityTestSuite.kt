package com.example.journeyofpeace

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(AboutActivityAndroidTest::class,
    ContactActivityAndroidTest::class,
    MainActivityAndroidTest::class,
    LocationActivityAndroidTest::class,
    MapActivityAndroidTest::class)
class ActivityTestSuite