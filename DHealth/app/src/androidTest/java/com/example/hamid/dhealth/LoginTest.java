package com.example.hamid.dhealth;

import android.test.suitebuilder.annotation.LargeTest;
import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.Activities.LoginScreen;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    static String valid_email = "test996@gmail.com";
    static String valid_pass = "abc123";

    @Rule
    public ActivityTestRule<LoginScreen> mActivityRule =
            new ActivityTestRule<>(LoginScreen.class);


    @Before
    public void initValidString() {

    }


    @Test
    public void login() {
        // Type text and then press the button.
        onView(withId(R.id.email))
                .perform(typeText(valid_email), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(valid_pass), closeSoftKeyboard());

        // Check that the text was changed.
        onView(withId(R.id.btn_login)).perform(click());
    }


}