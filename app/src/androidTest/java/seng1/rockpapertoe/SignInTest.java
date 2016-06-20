package seng1.rockpapertoe;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Andre Tegeder & Antonios Kouklidis
 * @author Andre Tegeder
 * @author Antonios Kouklidis
 */
@RunWith(AndroidJUnit4.class)
public class SignInTest {
    @Rule
    public ActivityTestRule<StartActivity> activityTestRule = new ActivityTestRule<StartActivity>(StartActivity.class);

    @Test
    public void play() {
    //Click on Button Play in Start Activity
    onView(withId(R.id.play)).perform(click());
    //Check if the Button disappears
    onView(withId(R.id.play)).check(doesNotExist());
    //Check if the View StarActivity is closed after pressing play button
    onView(withId(R.layout.activity_start)).check(doesNotExist());

    }
}
