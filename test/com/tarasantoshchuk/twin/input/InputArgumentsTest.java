package com.tarasantoshchuk.twin.input;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputArgumentsTest {
    private InputArguments mInArguments;

    @Before
    public void beforeTest() {
        mInArguments = new InputArguments();
    }

    @Test
    public void testHasFlagNoFlags() {
        for(Flag flag: Flag.values()) {
            assertFalse(mInArguments.hasFlag(flag));
        }
    }

    @Test
    public void testHasFlagAddFlag() {
        for(Flag flag: Flag.values()) {
            mInArguments.addFlag(flag, null);
            assertTrue(mInArguments.hasFlag(flag));
        }
    }
}