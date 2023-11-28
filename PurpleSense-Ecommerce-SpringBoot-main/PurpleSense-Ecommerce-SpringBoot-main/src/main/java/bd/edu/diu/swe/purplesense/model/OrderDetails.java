package bd.edu.diu.swe.purplesense.model;

import javax.persistence.*;

@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private double price;
    private int quantity;

    public OrderDetails(Long id, Orders orders, double price, int quantity, PurpleSense purpleSense) {
        this.id = id;
        this.orders = orders;
        this.price = price;
        this.quantity = quantity;
        this.purpleSense = purpleSense;
    }

    public OrderDetails() {
    }

    @ManyToOne
    @JoinColumn(name = "purple_sense_id")
    private PurpleSense purpleSense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PurpleSense getPurpleSense() {
        return purpleSense;
    }

    public void setPurpleSense(PurpleSense purpleSense) {
        this.purpleSense = purpleSense;
    }
}
