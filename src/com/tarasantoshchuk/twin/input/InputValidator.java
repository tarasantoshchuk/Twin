package com.tarasantoshchuk.twin.input;

import com.tarasantoshchuk.twin.util.ErrorHelper;

import java.io.File;

public class InputValidator {
    public InputArguments getInputArguments(String[] args) {
        InputArguments arguments = new InputArguments();

        File targetFolder = new File(args[args.length - 1]);

        if(!targetFolder.isDirectory()) {
            ErrorHelper.handleNotDirectoryError();
        }

        arguments.setTargetFolder(targetFolder);

        return arguments;
    }
}
