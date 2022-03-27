package nl.lyashevska.mmtspringboot.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;

@Entity
@Indexed
@Table(name="manuscript")
public class Manuscript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // identify attributes as searchable by Field annotation
    @Field(termVector = TermVector.YES)
    private String title;

    @Field(termVector = TermVector.YES)
    private String author;

    private String year;

    @Field(termVector = TermVector.YES)
    private String journal;

    private int volume;

    public Manuscript(){
        super();
    }

    // constructor
    public Manuscript(int id, String title, String author, String year, String journal, int volume) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.journal = journal;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Manuscript{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", journal='" + journal + '\'' +
                ", volume=" + volume +
                '}';
    }
}
