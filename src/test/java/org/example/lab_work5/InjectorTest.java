package org.example.lab_work5;

import org.junit.jupiter.api.Test;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class DependencyInjectionTests {
    private static final String TEST_INTERFACE = "org.example.lab_work5.TestInterface";
    private static final String TEST_IMPL = "org.example.lab_work5.TestImplementation";
    private static final String OTHER_INTERFACE = "org.example.lab_work5.OtherInterface";
    private static final String OTHER_IMPL = "org.example.lab_work5.OtherImplementation";

    @Test
    void shouldInjectDependenciesWhenConfigurationValid() {
        Properties config = new Properties();
        config.put(TEST_INTERFACE, TEST_IMPL);

        Injector dependencyManager = new Injector(config);
        TestClass testInstance = new TestClass();

        dependencyManager.inject(testInstance);

        assertNotNull(testInstance.getDependency());
        assertInstanceOf(TestImplementation.class, testInstance.getDependency());
    }

    @Test
    void shouldThrowWhenImplementationClassMissing() {
        Properties config = new Properties();
        config.put(TEST_INTERFACE, "missing.Class");

        Injector dependencyManager = new Injector(config);
        TestClass testInstance = new TestClass();

        assertThrows(ComponentBindingException.class,
                () -> dependencyManager.inject(testInstance));
    }

    @Test
    void shouldThrowWhenInterfaceNotConfigured() {
        Properties config = new Properties();

        Injector dependencyManager = new Injector(config);
        TestClass testInstance = new TestClass();

        ComponentBindingException exception = assertThrows(ComponentBindingException.class,
                () -> dependencyManager.inject(testInstance));

        assertTrue(exception.getMessage().contains(TEST_INTERFACE));
    }

    @Test
    void shouldInjectMultipleDependenciesCorrectly() {
        Properties config = new Properties();
        config.put(TEST_INTERFACE, TEST_IMPL);
        config.put(OTHER_INTERFACE, OTHER_IMPL);

        Injector dependencyManager = new Injector(config);
        ComplexClass complexInstance = new ComplexClass();

        dependencyManager.inject(complexInstance);

        assertAll(
                () -> assertNotNull(complexInstance.getPrimaryService()),
                () -> assertNotNull(complexInstance.getSecondaryService()),
                () -> assertInstanceOf(TestImplementation.class, complexInstance.getPrimaryService()),
                () -> assertInstanceOf(OtherImplementation.class, complexInstance.getSecondaryService())
        );
    }

    @Test
    void shouldThrowWhenNullConfigurationProvided() {
        assertThrows(IllegalArgumentException.class,
                () -> new Injector(null),
                "Должна быть ошибка при null конфигурации");
    }

    @Test
    void shouldSkipFieldsWithoutAnnotation() {
        Properties config = new Properties();
        config.put(TEST_INTERFACE, TEST_IMPL);

        Injector dependencyManager = new Injector(config);
        PlainClass plainInstance = new PlainClass();

        dependencyManager.inject(plainInstance);
        assertNull(plainInstance.getNonInjectedField());
    }

    // Тестовые классы
    static class TestClass {
        @AutoInjectable
        private TestInterface dependency;

        public TestInterface getDependency() {
            return dependency;
        }
    }

    static class ComplexClass {
        @AutoInjectable
        private TestInterface primaryService;

        @AutoInjectable
        private OtherInterface secondaryService;

        public TestInterface getPrimaryService() {
            return primaryService;
        }

        public OtherInterface getSecondaryService() {
            return secondaryService;
        }
    }

    static class PlainClass {
        private TestInterface nonInjectedField;

        public TestInterface getNonInjectedField() {
            return nonInjectedField;
        }
    }
}

interface TestInterface {}
class TestImplementation implements TestInterface {}

interface OtherInterface {}
class OtherImplementation implements OtherInterface {}