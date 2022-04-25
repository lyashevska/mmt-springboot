/**
 * https://reflectoring.io/hibernate-search/
 * hibernate search annotations are added to the class
 */

package nl.lyashevska.mmtspringboot.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

// map a class to a database table and its fields to the table columns
@Entity
// map a class to Lucene index and its fields to the document fields in the index
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

    @Field(termVector = TermVector.YES)
    private String year;

    @Field(termVector = TermVector.YES)
    private String journal;

    @Field(termVector = TermVector.YES)
    private int volume;

    // file upload fields
    private String name;
    private String type;
    @Lob
    private byte [] data;

    // no arg constructor
    public Manuscript(){
    }

    public Manuscript(int id, User user, String title, String author, String year, String journal, int volume, String name, String type, byte[] data) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.author = author;
        this.year = year;
        this.journal = journal;
        this.volume = volume;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Manuscript))
            return false;

        Manuscript manuscript = (Manuscript) o;

        if (id != manuscript.id)
            return false;
        if (user != manuscript.user)
            return false;
        if (!title.equals(manuscript.title))
            return false;
        return journal.equals(manuscript.journal);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
