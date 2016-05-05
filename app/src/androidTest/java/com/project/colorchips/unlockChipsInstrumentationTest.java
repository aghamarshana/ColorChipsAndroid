/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.project.colorchips;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.support.test.runner.AndroidJUnitRunner;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 Ui Tests for {@link MainActivity} using the {@link AndroidJUnitRunner}.
 * This class uses the JUnit4 syntax for tests.
 * <p>
 * With the new AndroidJUnit runner you can run both JUnit3 and JUnit4 tests in a single test
 * suite. The {@link AndroidRunnerBuilder} which extends JUnit's
 * {@link AllDefaultPossibilitiesBuilder} will create a single {@link
 * TestSuite} from all tests and run them.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class unlockChipsInstrumentationTest {

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    private  Unlockchips unlocker ;

    @Before
    public void setUp() {

        unlocker = new Unlockchips();
    }



    @Test
    public void Exampleone() {


        String inputval = "blue,green,blue,yellow,orange,red,red,green,yellow,red,orange,red";


        String expected = buildPairs(
                "blue", "yellow",
                "yellow", "red",
                "red", "orange",
                "orange", "red",
                "red", "green");

        performOperation(R.id.submit, inputval, expected);
        //performClearOperation(R.id.clear);
    }



    private void performOperation(int btnOperationResId, String input, String expectedResult) {
        (mActivityRule.getActivity()).setInputstring(input);
        // Type the two operands in the EditText fields
        // Click on a given operation button
        onView(withId(btnOperationResId)).perform(click());
        // Check the expected test is displayed in the Ui
        onView(withId(R.id.resultText)).check(matches(withText(expectedResult)));

    }


    private String buildPairs(String... colors) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < colors.length; i++){
            sb.append(colors[i]);
            //figure out separator by position
            if(i % 2 == 0){
                sb.append(",");
            } else {
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }

    private void performClearOperation(int btnOperationClearId) {
        onView(withId(btnOperationClearId)).perform(click());
    }
}
