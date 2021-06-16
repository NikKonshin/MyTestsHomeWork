package com.nikitakonshin.mytestshomework

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nikitakonshin.mytestshomework.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull(){
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed(){
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun editText_NotNull(){
        scenario.onActivity {
            val searchEditText = it.findViewById<EditText>(R.id.searchEditText)
            TestCase.assertNotNull(searchEditText)
        }
    }

    @Test
    fun editText_IsDisplayed(){
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
    }

    @Test
    fun editText_IsCompletelyDisplayed(){
        onView(withId(R.id.searchEditText)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun editText_IsVisible(){
        onView(withId(R.id.searchEditText)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun editText_isFocused(){
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).check(matches(isFocused()))
    }

    @Test
    fun toDetailsActivityButton_NotNull(){
        scenario.onActivity {
            val searchEditText = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(searchEditText)
        }
    }

    @Test
    fun toDetailsActivityButton_IsDisplayed(){
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isDisplayed()))
    }

    @Test
    fun toDetailsActivityButton_IsCompletelyDisplayed(){
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun toDetailsActivityButton_IsVisible(){
        onView(withId(R.id.toDetailsActivityButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun toDetailsActivityButton_ClickButton(){
        onView(withId(R.id.toDetailsActivityButton)).perform(click())
        onView(withId(R.id.details_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun totalCountTextView_NotNull(){
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertNotNull(searchEditText)
        }
    }

    @Test
    fun totalCountTextView_Invisible(){
        onView(withId(R.id.totalCountTextView)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    @Test
    fun totalCountTextView_Visible(){
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
        onView(withId(R.id.totalCountTextView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }

    @After
    fun close() {
        scenario.close()
    }
}