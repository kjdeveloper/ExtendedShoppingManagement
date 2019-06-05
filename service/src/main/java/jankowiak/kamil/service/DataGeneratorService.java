package jankowiak.kamil.service;

import jankowiak.kamil.persistence.model.Client;
import jankowiak.kamil.persistence.model.Order;
import jankowiak.kamil.persistence.model.Product;
import jankowiak.kamil.persistence.model.enums.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGeneratorService {

    private final List<Order> orderList;

    public DataGeneratorService() {
        this.orderList = getOrderList();
    }

    public List<Order> getOrderList(){
        Client client1 = new Client("Kamil", "Jankowiak", 30, new BigDecimal(6000));
        Client client2 = new Client("Anna", "Jankowiak", 29, new BigDecimal(8000));
        Client client3 = new Client("Izabela", "Kurdej", 38, new BigDecimal(2500));
        Client client4 = new Client("Karol", "Burd", 28, new BigDecimal(2500));
        Client client5 = new Client("Wojtek", "Liniacz", 27, new BigDecimal(2500));

        Product product1 = new Product("zelazko", Category.ELECTRONICS, new BigDecimal(400));
        Product product2 = new Product("TV", Category.ELECTRONICS, new BigDecimal(3500));
        Product product3 = new Product("dywan", Category.HOME, new BigDecimal(1000));
        Product product4 = new Product("krzesla_ogrodowe", Category.GARDEN, new BigDecimal(1600));
        Product product5 = new Product("obraz", Category.ART, new BigDecimal(300));
        Product product6 = new Product("kierownica", Category.AUTOMOTIVE, new BigDecimal(200));
        Product product7 = new Product("smoczek", Category.CHILD, new BigDecimal(40));
        Product product8 = new Product("piguly", Category.HEALTH, new BigDecimal(50));
        Product product9 = new Product("buty", Category.SPORT, new BigDecimal(360));
        Product product10 = new Product("koszulka", Category.SPORT, new BigDecimal(99));
        Product product11 = new Product("wyciraczki", Category.AUTOMOTIVE, new BigDecimal(60));
        Product product12 = new Product("pedzel", Category.ART, new BigDecimal(8));
        Product product13 = new Product("kwiat", Category.GARDEN, new BigDecimal(16));
        Product product14 = new Product("pieluchy", Category.CHILD, new BigDecimal(30));
        Product product15 = new Product("koktajl", Category.HEALTH, new BigDecimal(20));
        Product product16 = new Product("okulary_plywackie", Category.SPORT, new BigDecimal(1190));


        return Arrays.asList(
                new Order(client1, new ArrayList<>(Arrays.asList(product1, product2, product3, product13, product13, product13))),
                new Order(client2, new ArrayList<>(Arrays.asList(product5, product16, product16, product16, product7, product1))),
                new Order(client3, new ArrayList<>(Arrays.asList(product14, product4, product3, product10, product12, product12))),
                new Order(client4, new ArrayList<>(Arrays.asList(product8, product2, product2, product3, product6, product6))),
                new Order(client5, new ArrayList<>(Arrays.asList(product9, product11, product11, product8, product15, product15, product15, product15)))
        );
    }

}
