package models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Gebruiker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid", unique = true, nullable = false)
    int id;

    @Column(name = "username")
    String name;

    @Column(name = "password")
    String password;


    @ManyToMany(mappedBy = "friended", cascade = CascadeType.ALL)
    @JoinTable(name="friends",
            joinColumns={@JoinColumn(name="userid")},
            inverseJoinColumns={@JoinColumn(name="friendid")})
    private Set<Gebruiker> friends = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="friends",
            joinColumns={@JoinColumn(name="friendid")},
            inverseJoinColumns={@JoinColumn(name="userid")})
    private Set<Gebruiker> friended = new HashSet<>();



    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "usercards",
        joinColumns = @JoinColumn(name = "userid"),
        inverseJoinColumns = @JoinColumn(name = "cardid"))
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Gebruiker> getFriends() {
        return friends;
    }

    public void setFriends(Set<Gebruiker> friends) {
        this.friends = friends;
    }

    public List<PlanningCard> getPlanningCards() {
        return planningCards;
    }

    public void setPlanningCards(List<PlanningCard> planningCards) {
        this.planningCards = planningCards;
    }

    public Gebruiker(){}
    public Gebruiker(String name, String password){
        this.name = name;
        this.password = password;
    }
    public Gebruiker(int id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
