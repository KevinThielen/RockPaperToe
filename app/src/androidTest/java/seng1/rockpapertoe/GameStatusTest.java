package seng1.rockpapertoe;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Andre Tegeder & Antonios Kouklidis
 * @author Andre Tegeder
 * @author Antonios Kouklidis
 */
@RunWith(AndroidJUnit4.class)
public class GameStatusTest {
    @Rule
    public ActivityTestRule<GameStatusListActivity> activityTestRule = new ActivityTestRule<GameStatusListActivity>(GameStatusListActivity.class);

    @Test
    public void start() {
        //Click on Button Start in GameStatusListActivity
        onView(withId(R.id.buttonStartGame)).perform(click());
        //Check if the button is still displayed after clicking him
        onView(withId(R.id.buttonStartGame)).check(matches(isDisplayed()));


    }
}