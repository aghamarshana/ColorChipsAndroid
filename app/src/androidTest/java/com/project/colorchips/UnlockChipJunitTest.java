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

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * JUnit4 unit tests for the calculator logic.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class UnlockChipJunitTest {

    private  Unlockchips unlocker ;

    @Before
    public void setUp() {
        unlocker = new Unlockchips();
    }

    @Test public void exampleOne(){
        ChipsInput input = buildInput("blue", "green", "blue", "yellow", "red", "orange", "red", "green", "yellow", "red","orange", "purple");

        unlocker.unlock(input);
        String expected = unlocker.UNLOCK_FAILED_MSG;
        assertThat(expected, is(equalTo(unlocker.getResult())));

     }

    @Test public void exampleTwo() {
        ChipsInput input = buildInput(
                "blue", "green",
                "blue", "yellow",
                "orange", "red",
                "red", "green",
                "yellow", "red",
                "orange", "red");

        String expected = buildPairs(
                "blue", "yellow",
                "yellow", "red",
                "red", "orange",
                "orange", "red",
                "red", "green");

        unlocker.unlock(input);
        assertThat(expected, is(equalTo(unlocker.getResult())));
    }
     @Test public void example3(){
        ChipsInput input = buildInput(
                "blue", "red",
                "red", "blue",
                "orange", "red",
                "green", "green",
                "orange", "yellow",
                "red", "yellow");
        unlocker.unlock(input);


        assertThat(Unlockchips.UNLOCK_FAILED_MSG, is(equalTo(unlocker.getResult())));

     }

    private ChipsInput buildInput(String... colors) {
        ChipsInput input = new ChipsInput();
        input.parse(buildPairs(colors));
        return input;
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

}
