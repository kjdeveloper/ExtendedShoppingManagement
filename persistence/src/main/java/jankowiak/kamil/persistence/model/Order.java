package jankowiak.kamil.persistence.model;

import java.util.List;
import java.util.Objects;

public class Order {

    private Client client;
    private List<Product> products;

    public Order() {
    }

    public Order(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(client, order.client) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, products);
    }

    @Override
    public String toString() {
        return "Order: " +
                "client: " + client +
                ", products: " + products +
                '}';
    }
}
