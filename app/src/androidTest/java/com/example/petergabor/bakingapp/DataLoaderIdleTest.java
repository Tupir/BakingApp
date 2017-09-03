package com.example.petergabor.bakingapp;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;


/**
 * Instrumentation DataLoaderIdleTest, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DataLoaderIdleTest {

    @Rule
    public ActivityTestRule<AllRecipesActivity> mActivityTestRule =
            new ActivityTestRule<>(AllRecipesActivity.class);

    private IdlingResource mIdlingResource;


    // Registers any resource that needs to be synchronized with Espresso before the DataLoaderIdleTest is run.
    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the DataLoaderIdleTest fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void idlingResourceTest() {
        onView(ViewMatchers.withId(R.id.all_recepts)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    // Remember to unregister resources when not needed to avoid malfunction.
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }


}
