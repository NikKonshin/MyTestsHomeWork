package com.nikitakonshin.mytestshomework.auromator

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.nikitakonshin.mytestshomework.BaseCons.Companion.TEST_NUMBER_OF_RESULT_UIAUTOMATOR
import com.nikitakonshin.mytestshomework.BaseCons.Companion.TIMEOUT
import com.nikitakonshin.mytestshomework.BaseCons.Companion.context
import com.nikitakonshin.mytestshomework.BaseCons.Companion.createTextView
import com.nikitakonshin.mytestshomework.BaseCons.Companion.createView
import com.nikitakonshin.mytestshomework.BaseCons.Companion.packageName
import com.nikitakonshin.mytestshomework.BaseCons.Companion.uiDevice
import com.nikitakonshin.mytestshomework.DetailsActivityConst
import com.nikitakonshin.mytestshomework.MainActivityConst
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {
    @Before
    fun setUp() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = createView(MainActivityConst.SEARCH_EDIT_TEXT)
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_SearchIsPositive() {
        val editText = createView(MainActivityConst.SEARCH_EDIT_TEXT)
        editText.text = "UiAutomator"
        val searchButton = createView(MainActivityConst.SEARCH_BUTTON)
        searchButton.click()
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        Assert.assertEquals(changedText.text.toString(), TEST_NUMBER_OF_RESULT_UIAUTOMATOR)
    }

    @Test
    fun test_OpenDetailsScreen() {
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        toDetails.click()
        val changedText = createTextView(MainActivityConst.TOTAL_COUNT_TEXT_VIEW)
        Assert.assertEquals(changedText.text, MainActivityConst.NUMBER_OF_RESULT_ZERO_STRING)
    }

    @Test
    fun test_OpenDetailsScreenResult() {
        val editText = createView(MainActivityConst.SEARCH_EDIT_TEXT)
        editText.text = "UiAutomator"
        val searchButton = createView(MainActivityConst.SEARCH_BUTTON)
        searchButton.click()
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        searchButton.click()
        toDetails.click(TIMEOUT)
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULT_UIAUTOMATOR)
    }

    fun test_OpenDetailsScreenResultNotEquals() {
        val editText = createView(MainActivityConst.SEARCH_EDIT_TEXT)
        editText.text = "UiAutomator"
        val searchButton = createView(MainActivityConst.SEARCH_BUTTON)
        searchButton.click()
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        searchButton.click()
        toDetails.click(TIMEOUT)
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        Assert.assertNotEquals(changedText.text, MainActivityConst.NUMBER_OF_RESULT_ZERO_STRING)
    }

    @Test
    fun test_DetailsActivityIncrementButton() {
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        toDetails.click()
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        val incrementButton = createView(DetailsActivityConst.INCREMENT_BUTTON)
        incrementButton.click()
        Assert.assertEquals(changedText.text, DetailsActivityConst.NUMBER_OF_RESULT_ONE_STRING)
    }

    fun test_DetailsActivityIncrementButton_NotEquals() {
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        toDetails.click()
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        val incrementButton = createView(DetailsActivityConst.INCREMENT_BUTTON)
        incrementButton.click()
        Assert.assertNotEquals(changedText.text, "Number of results: 2")
    }

    @Test
    fun test_DetailsActivityDecrementButton() {
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        toDetails.click()
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        val decrementButton = createView(DetailsActivityConst.DECREMENT_BUTTON)
        for (i in 1..10) {
            decrementButton.click()
        }
        Assert.assertEquals(changedText.text, "Number of results: -10")
    }

    @Test
    fun test_DetailsActivityDecrementButton_NotEquals() {
        val toDetails: UiObject2 = createView(MainActivityConst.TO_DETAILS_ACTIVITY_BUTTON)
        toDetails.click()
        val changedText = createTextView(DetailsActivityConst.TOTAL_COUNT_TEXT_VIEW)
        val incrementButton = createView(DetailsActivityConst.INCREMENT_BUTTON)
        for (i in 1..10) {
            incrementButton.click()
        }
        Assert.assertNotEquals(changedText.text, "Number of results: -11")
    }


}