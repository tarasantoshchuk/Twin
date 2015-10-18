package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.Flag;
import com.tarasantoshchuk.twin.input.InputArguments;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class FilePickerFactoryTest {

    @Test
    public void testGetFilePickerRecursive() throws Exception {
        FilePicker picker = new FilePickerFactory().getFilePicker(new MockInputArgumentRecursive());

        assertThat(picker, instanceOf(RecursiveFilePicker.class));
    }

    @Test
    public void testGetFilePickerNonRecursive() throws Exception {
        FilePicker picker = new FilePickerFactory().getFilePicker(new MockInputArgumentNonRecursive());

        assertThat(picker, instanceOf(NonRecursiveFilePicker.class));
    }

    private class MockInputArgumentRecursive extends InputArguments {
        @Override
        public List<FlagWithArguments> getFlags() {
            List<FlagWithArguments> result = new ArrayList<>();
            result.add(new FlagWithArguments(Flag.RECURSIVE, new ArrayList<String>()));
            return result;
        }

        @Override
        public boolean hasFlag(Flag flag) {
            return flag == Flag.RECURSIVE;
        }

        @Override
        public File getTargetFolder() {
            return super.getTargetFolder();
        }
    }

    private class MockInputArgumentNonRecursive extends InputArguments {
        @Override
        public List<FlagWithArguments> getFlags() {
            List<FlagWithArguments> result = new ArrayList<>();
            result.add(new FlagWithArguments(Flag.RECURSIVE, new ArrayList<String>()));
            return result;
        }

        @Override
        public boolean hasFlag(Flag flag) {
            return false;
        }

        @Override
        public File getTargetFolder() {
            return super.getTargetFolder();
        }
    }
}