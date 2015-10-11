package com.tarasantoshchuk.twin.util;

import com.tarasantoshchuk.twin.input.Flag;
import com.tarasantoshchuk.twin.util.exception.InvalidArgumentsException;

public class ErrorHelper {
    public static void missingArguments() {
        System.out.println("Missing input arguments");
        handleInvalidArguments();
    }

    public static void invalidFlagArguments(Flag flag) {
        System.out.println("Invalid arguments for flag " + flag.getFlagString());
        handleInvalidArguments();
    }

    public static void missingFlagArguments(Flag flag) {
        System.out.println("Missing arguments for flag " + flag.getFlagString());
        handleInvalidArguments();
    }

    public static void missingTargetFolder() {
        System.out.println("You must provide path to folder as last argument");
        handleInvalidArguments();
    }

    public static void tooManyArguments() {
        System.out.println("You mustn't place any arguments after target folder arguments");
        handleInvalidArguments();
    }

    public static void pathIsNotDirectory() {
        System.out.println("You provided path that is not path to directory");
        handleInvalidArguments();
    }

    private static void handleInvalidArguments() {
        throw new InvalidArgumentsException();
    }
}
