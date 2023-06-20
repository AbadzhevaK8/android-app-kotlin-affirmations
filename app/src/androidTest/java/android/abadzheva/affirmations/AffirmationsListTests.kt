package android.abadzheva.affirmations

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AffirmationsListTests {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val disableAnimationsRule: TestRule = DisableAnimationsRule()

    class DisableAnimationsRule : TestWatcher() {
        override fun starting(description: Description) {
            super.starting(description)
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                "settings put global transition_animation_scale 0"
            )
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                "settings put global window_animation_scale 0"
            )
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                "settings put global animator_duration_scale 0"
            )
        }

        override fun finished(description: Description) {
            super.finished(description)
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                "settings put global transition_animation_scale 1"
            )
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                "settings put global window_animation_scale 1"
            )
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                "settings put global animator_duration_scale 1"
            )
        }
    }


    @Test
    fun scroll_to_item(){
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions
                .scrollTo<RecyclerView.ViewHolder>(
                    withText(R.string.affirmation10)
                )
        )

        onView(withText(R.string.affirmation10))
            .check(
                ViewAssertions.matches(isDisplayed())
            )
    }
}