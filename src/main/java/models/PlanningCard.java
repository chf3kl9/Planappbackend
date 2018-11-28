package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "cards")
public class PlanningCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cardid", unique = true, nullable = false)
    int id;

    @Column(name = "cardname")
    String name;

    @Column(name = "carddescription")
    String cardDescription;

    @Column(name = "deadline")
    Date deadline;

    @ManyToMany(mappedBy = "planningCards")
    private Set<Gebruiker> users = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Set<Gebruiker> getUsers() {
        return users;
    }

    public void setUsers(Set<Gebruiker> users) {
        this.users = users;
    }

    public PlanningCard() {
    }
    public PlanningCard(String name, String cardDescription, Date deadline) {
        this.name = name;
        this.cardDescription = cardDescription;
        this.deadline = deadline;
    }
    public PlanningCard(int id, String name, String cardDescription, Date deadline){
        this.id = id;
        this.name = name;
        this.cardDescription = cardDescription;
        this.deadline = deadline;
    }
}
