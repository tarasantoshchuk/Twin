package com.tarasantoshchuk.twin.input;

import com.tarasantoshchuk.twin.util.ErrorHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InputValidator {
    public InputArguments getInputArguments(String[] args) {
        if(args.length == 0) {
            ErrorHelper.missingArguments();
        }

        InputArguments convertedArguments = new InputArguments();

        int currentTokenIndex;

        for(currentTokenIndex = 0; currentTokenIndex < args.length; currentTokenIndex++) {
            boolean isFlag = false;

            for(Flag flag: Flag.values()) {
                if(args[currentTokenIndex].equals(flag.getFlagString())) {
                    isFlag = true;

                    List<String> flagArgs = new ArrayList<>();

                    int firstFlagArgIndex = currentTokenIndex + 1;
                    int lastFlagArgIndex = currentTokenIndex + flag.getArgumentsCount();
                    int numOfTokensToSkip = flag.getArgumentsCount();

                    if(lastFlagArgIndex > args.length) {
                        ErrorHelper.missingFlagArguments(flag);
                    }

                    for(int j = firstFlagArgIndex; j <= lastFlagArgIndex; j++) {
                        flagArgs.add(args[j]);
                    }

                    if(!flag.getValidator().isValid(flagArgs)) {
                        ErrorHelper.invalidFlagArguments(flag);
                    }

                    convertedArguments.addFlag(flag, flagArgs);

                    currentTokenIndex += numOfTokensToSkip;
                }
            }

            if(!isFlag) {
                break;
            }
        }

        if(currentTokenIndex == args.length) {
            ErrorHelper.missingTargetFolder();
        } else if(currentTokenIndex < args.length - 1) {
            ErrorHelper.tooManyArguments();
        }

        File targetFolder = new File(args[currentTokenIndex]);

        if(!targetFolder.isDirectory()) {
            ErrorHelper.pathIsNotDirectory();
        }

        convertedArguments.setTargetFolder(targetFolder);

        return convertedArguments;
    }
}
