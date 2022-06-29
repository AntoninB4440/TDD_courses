package com.abarcelo.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorISBNTest {

    @Test
    public void checkValid10DigitISBN(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("0140445927");
        assertTrue(result, "First value");
        result = validator.checkISBN("0140444440");
        assertTrue(result, "Second Value");
    }

    @Test
    public void checkValid13DigitISBN(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("9798838158192");
        assertTrue(result, "First 13 ISBN digit value");
        result = validator.checkISBN("9780140444445");
        assertTrue(result, "Second 13 ISBN digit value");
    }


    @Test
    public void checkAndInvalid10DigitISBN(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("0140445926");
        assertFalse(result);
    }

    @Test
    public void checkAndInvalid13DigitISBN(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("9780140444448");
        assertFalse(result);
    }

    @Test
    public void TenDigitISBNNumberEndingWithAnXAreValid(){
        ValidatorISBN validator = new ValidatorISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result, "ISBN ending with X");
    }

    @Test()
    public void nineDigitISBNsAreNotAllowed(){
        ValidatorISBN validator = new ValidatorISBN();
        assertThrows(NumberFormatException.class,() ->  validator.checkISBN("123456789"));
    }

    @Test()
    public void nonStringNumberISBNsAreNotAllowed(){
        ValidatorISBN validator = new ValidatorISBN();
        assertThrows(NumberFormatException.class,() ->  validator.checkISBN("helloworld"));
    }

}