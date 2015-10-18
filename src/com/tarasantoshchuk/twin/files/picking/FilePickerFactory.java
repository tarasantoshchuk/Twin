package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.Flag;
import com.tarasantoshchuk.twin.input.Flag.Matcher;
import com.tarasantoshchuk.twin.input.InputArguments;
import com.tarasantoshchuk.twin.input.InputArguments.FlagWithArguments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePickerFactory {
    public FilePicker getFilePicker(InputArguments arguments) {
        List<FlagWithArguments> flags = arguments.getFlags();
        List<Matcher> matchers = new ArrayList<>();

        for(FlagWithArguments flagWithArgs: flags) {
            Flag flag = flagWithArgs.getFlag();
            List<String> flagArgs = flagWithArgs.getArguments();

            matchers.add(flag.getMatcher(flagArgs));
        }

        File rootFolder = arguments.getTargetFolder();

        if(arguments.hasFlag(Flag.RECURSIVE)) {
            return new RecursiveFilePicker(rootFolder, matchers);
        } else {
            return new NonRecursiveFilePicker(rootFolder, matchers);
        }
    }
}
