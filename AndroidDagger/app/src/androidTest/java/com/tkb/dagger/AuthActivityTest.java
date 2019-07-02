package com.tkb.dagger;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.tkb.dagger.ui.auth.AuthActivity;
import com.tkb.dagger.ui.main.MainActivity;
import com.tkb.dagger.ui.main.profile.ProfileFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AuthActivityTest {

    @Rule
    public IntentsTestRule<AuthActivity> intentsTestRule = new IntentsTestRule<>(AuthActivity.class);

    @Test
    public void testToolbarImageView_shouldHaveContentDescription() {
        onView(withId(R.id.login_logo))
                .check(matches(withContentDescription(R.string.auth_logo)));
    }

    @Test
    public void testLoginClick_shouldOpenProfileFragment() {

        onView(withId(R.id.user_id_input)).perform(typeText("3"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button))
                .perform(click());


        String templatePictureActivityClassName = MainActivity.class.getName();
        IdlingRegistry.getInstance().register(new WaitActivityIsResumedIdlingResource(templatePictureActivityClassName));

        intended(hasComponent(hasClassName(templatePictureActivityClassName)));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //onView(withText("Logout")).perform(click());
        //pressBack();
       /* String loginActivityClassName = AuthActivity.class.getName();

        intended(hasComponent(hasClassName(loginActivityClassName)));*/


    }

    @Test
    public void testLogout() {

        onView(withId(R.id.user_id_input)).perform(typeText("3"));
        closeSoftKeyboard();
        onView(withId(R.id.login_button))
                .perform(click());


        String templatePictureActivityClassName = MainActivity.class.getName();
        IdlingRegistry.getInstance().register(new WaitActivityIsResumedIdlingResource(templatePictureActivityClassName));

        intended(hasComponent(hasClassName(templatePictureActivityClassName)));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Logout")).perform(click());
        String loginActivityClassName = AuthActivity.class.getName();

        intended(hasComponent(hasClassName(loginActivityClassName)));
    }
}
