package com.se.riddaradb.dtos;
import java.util.HashSet;
import java.util.Set;

public class BibDto {
        int id;
        String authors;
        String title;
        String editors;
        String book;
        String bookSeries;
        int numOfVolumes;
        String placeOfPublication;
        String publisher;
        String publicationYear;
        String pageNumbers;
        Set<Integer> SagaVersionIds;

        protected BibDto(){
        }

    public BibDto(int id,
                  String authors,
                  String title,
                  String editors,
                  String book,
                  String bookSeries,
                  int numOfVolumes,
                  String placeOfPublication,
                  String publisher,
                  String publicationYear,
                  String pageNumbers) {
        this.id = id;
        this.authors = authors;
        this.title = title;
        this.editors = editors;
        this.book = book;
        this.bookSeries = bookSeries;
        this.numOfVolumes = numOfVolumes;
        this.placeOfPublication = placeOfPublication;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.pageNumbers = pageNumbers;
        SagaVersionIds = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditors() {
        return editors;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getBookSeries() {
        return bookSeries;
    }

    public void setBookSeries(String bookSeries) {
        this.bookSeries = bookSeries;
    }

    public int getNumOfVolumes() {
        return numOfVolumes;
    }

    public void setNumOfVolumes(int numOfVolumes) {
        this.numOfVolumes = numOfVolumes;
    }

    public String getPlaceOfPublication() {
        return placeOfPublication;
    }

    public void setPlaceOfPublication(String placeOfPublication) {
        this.placeOfPublication = placeOfPublication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(String pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public Set<Integer> getSagaVersionIds() {
        return SagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> SagaVersionIds) {
        this.SagaVersionIds = SagaVersionIds;
    }
}
