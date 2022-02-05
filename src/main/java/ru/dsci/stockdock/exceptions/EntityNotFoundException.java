package ru.dsci.stockdock.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        this(entityClass, String.format("id = %d", id));
    }

    public EntityNotFoundException(Class<?> entityClass, String cause) {
        super(String.format("Entity %s does not exist: %s", entityClass.getSimpleName(), cause));
    }

}
