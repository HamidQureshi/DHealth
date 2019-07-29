package com.example.hamid.dhealth;

import android.test.suitebuilder.annotation.LargeTest;

import com.example.hamid.dhealth.ui.Activities.LoginScreen;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    static String valid_email = "test996@gmail.com";
    static String invalid_email = "";
    static String valid_pass = "abc123";

    @Rule
    public ActivityTestRule<LoginScreen> mActivityRule =
            new ActivityTestRule<>(LoginScreen.class);


    @Before
    public void initValidString() {

    }


    @Test
    public void login_valid() {
        LoginScreen activity = mActivityRule.getActivity();

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        onView(withId(R.id.email))
                .perform(typeText(valid_email), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(valid_pass), closeSoftKeyboard());

        // Check that the text was changed.
        onView(withId(R.id.btn_login)).perform(click());

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

        onView(withText("Enter email address")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void login_invalid() {
        LoginScreen activity = mActivityRule.getActivity();

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        onView(withId(R.id.email))
                .perform(typeText(invalid_email), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(valid_pass), closeSoftKeyboard());

        // Check that the text was changed.
        onView(withId(R.id.btn_login)).perform(click());

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));

        onView(withText("Enter email address")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }


}