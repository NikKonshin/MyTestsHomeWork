//package com.nikitakonshin.mytestshomework
//
//import android.content.Context
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.platform.app.InstrumentationRegistry
//import androidx.test.uiautomator.By
//import androidx.test.uiautomator.UiDevice
//import androidx.test.uiautomator.Until
//
//class BaseCons {
//    companion object {
//        internal val uiDevice: UiDevice =
//            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//        internal val context = ApplicationProvider.getApplicationContext<Context>()
//        internal val packageName = context.packageName
//        internal const val TEST_NUMBER_OF_RESULT_UIAUTOMATOR = "Number of results: 674"
//
//        internal fun createView(name: String) =
//            BaseCons.uiDevice.findObject(By.res(BaseCons.packageName, name))
//
//        internal fun createTextView(name: String) =
//            BaseCons.uiDevice.wait(
//                Until.findObject(By.res(BaseCons.packageName, name)),
//                TIMEOUT
//            )
//        internal const val TIMEOUT = 5000L
//
//    }
//}