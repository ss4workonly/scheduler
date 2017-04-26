package hello;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIME;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Event {

    public enum Style {SINGLE, PAIR};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TIMESTAMP)
    private Date created;

    @Temporal(TIME)
    private Date duration;

    private String title;

    @Enumerated
    private Style style;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> people = new ArrayList<>();

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
