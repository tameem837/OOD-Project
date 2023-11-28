package bd.edu.diu.swe.purplesense.model;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purple_sense_id")
    private PurpleSense purpleSense;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cart(Long id, PurpleSense purpleSense, int quantity, User user) {
        this.id = id;
        this.purpleSense = purpleSense;
        this.quantity = quantity;
        this.user = user;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurpleSense getPurpleSense() {
        return purpleSense;
    }

    public void setPurpleSense(PurpleSense purpleSense) {
        this.purpleSense = purpleSense;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
