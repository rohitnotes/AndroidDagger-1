package com.tkb.dagger;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.tkb.dagger.ui.auth.AuthActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AuthActivityTest {

    @Rule
    public IntentsTestRule<AuthActivity> intentsTestRule = new IntentsTestRule<>(AuthActivity.class);

    @Test
    public void testToolbarImageView_shouldHaveContentDescription() {
        onView(withId(R.id.login_logo))
                .check(matches(withContentDescription(R.string.auth_logo)));
    }

}
