package com.example.networkingtesting;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void success_with_realAPI() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.followers))
                .check(matches(withText("up1: 468")));
    }

    @Test
    public void success_with_realAPI_and_IdleResource() {
        IdlingResource idlingResource = OkHttp3IdlingResource.create(
                "okhttp", OkHttp.getInstance());
        IdlingRegistry.getInstance().register(idlingResource);

        onView(withId(R.id.followers))
                .check(matches(withText("up1: 468")));

        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}