package org.gloria.designpatterns;

/**
 * Create on 2016/12/20 10:45.
 *
 * @author : gloria.
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {
        
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
    
    
    
}
