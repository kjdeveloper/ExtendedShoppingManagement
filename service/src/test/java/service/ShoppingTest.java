package service;

import jankowiak.kamil.persistence.model.enums.Category;
import jankowiak.kamil.persistence.repository.impl.JsonShoppingRepository;
import jankowiak.kamil.service.ShoppingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingTest {
    private JsonShoppingRepository jsonShoppingRepository = new JsonShoppingRepository();

    @Test
    @DisplayName("Biggest money spender")
    public void test1() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var client = new ShoppingService(jsonFilename).biggestMoneySpender();

        assertEquals("Anna", client.getName(), "TEST 1 FAILED");
    }

    @Test
    @DisplayName("Biggest spender in category")
    public void test2() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var category = Category.AUTOMOTIVE;
        var client = new ShoppingService(jsonFilename).biggestMoneySpenderInCategory(category);

        assertEquals("Izabela", client.getName(), "TEST 2 FAILED");
    }

    @Test
    @DisplayName("Categories and its average price")
    public void test3() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var categoriesAvgPrice = new ShoppingService(jsonFilename).categoriesWithAveragePrices();

        assertEquals(new BigDecimal(1500), categoriesAvgPrice.get(Category.ELECTRONICS), "Test 3 FAILED");
    }

    @Test
    @DisplayName("Most buy by category SPORT")
    public void test4() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Kamil", productsBuyer.get(Category.SPORT).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Most buy by category HOME")
    public void test4a() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Kamil", productsBuyer.get(Category.HOME).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Most buy by category ELECTRONICS")
    public void test4b() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Kamil", productsBuyer.get(Category.ELECTRONICS).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Most buy by category AUTOMOTIVE")
    public void test4c() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Izabela", productsBuyer.get(Category.AUTOMOTIVE).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Most buy by category ART")
    public void test4d() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Izabela", productsBuyer.get(Category.ART).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Most buy by category GARDEN")
    public void test4e() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Anna", productsBuyer.get(Category.GARDEN).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Most buy by category CHILD")
    public void test4f() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var productsBuyer = new ShoppingService(jsonFilename).mostBuyByCategory();

        assertEquals("Kamil", productsBuyer.get(Category.CHILD).getName(), "Test 4 failed");
    }

    @Test
    @DisplayName("Does the customer have enough money for shopping")
    public void test5() {
        String jsonFilename = "C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\service\\src\\test\\java\\service\\resources\\shoppingTest.json";

        var debt = new ShoppingService(jsonFilename).doesTheCustomerHaveEnoughCash();

        assertEquals(new BigDecimal(10000), debt.values().toArray()[2], "TEST 5 FAILED");
    }






}
