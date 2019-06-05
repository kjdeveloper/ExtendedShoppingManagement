package jankowiak.kamil.mainmenu;

import jankowiak.kamil.mainmenu.menu.MenuService;

public class App
{
    public static void main( String[] args )
    {
/*

        DataGeneratorService dataGeneratorService = new DataGeneratorService();
        List<Order> orders = dataGeneratorService.getOrderList();
        dataGeneratorService.saveToFile("C:\\Programowanie\\TerribleShoppingFinal\\persistence\\src\\main\\java\\resources\\ordersForTerribleShopping.json", orders);

*/

        new MenuService("C:\\Users\\Admin\\Desktop\\ExtendedShoppingManagement\\persistence\\src\\main\\java\\resources\\ordersForTerribleShopping.json").mainMenu();


    }
}
