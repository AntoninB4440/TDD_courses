package com.abarcelo.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StockManagerTest {

    @Test
    public void canGetACorrectLocatorCode(){
        /*ExternalISBNDataService externalISBNDataService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn,"The Iliad","Homer");
            }
        };*/

        ExternalISBNDataService externalISBNDataService = isbn -> new Book(isbn,"The Iliad","Homer");

        String isbn = "0140445927"; //The Iliad Book by Homer
        StockManager stockmanager = new StockManager(externalISBNDataService);
        String locatorCode = stockmanager.getLocatorCode(isbn);
        assertEquals("5927H2", locatorCode);
    }
}
