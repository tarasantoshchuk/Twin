package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.Flag;
import org.junit.Test;

import java.util.ArrayList;

import static com.tarasantoshchuk.twin.files.picking.FilePickerTestUtils.getAllFilesNonRecursive;
import static com.tarasantoshchuk.twin.files.picking.FilePickerTestUtils.getRootFolder;
import static org.junit.Assert.*;

public class NonRecursiveFilePickerTest {

    @Test
    public void testGetAllFiles() throws Exception {
        FilePicker picker = new NonRecursiveFilePicker(getRootFolder(), new ArrayList<Flag.Matcher>());
        assertEquals(getAllFilesNonRecursive(), picker.getAllFiles());
    }
}