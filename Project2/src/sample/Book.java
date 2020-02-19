package sample;

import javafx.beans.property.SimpleStringProperty;

public class Book {
    private SimpleStringProperty Price;
    private SimpleStringProperty BookISBN;
    private SimpleStringProperty Author;

    private SimpleStringProperty title;
    private SimpleStringProperty edition;
    private SimpleStringProperty publisher;
    private SimpleStringProperty publish_Date;


    Book(    String price,
             String BooKISBN,
             String Author,
             String title,
             String edition,
             String publisher,
             String publish_Date
             ){

        this.setPrice(price);
        this.setBookISBN(BooKISBN);
        this.setAuthor(Author);
        this.setTitle(title);
        this.setEdition(edition);
        this.setPublisher(publisher);
        this.setPrice(getPrice());
        this.setPublish_Date(publish_Date);

    }


    public String getPrice() {
        return Price.get();
    }

    public void setPrice(String price) {
        Price = new SimpleStringProperty(price);
    }

    public String getBookISBN() {
        return BookISBN.get();
    }

    public void setBookISBN(String bookISBN) {
        BookISBN = new SimpleStringProperty(bookISBN);
    }

    public String getAuthor() {
        return Author.get();
    }

    public void setAuthor(String author) {

        Author = new SimpleStringProperty(author);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getEdition() {
        return edition.get();
    }

    public void setEdition(String edition) {
        this.edition =new SimpleStringProperty (edition);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String publisher) {
        this.publisher = new SimpleStringProperty(publisher);
    }

    public String getPublish_Date() {
        return publish_Date.get();
    }

    public void setPublish_Date(String publish_Date) {
        this.publish_Date = new SimpleStringProperty (publish_Date);
    }
}
