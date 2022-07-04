package com.abarcelo.isbntools;

public class StockManager {

    private final ExternalISBNDataService webService;
    private final ExternalISBNDataService databaseService;
    public StockManager(ExternalISBNDataService webService, ExternalISBNDataService databaseService) {
        this.webService = webService;
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);
        if (book == null) book = webService.lookup(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4))
                .append(book.getAuthor().charAt(0))
                .append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
