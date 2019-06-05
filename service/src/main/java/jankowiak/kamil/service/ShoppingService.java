package jankowiak.kamil.service;

import jankowiak.kamil.exceptions.AppException;
import jankowiak.kamil.persistence.model.Client;
import jankowiak.kamil.persistence.model.Product;
import jankowiak.kamil.persistence.model.enums.Category;
import jankowiak.kamil.persistence.repository.impl.JsonShoppingRepository;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoppingService {
    private Map<Client, Map<Product, Long>> clientsWithProducts;

    public ShoppingService(String jsonFilename) {
        this.clientsWithProducts = new JsonShoppingRepository().readOrders(jsonFilename);
    }

    //1
    public Client biggestMoneySpender() {
        return clientsWithProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        v -> v.getValue()
                                .entrySet()
                                .stream()
                                .map(e -> Collections.nCopies(e.getValue().intValue(), e.getKey()))
                                .flatMap(s -> s.stream()
                                        .map(Product::getPrice))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(() -> new IllegalStateException("CLIENT NO FOUND"))
                .getKey();
    }

    //2
    public Client biggestMoneySpenderInCategory(Category category) {
        return clientsWithProducts
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                                .entrySet()
                                .stream()
                                .map(ee -> Collections.nCopies(ee.getValue().intValue(), ee.getKey()))
                                .flatMap(ss -> ss.stream())
                                .filter(pr -> pr.getCategory().equals(category))
                                .map(Product::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(() -> new IllegalStateException("CLIENT NOT FOUND"))
                .getKey();

    }

    //3
    public Map<Integer, Category> mostBuyProductsInAgeMap() {
        Map<Client, List<Product>> m1 = clientsWithProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            List<Product> products = new ArrayList<>();
                            e.getValue()
                                    .forEach((key, value) -> products.addAll(Collections.nCopies(value.intValue(), key)));
                            return products;
                        }
                ));

        Map<Integer, List<Category>> m2 = m1.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().getAge(),
                        e -> e.getValue()
                                .stream()
                                .map(Product::getCategory).collect(Collectors.toList())
                ));

        Map<Integer, Category> m3 = m2.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .entrySet()
                                .stream().min((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                                .orElseThrow(NullPointerException::new)
                                .getKey()
                ));

        return m3;
    }

    //4
    public Map<Category, BigDecimal> categoriesWithAveragePrices() {
        return clientsWithProducts.entrySet()
                .stream()
                .map(e -> e.getValue().entrySet()
                        .stream()
                        .map(ee -> Collections.nCopies(ee.getValue().intValue(), ee.getKey()))
                        .flatMap(ss -> ss.stream())
                )
                .flatMap(ss -> ss)
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(ee -> ee.getKey(), ee -> ee.getValue()
                        .stream()
                        .collect(Collectors2.summarizingBigDecimal(Product::getPrice))
                        .getAverage()));
    }

    public Map<Category, Client> mostBuyByCategory() {

        return Arrays
                .stream(Category.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        category -> clientsWithProducts
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        e -> e.getKey(),
                                        e -> e.getValue()
                                                .entrySet()
                                                .stream()
                                                .flatMap(ee -> Collections.nCopies(ee.getValue().intValue(), ee.getKey()).stream())
                                                .filter(p -> p.getCategory().equals(category))
                                                .count()

                                        )
                                )
                                .entrySet().stream()
                                .max(Comparator.comparingLong(Map.Entry::getValue))
                                .orElseThrow(() -> new AppException("...."))
                                .getKey()
                )
                );

    }

    //6
    private boolean checkClientCash(BigDecimal cashClient, BigDecimal priceForProducts) {
        return cashClient.compareTo(priceForProducts) >= 0;
    }

    private BigDecimal howBigIsTheDebt(BigDecimal cashClient, BigDecimal priceForProducts) {
        if (!checkClientCash(cashClient, priceForProducts)) {
            return priceForProducts.subtract(cashClient);
        }
        return BigDecimal.ZERO;
    }

    public Map<Client, BigDecimal> doesTheCustomerHaveEnoughCash() {
        Map<Client, List<Product>> m1 = clientsWithProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            List<Product> products = new ArrayList<>();
                            e.getValue()
                                    .forEach((key, value) -> products.addAll(Collections.nCopies(value.intValue(), key)));
                            return products;
                        }
                ));

        Map<Client, BigDecimal> m2 = m1.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .map(Product::getPrice)
                                .reduce(BigDecimal::add)
                                .orElseThrow(() -> new AppException("Invalid price"))
                ));

        return m2.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> howBigIsTheDebt(e.getKey().getCash(), e.getValue())
                ));
    }

    public Map<Client, Map<Product, Long>> mapWithAllOrders() {
        return clientsWithProducts;
    }


}



