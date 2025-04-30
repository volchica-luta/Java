package org.example.lab_work5;

/**
 * Исключение при ошибках связывания компонентов
 */
class ComponentBindingException extends RuntimeException {
    public ComponentBindingException(String message) {
        super(message);
    }

    public ComponentBindingException(String message, Throwable cause) {
        super(message, cause);
    }
}