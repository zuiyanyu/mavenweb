package com.DesignPattern.patterns.builder_pattern.demo02;

import com.DesignPattern.patterns.builder_pattern.demo02.items.impl.ChickenBurger;
import com.DesignPattern.patterns.builder_pattern.demo02.items.impl.Coke;
import com.DesignPattern.patterns.builder_pattern.demo02.items.impl.Pepsi;
import com.DesignPattern.patterns.builder_pattern.demo02.items.impl.VegBurger;

public class MealBuilder {

    public Meal prepareVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal (){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}