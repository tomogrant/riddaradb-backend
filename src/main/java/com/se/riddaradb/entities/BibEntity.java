package com.se.riddaradb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BIB")
public class BibEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    PublicationType publicationType;

    @NotBlank
    String authors;

    @NotBlank
    String title;

    String editors;

    @NotBlank
    String book;

    String bookSeries;

    int numOfVolumes;

    String placeOfPublication;

    @NotBlank
    String publisher;

    @NotBlank
    String publicationYear;

    String pageNumbers;

    @ManyToMany(mappedBy = "bibEntity")
    Set<SagaEntity> sagaEntity = new HashSet<>();

    protected BibEntity(){
    }

    public BibEntity(int id,
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
    }

    public enum PublicationType {
        JOURNAL_ARTICLE,
        BOOK_CHAPTER,
        BOOK
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

    public Set<SagaEntity> getSagaEntity() {
        return sagaEntity;
    }

    public void setSagaEntity(Set<SagaEntity> sagaEntity) {
        this.sagaEntity = sagaEntity;
    }
}
