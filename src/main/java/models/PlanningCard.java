package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "PlanningCard")
public class PlanningCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PlanningCard_Id", unique = true, nullable = false)
    int id;

    @NotNull
    String Name;

    @ManyToMany(mappedBy = "planningCards")
    List<Gebruiker> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Gebruiker> getUsers() {
        return users;
    }

    public void setUsers(List<Gebruiker> users) {
        this.users = users;
    }
}
