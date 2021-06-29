package com.nikitakonshin.mytestshomework.auromator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

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
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        Assert.assertNotNull(editText)
    }


    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val searchButton =
            uiDevice.findObject(By.res(packageName, "searchAndToDetailsActivityButton"))
        searchButton.click()

        val changedText = uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )
        Assert.assertEquals(changedText.text.toString(), "Number of results: 42")
    }

    @Test
    fun test_OpenDetailsScreen() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )

        toDetails.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, "Number of results: 0")
    }

    @Test
    fun test_OpenDetailsScreenResult() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val searchButton =
            uiDevice.findObject(By.res(packageName, "searchAndToDetailsActivityButton"))
        searchButton.click()
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )

        searchButton.click()
        toDetails.click(TIMEOUT)

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, "Number of results: 42")
    }

    fun test_OpenDetailsScreenResultNotEquals() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "UiAutomator"
        val searchButton =
            uiDevice.findObject(By.res(packageName, "searchAndToDetailsActivityButton"))
        searchButton.click()
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )

        searchButton.click()
        toDetails.click(TIMEOUT)

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        Assert.assertNotEquals(changedText.text, "Number of results: 0")
    }

    @Test
    fun test_DetailsActivityIncrementButton() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )
        toDetails.click()
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        val incrementButton = uiDevice.findObject(By.res(packageName, "incrementButton"))

        incrementButton.click()
        Assert.assertEquals(changedText.text, "Number of results: 1")
    }
    fun test_DetailsActivityIncrementButton_NotEquals() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )
        toDetails.click()
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        val incrementButton = uiDevice.findObject(By.res(packageName, "incrementButton"))

        incrementButton.click()
        Assert.assertNotEquals(changedText.text, "Number of results: 2")
    }

    @Test
    fun test_DetailsActivityDecrementButton() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )
        toDetails.click()
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        val decrementButton = uiDevice.findObject(By.res(packageName, "decrementButton"))

        for (i in 1..10){
            decrementButton.click()
        }
        Assert.assertEquals(changedText.text, "Number of results: -10")
    }

    @Test
    fun test_DetailsActivityDecrementButton_NotEquals() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, "toDetailsActivityButton")
        )
        toDetails.click()
        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            )

        val incrementButton = uiDevice.findObject(By.res(packageName, "decrementButton"))

        for (i in 1..10){
            incrementButton.click()
        }
        Assert.assertNotEquals(changedText.text, "Number of results: -11")
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}