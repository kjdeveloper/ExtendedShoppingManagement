package jankowiak.kamil.persistence.repository;

import jankowiak.kamil.persistence.model.Client;
import jankowiak.kamil.persistence.model.Order;
import jankowiak.kamil.persistence.model.Product;

import java.util.List;
import java.util.Map;

public interface ShoppingRepository {

    void save(final String jsonFilename, List<Order> orders);

    Map<Client, Map<Product, Long>> readOrders(final String jsonFilename);
}
