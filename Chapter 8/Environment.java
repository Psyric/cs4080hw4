package lox;

import java.util.HashMap;
import java.util.Map;

class Environment {
    static final Object UNINITIALIZED = new Object();

    private final Map<String, Object> values = new HashMap<>();

    void define(String name, Object value) {
        values.put(name, value);
    }

    Object get(Token name) {
        if (!values.containsKey(name.lexeme)) {
            throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
        }

        Object value = values.get(name.lexeme);
        if (value == UNINITIALIZED) {
            throw new RuntimeError(name, "Variable '" + name.lexeme + "' is not initialized.");
        }

        return value;
    }

    void assign(Token name, Object value) {
        if (!values.containsKey(name.lexeme)) {
            throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
        }

        values.put(name.lexeme, value);
    }
}
