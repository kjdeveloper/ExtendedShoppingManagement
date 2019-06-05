package jankowiak.kamil.persistence.repository.impl;

import jankowiak.kamil.persistence.model.Client;
import jankowiak.kamil.persistence.model.Order;
import jankowiak.kamil.persistence.model.Product;
import jankowiak.kamil.persistence.repository.ShoppingRepository;
import jankowiak.kamil.persistence.repository.converters.JsonOrdersConverter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonShoppingRepository implements ShoppingRepository {

    @Override
    public void save(String jsonFilename, List<Order> orders) {
        new JsonOrdersConverter(jsonFilename).toJson(orders);
    }

    @Override
    public Map<Client, Map<Product, Long>> readOrders(String jsonFilename) {
        return new JsonOrdersConverter(jsonFilename)
                .fromJson()
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(
                        o -> o.getClient(),
                        o -> o.getProducts()
                                .stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())
                                )
               ));
    }
}
