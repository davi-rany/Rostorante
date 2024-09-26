package com.example.rostorante.utils;

import java.util.Objects;

public class TestUtils {
    public static void logErrors(String actualResult, String expectation) {
        if (!Objects.equals(actualResult, expectation)) {
            System.out.printf("Expected: \n\t%s\nBut got: \n\t%s%n", expectation, actualResult);
        }
    }
}
