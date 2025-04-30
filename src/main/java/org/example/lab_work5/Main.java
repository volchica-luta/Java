package org.example.lab_work5;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static final String DEPENDENCY_CONFIG_PATH = "/dependency-config.properties";

    public static void main(String[] args) {
        final Properties dependencySettings = new Properties();

        try (InputStream configStream = Main.class.getResourceAsStream(DEPENDENCY_CONFIG_PATH)) {
            if (configStream == null) {
                System.err.println("Configuration file not found at: " + DEPENDENCY_CONFIG_PATH);
                return;
            }

            dependencySettings.load(configStream);
            Injector dependencyInjector = new Injector(dependencySettings);
            testDependencyInjection(dependencyInjector);

        } catch (IOException e) {
            System.err.println("Failed to load dependency configuration: " + e.getMessage());
        }
    }

    /**
     * Тестирует работу инжектора зависимостей на классе SomeBean
     *
     * @param dependencyInjector экземпляр инжектора для тестирования
     */
    private static void testDependencyInjection(Injector dependencyInjector) {
        SomeBean testBean = new SomeBean();
        try {
            dependencyInjector.inject(testBean);
            testBean.foo();
        } catch (ComponentBindingException e) {
            System.err.println("Dependency injection failed: " + e.getMessage());
        }
    }
}