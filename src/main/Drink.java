/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


public enum Drink {
    
    TEA("T",0.4,true),COFFEE("C",0.6,true),CHOCOLATE("H",0.5,true),ORANGE("O",0.6,false);
    
    private String code;
    private double price;
    private boolean extraHot;
   
    
    Drink(String code,double price,boolean extraHot){
        this.code = code;
        this.price = price;
        this.extraHot = extraHot;
    }
    
    public String getCode(){
        return this.code;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public boolean isExtraHot(){
        return this.extraHot;
    }
    
}
