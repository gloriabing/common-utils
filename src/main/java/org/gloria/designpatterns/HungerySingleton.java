package org.gloria.designpatterns;

/**
 * Create on 2016/12/20 11:15.
 *
 * @author : gloria.
 */
public class HungerySingleton {

    private static HungerySingleton instance = new HungerySingleton();

    private HungerySingleton() {
        
    }
    
    public static HungerySingleton getInstance() {
        return instance;
    }
}
