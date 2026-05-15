package com.se.riddaradb.dtos;
import com.se.riddaradb.entities.BibEntity;

import java.util.HashSet;
import java.util.Set;

public class BibDto {
        int id;
        BibEntity.PublicationType publicationType;
        String authors;
        String editors;
        String translators;
        String title;
        String url;
        String bookEditors;
        String book;
        String bookSeries;
        String volume;
        String numOfVolumes;
        String placeOfPublication;
        String publisher;
        String publicationYear;
        String pageNumbers;
        Boolean recommended;
        String description;
        Set<Integer> SagaVersionIds;

        protected BibDto(){
        }

    public BibDto(int id,
                  BibEntity.PublicationType publicationType,
                  String authors,
                  String editors,
                  String translators,
                  String title,
                  String url,
                  String bookEditors,
                  String book,
                  String bookSeries,
                  String volume,
                  String numOfVolumes,
                  String placeOfPublication,
                  String publisher,
                  String publicationYear,
                  String pageNumbers,
                  Boolean recommended,
                  String description) {
        this.id = id;
        this.publicationType = publicationType;
        this.authors = authors;
        this.title = title;
        this.url = url;
        this.editors = editors;
        this.translators = translators;
        this.bookEditors = bookEditors;
        this.book = book;
        this.bookSeries = bookSeries;
        this.volume = volume;
        this.numOfVolumes = numOfVolumes;
        this.placeOfPublication = placeOfPublication;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.pageNumbers = pageNumbers;
        this.recommended = recommended;
        this.description = description;
        SagaVersionIds = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BibEntity.PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(BibEntity.PublicationType publicationType) {
        this.publicationType = publicationType;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getEditors() {
        return editors;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getTranslators() {
        return translators;
    }

    public void setTranslators(String translators) {
        this.translators = translators;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBookEditors() {
        return bookEditors;
    }

    public void setBookEditors(String bookEditors) {
        this.bookEditors = bookEditors;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumOfVolumes() {
        return numOfVolumes;
    }

    public void setNumOfVolumes(String numOfVolumes) {
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

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Integer> getSagaVersionIds() {
        return SagaVersionIds;
    }

    public void setSagaVersionIds(Set<Integer> sagaVersionIds) {
        SagaVersionIds = sagaVersionIds;
    }
}
