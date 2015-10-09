package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.InputArguments;

public class FilePickerFactory {
    public FilePicker getFilePicker(InputArguments arguments) {
        return new NonRecursiveFilePicker(arguments.getTargetFolder());
    }
}
