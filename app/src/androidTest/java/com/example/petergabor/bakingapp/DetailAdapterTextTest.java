package com.example.petergabor.bakingapp;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.petergabor.bakingapp.TestUtils.withRecyclerView;



/**
 *
 // Convenience helper
 public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
 return new RecyclerViewMatcher(recyclerViewId);
 }

 // Check item at position 3 has "Some content"
 onView(withRecyclerView(R.id.scroll_view).atPosition(3))
 .check(matches(hasDescendant(withText("Some content"))));

 // Click item at position 3
 onView(withRecyclerView(R.id.scroll_view).atPosition(3)).perform(click());
 *
 *
 */
@RunWith(AndroidJUnit4.class)
public class DetailAdapterTextTest {

    public static final String RECIPE_DESC = "Prep the cookie crust.";
    public static final String RECIPE_DETAIL_DESC = "3. Press the cookie crumb mixture into the " +
            "prepared pie pan and bake for 12 minutes. Let crust cool to room temperature.";

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
    public void clickRecyclerViewItem_OpensDetailActivity() {

        // click on nutella pie
        onView(withId(R.id.all_recepts)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // check for first description text
        onView(withRecyclerView(R.id.recepts)
                .atPositionOnView(2, R.id.step_desc))
                .check(matches(withText(RECIPE_DESC)));

        // click on first description text
        onView(withId(R.id.recepts)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        onView((withId(R.id.step_desc))).check(matches(withText(RECIPE_DETAIL_DESC)));

    }


    // Remember to unregister resources when not needed to avoid malfunction.
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }



}
