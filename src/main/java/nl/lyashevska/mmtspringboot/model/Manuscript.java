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

    // bidirectional
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // identify attributes as searchable by Field annotation
    @Field(termVector = TermVector.YES)
    private String title;

    @Field(termVector = TermVector.YES)
    private String author;

    private String year;

    @Field(termVector = TermVector.YES)
    private String journal;

    private int volume;

    // constructor
    public Manuscript(){
        super();
    }

    public Manuscript(int id, User user, String title, String author, String year, String journal, int volume) {
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year='" + year + '\'' +
                ", journal='" + journal + '\'' +
                ", volume=" + volume +
                '}';
    }
}
