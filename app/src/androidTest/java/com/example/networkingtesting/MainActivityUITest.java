package com.example.networkingtesting;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityUITest {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);


    private static final String BODY = "{ \"login\" : \"xxx\", \"followers\" : 123 }";
    private final MockWebServer server = new MockWebServer();

    @Before
    public void setBaseUrl() throws IOException {
        TestDemoApplication app = (TestDemoApplication)
                InstrumentationRegistry.getTargetContext().getApplicationContext();

        server.start();
        app.setBaseUrl(server.url("/").toString());
    }

    @After
    public void shutdown() throws IOException {
        server.shutdown();
    }

    @Test
    public void success_with_realAPI_and_IdleResource() {
        // Stub data from API
        server.enqueue(new MockResponse().setBody(BODY));

        // Start Activity
        activityTestRule.launchActivity(null);


        IdlingResource idlingResource = OkHttp3IdlingResource.create(
                "okhttp", OkHttp.getInstance());
        IdlingRegistry.getInstance().register(idlingResource);

        onView(withId(R.id.followers))
                .check(matches(withText("xxx: 123")));

        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}