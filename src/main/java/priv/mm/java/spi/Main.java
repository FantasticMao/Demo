package priv.mm.java.spi;

import java.util.ServiceLoader;

/**
 * Main
 *
 * @author maomao
 * @see java.util.ServiceLoader
 * @see java.sql.DriverManager#loadInitialDrivers
 * @since 2020-01-02
 */
public class Main {

    public static void main(String[] args) {
        ServiceLoader<Hello> serviceLoader = ServiceLoader.load(Hello.class);
        for (Hello hello : serviceLoader) {
            hello.say();
        }
    }
}
