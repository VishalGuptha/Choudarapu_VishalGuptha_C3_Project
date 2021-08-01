import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RestaurantTest {
    Restaurant restaurant;
    
    private static final String RESTAURANT_NAME = "Amelie's cafe";
    private static final String SWEET_CORN_SOUP = "Sweet corn soup";
    private static final String VEGITABLE_LASAGNE = "Vegetable lasagne";
    private static final int PRICE_CONSTANT_119 = 119;
    private static final int PRICE_CONSTANT_269 = 269;
    private static final String LOCATION = "Chennai";
    
    
    private Restaurant addRestaurant()
    {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant restaurant = new Restaurant(RESTAURANT_NAME,LOCATION,openingTime,closingTime);
        restaurant.addToMenu(SWEET_CORN_SOUP,PRICE_CONSTANT_119);
        restaurant.addToMenu(VEGITABLE_LASAGNE, PRICE_CONSTANT_269);
        
        return restaurant;
        
    }
  
    
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
    	LocalTime openingTime = LocalTime.now().minusHours(5);
    	LocalTime closingTime = LocalTime.now().plusHours(5);
    	
    	restaurant = new Restaurant(RESTAURANT_NAME,LOCATION,openingTime,closingTime);
    	
    	assertTrue(restaurant.isRestaurantOpen());
    	
    	
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
    	
    	LocalTime openingTime = LocalTime.now().plusHours(1);
    	LocalTime closingTime = LocalTime.now().plusHours(10);
    	
    	restaurant = new Restaurant(RESTAURANT_NAME,LOCATION,openingTime,closingTime);
    	
    	assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        
        restaurant =addRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
    	restaurant =addRestaurant();
        
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu(VEGITABLE_LASAGNE);
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
    	restaurant =addRestaurant();
        
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    //>>>>>>>>>>>>>>>>>>>>>>TOTAL COST<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void get_total_price_for_selected_items_should_return_expected_total_cost()
    {
    	restaurant =addRestaurant();
    	List<String> itemNames = Arrays.asList(SWEET_CORN_SOUP,VEGITABLE_LASAGNE);
    	
    	int totalCost = restaurant.getTotalPriceForSelectedItems(itemNames);
    	
    	assertEquals(388,totalCost);
    }
}