package com.abarcelo.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StockManagerTest {

    @Test
    public void canGetACorrectLocatorCode(){
        /*ExternalISBNDataService externalISBNDataService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn,"The Iliad","Homer");
            }
        };*/

        ExternalISBNDataService webService = isbn -> new Book(isbn,"The Iliad","Homer");
        ExternalISBNDataService databaseService = isbn -> null;
        String isbn = "0140445927"; //The Iliad Book by Homer
        StockManager stockmanager = new StockManager(webService, databaseService);
        String locatorCode = stockmanager.getLocatorCode(isbn);
        assertEquals("5927H2", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140445927"))
                .thenReturn(new Book("0140445927","ABC","ABC"));

        String isbn = "0140445927"; //The Iliad Book by Homer
        StockManager stockmanager = new StockManager(webService, databaseService);
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(databaseService).lookup("0140445927");
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDB(){
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

        when(databaseService.lookup("0140445927"))
                .thenReturn(null);

        when(webService.lookup("0140445927"))
                .thenReturn(new Book("0140445927","ABC","ABC"));

        String isbn = "0140445927"; //The Iliad Book by Homer
        StockManager stockmanager = new StockManager(webService, databaseService);
        String locatorCode = stockmanager.getLocatorCode(isbn);

        verify(databaseService).lookup("0140445927");
        verify(webService, times(1)).lookup("0140445927");
    }
}
