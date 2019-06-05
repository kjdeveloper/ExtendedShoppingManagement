package jankowiak.kamil.mainmenu.menu;

import jankowiak.kamil.persistence.model.Client;
import jankowiak.kamil.persistence.model.Product;
import jankowiak.kamil.persistence.model.enums.Category;
import jankowiak.kamil.service.ShoppingService;
import jankowiak.kamil.service.UserDataService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class MenuService {

    private final String filename;
    private final ShoppingService shoppingService;
    private final UserDataService userDataService;

    public MenuService(String filename) {
        this.filename = filename;
        shoppingService = new ShoppingService(filename);
        userDataService = new UserDataService();
    }

    private void menu() {
        System.out.println("1. Biggest money spender");
        System.out.println("2. Biggest money spender by category");
        System.out.println("3. Map with key: age and value: most category used in this age");
        System.out.println("4. Map with categories and average prices");
        System.out.println("5. Map with most buy products in category");
        System.out.println("6. Map with clients and their debt");
        System.out.println("7. Show order list");
        System.out.println("0. Exit");
    }

    public void mainMenu() {

        int option;
        do {
            menu();
            option = userDataService.getInt("Choose option: ");
            switch (option) {
                case 1:
                    Client biggestMoneySpender = option1();
                    System.out.println(biggestMoneySpender + "\n");
                    break;
                case 2:
                    Client biggestMoneySpenderInCategory = option2();
                    System.out.println(biggestMoneySpenderInCategory + "\n");
                    break;
                case 3:
                    Map<Integer, Category> mapWihMostBuyProducts = option3();
                    showMap(mapWihMostBuyProducts);
                    System.out.println("\n");
                    break;
                case 4:
                    Map<Category, BigDecimal> mapWithCategoriesAndAveragePrices = option4();
                    showMap(mapWithCategoriesAndAveragePrices);
                    System.out.println("\n");
                    break;
                case 5:
                    Map<Category, Client> mapWithMostBuyProductsByCategory = option5();
                    showMap(mapWithMostBuyProductsByCategory);
                    System.out.println("\n");
                    break;
                case 6:
                    Map<Client, BigDecimal> mapWithClientsDebt = option6();
                    showMap(mapWithClientsDebt);
                    System.out.println("\n");
                    break;
                case 7:
                    Map<Client, Map<Product, Long>> mapWithOrders = option7();
                    showMap(mapWithOrders);
                    System.out.println("\n");
                    break;
                case 0:
                    userDataService.close();
                    System.out.println("Bye bye");
                    return;
            }
        } while (true);
    }

    private Client option1() {
        return shoppingService.biggestMoneySpender();
    }

    private Client option2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which category do you choose? ELECTRONICS / HOME / HEALTH / CHILD / AUTOMOTIVE / ART / SPORT / GARDEN");
        Category category = Category.valueOf(sc.nextLine());
        return shoppingService.biggestMoneySpenderInCategory(category);
    }

    private Map<Integer, Category> option3() {
        return shoppingService.mostBuyProductsInAgeMap();
    }

    private Map<Category, BigDecimal> option4() {
        return shoppingService.categoriesWithAveragePrices();
    }

    private Map<Category, Client> option5() {
        return shoppingService.mostBuyByCategory();
    }

    private Map<Client, BigDecimal> option6() {
        return shoppingService.doesTheCustomerHaveEnoughCash();
    }

    private Map<Client, Map<Product, Long>> option7(){
        return shoppingService.mapWithAllOrders();
    }

    private void showMap(Map<?, ?> map) {
        map.forEach((key, value) -> System.out.println(key + " => " + value));
    }

}