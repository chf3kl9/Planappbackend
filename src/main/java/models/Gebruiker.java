package models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Gebruiker")
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Gebruiker_ID", unique = true, nullable = false)
    int id;

    @NotNull
    String name;

    @ManyToMany(mappedBy="vrienden")
    private List<Gebruiker> vrienden;

    @ManyToMany(mappedBy = "users")
    private List<PlanningCard> planningCards;

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

    public List<Gebruiker> getVrienden() {
        return vrienden;
    }

    public void setVrienden(List<Gebruiker> vrienden) {
        this.vrienden = vrienden;
    }

    public List<PlanningCard> getPlanningCards() {
        return planningCards;
    }

    public void setPlanningCards(List<PlanningCard> planningCards) {
        this.planningCards = planningCards;
    }
}
