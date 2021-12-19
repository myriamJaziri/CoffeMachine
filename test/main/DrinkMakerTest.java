/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DrinkMakerTest {
    
    DrinkMaker drinkMaker;
    @Mock
    EmailNotifier emailNotif;
    @Mock
    BeverageQuantityChecker checker;
    
    @Before
    public void init(){
        drinkMaker = new DrinkMaker(emailNotif,checker); 
    }
    
    @Test
    public void orderTeaWithOneSugarAndStick() {
        String expected = "T:1:0" ;
        String actual = drinkMaker.makeDrink(Drink.TEA, 1, 0.4,false);
        assertEquals(expected,actual);
    }

    @Test
    public void orderChocolateWithoutSugarAndStick() {
        assertEquals(drinkMaker.makeDrink(Drink.CHOCOLATE, 0, 0.5,false),"H::");
    }

    @Test
    public void orderCoffeeWithTwoSugarAndStick() {
        assertEquals(drinkMaker.makeDrink(Drink.COFFEE, 2, 0.6,false),"C:2:0");
    }

    @Test
    public void orderCoffeeWhithoutEnoughMoney() {
        assertEquals(drinkMaker.makeDrink(Drink.COFFEE, 2, 0.1,false),"C:2:0");
    }
   
    @Test
    public void orderOrangeJuice() {
        assertEquals(drinkMaker.makeDrink(Drink.ORANGE, 0, 0.6,false),"O::");
    }
    
    @Test
    public void orderHotCoffeeWithoutSugar() {
        assertEquals(drinkMaker.makeDrink(Drink.COFFEE, 0, 0.6,true),"Ch::");
    }    
    
    @Test
    public void orderHotChocolateWithOneSugarAndStick() {
        assertEquals(drinkMaker.makeDrink(Drink.CHOCOLATE, 1, 0.5,true),"Hh:1:0");
    }
    
    @Test
    public void orderHotTeaWithTwoSugarAndStick() {
        assertEquals(drinkMaker.makeDrink(Drink.TEA, 2, 0.4,true),"Th:2:0");
    }
    
    //print report
    @Test
    public void diplayReport(){
        drinkMaker.makeDrink(Drink.TEA, 2, 0.4,true);
        drinkMaker.makeDrink(Drink.TEA, 1, 0.4,false);
        drinkMaker.makeDrink(Drink.CHOCOLATE, 1, 0.5,true);
        System.out.println(drinkMaker.printReport());
    }
    
    //check if enough tea or not 
    @Test
    public void checkEnoughOfTea(){
       when(checker.isEmpty("T")).thenReturn(true);
    }
}
