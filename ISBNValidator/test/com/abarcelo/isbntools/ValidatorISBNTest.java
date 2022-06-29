package com.abarcelo.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorISBNTest {

    @Test
    public void checkValidISBN(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("0140445927");
        assertTrue(result, "First value");
        result = validator.checkISBN("0140444440");
        assertTrue(result, "Second Value");
    }

    @Test
    public void checkAndInvalidISBN(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("0140445926");
        assertFalse(result);
    }

}