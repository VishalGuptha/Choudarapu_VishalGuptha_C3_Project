import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
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
        Restaurant restaurant = service.addRestaurant(RESTAURANT_NAME,LOCATION,openingTime,closingTime);
        
        restaurant.addToMenu(SWEET_CORN_SOUP,PRICE_CONSTANT_119);
        restaurant.addToMenu(VEGITABLE_LASAGNE, PRICE_CONSTANT_269);
        
        return restaurant;
        
    }



    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        
    	
        restaurant = addRestaurant();
        
        Restaurant resultRestaurant = service.findRestaurantByName(RESTAURANT_NAME);
        
        assertEquals(restaurant.getName(),resultRestaurant.getName());
    	
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        
        restaurant = addRestaurant();
    	
    	assertThrows(restaurantNotFoundException.class,() -> {
    	
    		service.findRestaurantByName("Cafe");
    	});
    	
    	
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        
        restaurant = addRestaurant();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant(RESTAURANT_NAME);
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        
        restaurant = addRestaurant();

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        
        restaurant = addRestaurant();

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales",LOCATION,LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}