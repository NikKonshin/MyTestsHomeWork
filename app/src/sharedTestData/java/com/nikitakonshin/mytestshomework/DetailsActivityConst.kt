package com.nikitakonshin.mytestshomework

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.nikitakonshin.mytestshomework.BaseCons.Companion.context
import com.nikitakonshin.mytestshomework.BaseCons.Companion.packageName
import com.nikitakonshin.mytestshomework.BaseCons.Companion.uiDevice
import org.junit.runner.RunWith

class DetailsActivityConst {
    companion object {
        internal const val TOTAL_COUNT_TEXT_VIEW = "totalCountTextView"
        internal const val INCREMENT_BUTTON = "incrementButton"
        internal const val DECREMENT_BUTTON = "decrementButton"
        internal const val NUMBER_OF_RESULT_ONE_STRING = "Number of results: 1"
    }
}