package com.example.nativeapps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.nativeapps.ui.login.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorTest {

    private lateinit var stringToBetyped: String
    private lateinit var passwordToBeType: String

    @get:Rule
    var activityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "admin@admin.com"
        passwordToBeType = "admin1"
    }

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.username))
            .perform(typeText(stringToBetyped), closeSoftKeyboard())
        onView(withId(R.id.password))
            .perform(typeText(passwordToBeType), closeSoftKeyboard())

        // Check that the text was changed.
        onView(withId(R.id.username))
            .check(matches(withText(stringToBetyped)))
        onView(withId(R.id.password))
            .check(matches(withText(passwordToBeType)))
    }
}
