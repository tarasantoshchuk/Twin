package com.tarasantoshchuk.twin;

import com.tarasantoshchuk.twin.files.comparing.FileComparator;
import com.tarasantoshchuk.twin.files.picking.FilePicker;
import com.tarasantoshchuk.twin.files.picking.FilePickerFactory;
import com.tarasantoshchuk.twin.input.InputArguments;
import com.tarasantoshchuk.twin.input.InputValidator;
import com.tarasantoshchuk.twin.util.OutputManager;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputValidator validator = new InputValidator();
        InputArguments arguments = validator.getInputArguments(args);

        FilePickerFactory factory = new FilePickerFactory();
        FilePicker picker = factory.getFilePicker(arguments);

        FileComparator comparator = new FileComparator();
        List<List<File>> duplicates = comparator.getDuplicates(picker.getFiles());

        OutputManager.printResult(duplicates);
    }
}
