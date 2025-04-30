package org.example.lab_work5;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для автоматического связывания компонентов с использованием аннотации @AutoInjectable.
 */
public class Injector {
    private final Properties componentBindings;

    /**
     * Инициализирует новый экземпляр связывателя компонентов.
     *
     * @param componentBindings настройки привязок интерфейсов к реализациям
     * @throws IllegalArgumentException если настройки привязок не указаны
     */
     Injector(Properties componentBindings) throws IllegalArgumentException {
        if (componentBindings == null) {
            throw new IllegalArgumentException("Отсутствуют настройки привязок компонентов");
        }
        this.componentBindings = componentBindings;
    }

    /**
     * Выполняет связывание компонентов для указанного объекта.
     *
     * @param targetObject объект для внедрения зависимостей
     * @param <T> тип целевого объекта
     * @throws ComponentBindingException при ошибках связывания компонентов
     */
     <T> void inject(T targetObject) throws ComponentBindingException {
        Class<?> objectClass = targetObject.getClass();
        Field[] objectFields = objectClass.getDeclaredFields();

        for (Field field : objectFields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> interfaceType = field.getType();
                String implementationName = componentBindings.getProperty(interfaceType.getName());

                if (implementationName == null) {
                    throw new ComponentBindingException(
                            "Не найдена реализация для интерфейса: " + interfaceType.getName());
                }

                try {
                    Class<?> implementationClass = Class.forName(implementationName);
                    Object componentInstance = implementationClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(targetObject, componentInstance);
                } catch (Exception e) {
                    throw new ComponentBindingException(
                            "Ошибка создания компонента " + implementationName +
                                    " для поля " + field.getName() + " в классе " + objectClass.getName(), e);
                }
            }
        }
    }
}
