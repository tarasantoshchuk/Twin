package com.tarasantoshchuk.twin.input;

import java.io.File;
import java.util.List;

public enum Flag {
    RECURSIVE("-r", 0, Validator.NO_CHECKS_VALIDATOR, new MatcherFactory() {
        @Override
        public Matcher getMatcher(List<String> flagArguments) {
            return new Matcher() {
                @Override
                public boolean isMatch(File file) {
                    return true;
                }
            };
        }
    }),
    MIN_SIZE("-smin", 1, Validator.NUMBER_VALIDATOR, new MatcherFactory() {
        @Override
        public Matcher getMatcher(final List<String> flagArguments) {
            return new Matcher() {
                @Override
                public boolean isMatch(File file) {
                    String minSizeString = flagArguments.get(0);
                    int minSize = Integer.valueOf(minSizeString);
                    return file.length() >= minSize;
                }
            };
        }
    }),
    MAX_SIZE("-smax", 1, Validator.NUMBER_VALIDATOR, new MatcherFactory() {
        @Override
        public Matcher getMatcher(final List<String> flagArguments) {
            return new Matcher() {
                @Override
                public boolean isMatch(File file) {
                    String maxSizeString = flagArguments.get(0);
                    int maxSize = Integer.valueOf(maxSizeString);
                    return file.length() <= maxSize;
                }
            };
        }
    }),
    REGEX("-regex", 1, Validator.NO_CHECKS_VALIDATOR, new MatcherFactory() {
        @Override
        public Matcher getMatcher(final List<String> flagArguments) {
            return new Matcher() {
                @Override
                public boolean isMatch(File file) {
                    String fileName = file.getName();
                    String regExpr = flagArguments.get(0);
                    return fileName.matches(regExpr);
                }
            };
        }
    }),
    SUBSTRING("-n", 1, Validator.NO_CHECKS_VALIDATOR, new MatcherFactory() {
        @Override
        public Matcher getMatcher(final List<String> flagArguments) {
            return new Matcher() {
                @Override
                public boolean isMatch(File file) {
                    String fileName = file.getName();
                    String substring = flagArguments.get(0);
                    return fileName.contains(substring);
                }
            };
        }
    });

    Flag(String flagString, int numOfArguments, Validator validator, MatcherFactory factory) {
        mFlagString = flagString;
        mArgumentsCount = numOfArguments;
        mValidator = validator;
        mFactory = factory;
    }

    private final String mFlagString;
    private final int mArgumentsCount;
    private final Validator mValidator;
    private final MatcherFactory mFactory;

    public String getFlagString() {
        return mFlagString;
    }

    public int getArgumentsCount() {
        return mArgumentsCount;
    }

    public Validator getValidator() {
        return mValidator;
    }

    public Matcher getMatcher(List<String> flagArguments) {
        return mFactory.getMatcher(flagArguments);
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

    public interface Matcher {
        boolean isMatch(File file);
    }

    interface MatcherFactory {
        Matcher getMatcher(List<String> flagArguments);
    }
}
