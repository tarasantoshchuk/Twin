package com.tarasantoshchuk.twin.input;

import java.util.List;

public enum Flag {
    RECURSIVE("-r", 0, Validator.NO_CHECKS_VALIDATOR),
    MIN_SIZE("-smin", 1, Validator.NUMBER_VALIDATOR),
    MAX_SIZE("-smax", 1, Validator.NUMBER_VALIDATOR),
    REGEX("-regex", 1, Validator.NO_CHECKS_VALIDATOR),
    SUBSTRING("-n", 1, Validator.NO_CHECKS_VALIDATOR);

    Flag(String flagString, int numOfArguments, Validator validator) {
        mFlagString = flagString;
        mArgumentsCount = numOfArguments;
        mValidator = validator;
    }

    private final String mFlagString;
    private final int mArgumentsCount;
    private final Validator mValidator;

    public String getFlagString() {
        return mFlagString;
    }

    public int getArgumentsCount() {
        return mArgumentsCount;
    }

    public Validator getValidator() {
        return mValidator;
    }

    interface Validator {
        boolean isValid(List<String> flagArguments);

        Validator NO_CHECKS_VALIDATOR = new Validator() {
            @Override
            public boolean isValid(List<String> flagArguments) {
                return true;
            }
        };

        Validator NUMBER_VALIDATOR = new Validator() {
            @Override
            public boolean isValid(List<String> flagArguments) {
                String numberToCheck = flagArguments.get(0);

                return numberToCheck.matches("\\d+");
            }
        };
    }
}
