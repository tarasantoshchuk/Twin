package com.tarasantoshchuk.twin.util;

public class ErrorHelper {
    public static void handleNotDirectoryError() {
        System.out.println("Provided path is not a directory");
        System.exit(0);
    }
}
