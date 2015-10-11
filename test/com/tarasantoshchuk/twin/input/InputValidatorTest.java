package com.tarasantoshchuk.twin.input;

import com.tarasantoshchuk.twin.input.InputArguments.FlagWithArguments;
import com.tarasantoshchuk.twin.util.exception.InvalidArgumentsException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.tarasantoshchuk.twin.input.Flag.*;
import static org.junit.Assert.*;

public class InputValidatorTest {
    private static final String PATH = "C:";
    private static final String NOT_A_PATH = "c";
    private static final String NUMBER_STRING = "1";
    private static final String NON_NUMBER_STRING = "not number";
    private InputValidator mInputValidator;

    @Before
    public void beforeTest() {
        mInputValidator = new InputValidator();
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testNoArguments() {
        mInputValidator.getInputArguments(new String[]{});
    }


    @Test
    public void testNoFlags() {
        InputArguments args = mInputValidator.getInputArguments(new String[]{PATH});

        for(Flag flag: values()) {
            assertFalse(args.hasFlag(flag));
        }

        assertEquals(PATH, args.getTargetFolder().getPath());
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testMissingFlagArgs() {
        mInputValidator.getInputArguments(
                new String[]{
                        MAX_SIZE.getFlagString(),
                        PATH
                });
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testNotAFolder() {
        mInputValidator.getInputArguments(
                new String[] {
                        NOT_A_PATH
                }
        );
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testBadFlagArgumentValue() {
        mInputValidator.getInputArguments(
                new String[]{
                        MAX_SIZE.getFlagString(),
                        NON_NUMBER_STRING,
                        PATH
                });
    }

    @Test
    public void testFlags() {
        InputArguments arguments = mInputValidator.getInputArguments(
                new String[]{
                        MAX_SIZE.getFlagString(),
                        NUMBER_STRING,
                        RECURSIVE.getFlagString(),
                        SUBSTRING.getFlagString(),
                        NON_NUMBER_STRING,
                        PATH
                });
        List<FlagWithArguments> mFlags = arguments.getFlags();

        assertEquals(MAX_SIZE, mFlags.get(0).getFlag());
        assertEquals(Collections.singletonList(NUMBER_STRING), mFlags.get(0).getArguments());

        assertEquals(RECURSIVE, mFlags.get(1).getFlag());
        assertEquals(Collections.emptyList(), mFlags.get(1).getArguments());

        assertEquals(SUBSTRING, mFlags.get(2).getFlag());
        assertEquals(Collections.singletonList(NON_NUMBER_STRING), mFlags.get(2).getArguments());

        assertEquals(PATH, arguments.getTargetFolder().getPath());
    }
}