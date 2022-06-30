package com.abarcelo.isbntools;

public class StockManager {

    private final ExternalISBNDataService externalISBNDataService;
    public StockManager(ExternalISBNDataService externalISBNDataService) {
        this.externalISBNDataService = externalISBNDataService;
    }

    public String getLocatorCode(String isbn) {
        Book book = externalISBNDataService.lookup(isbn);
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4))
                .append(book.getAuthor().charAt(0))
                .append(book.getTitle().split(" ").length);
        return locator.toString();
    }
}
