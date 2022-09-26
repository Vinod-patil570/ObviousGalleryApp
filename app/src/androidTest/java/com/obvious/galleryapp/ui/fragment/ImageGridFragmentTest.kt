package com.obvious.galleryapp.ui.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.obvious.galleryapp.R
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageGridFragmentTest {

    private lateinit var scenario: FragmentScenario<ImageGridFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_ObviousGalleryApp)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun scrollGridView() {
        onData(anything()).inAdapterView(withId(R.id.gvImages)).atPosition(3).
        onChildView(withId(R.id.imageView)).perform(click());
    }


}