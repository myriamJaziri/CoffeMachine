/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.HashMap;
import java.util.Map;


public class DrinkMaker {
    
   private Map<Drink ,Integer> mapDrink;
   private double totalMoney;
   private EmailNotifier emailNotif;
   private BeverageQuantityChecker checker;
    
    public DrinkMaker(EmailNotifier emailNotif,BeverageQuantityChecker checker){
        
        //this map is used to store how many of each drink was sold
        mapDrink = new HashMap<>();
        
        //initialize the total to 0
        this.totalMoney = 0;
        
        this.emailNotif = emailNotif;
        this.checker  = checker;
    }
    
    public String makeDrink(Drink drink,int nbSugar, double amount, boolean extraHot){
        
       double priceDiff = drink.getPrice() - amount;
       
        //check if the entered amount is less than the price of the drink 
        if(priceDiff > 0){
            return "M: "+ priceDiff + " euro of the amount are missing";
        }
        
        String sugar = this.setSugar(nbSugar);
        String stick = this.setStick(sugar);
        String hot = this.setExtraHot(drink, extraHot);
        this.totalMoney +=  drink.getPrice();

        //Check if drink is already sold or not
        //to increment the total 
        if(mapDrink.containsKey(drink))
            mapDrink.put(drink, mapDrink.get(drink)+1);
        else
            mapDrink.put(drink, 1);

        return drink.getCode() + hot + ":" + sugar + ":"+ stick;
    }
    
    //convert sugar to string
    private String setSugar(int nbSugar){
        
        if(nbSugar > 0)
            return String.valueOf(nbSugar);
        else
            return "";
    }
    
    //set stick value based on the number of sugar
    private String setStick(String nbSugar){
    
          if(nbSugar.length() > 0)
              return "0";
          else
              return "";            
    }
    
    //set extraHot value based on the drink and cutomer demand
    private String setExtraHot(Drink drink, boolean extraHot){
    
        if(drink.isExtraHot() && extraHot)
            return "h";
        else
            return "";
    }
    
    public String printReport(){

        String report = "";
        for (Map.Entry map: mapDrink.entrySet()) {
            report = map.getKey() +  ":"+ map.getValue() + ","+ report;
        }
        return report + " total : "+ this.totalMoney;
    }
}
