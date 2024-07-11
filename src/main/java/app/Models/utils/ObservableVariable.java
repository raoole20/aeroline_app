package app.Models.utils;

import java.util.*;

import app.Models.types.VariableChangeListener;

public class ObservableVariable<T> {
    private T value;
    private ArrayList<VariableChangeListener> listeners = new ArrayList<>();

    public ObservableVariable(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
        notifyListeners();
    }

    public T getValue() {
        return this.value;
    }

    public void addListener(VariableChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(VariableChangeListener listener) {
        this.listeners.remove(listener);
    }

    // Paso 3: Notifica a los this.
    private void notifyListeners() {
        for (VariableChangeListener listener : this.listeners) {
            listener.onVariableChanged(this.value);
        }
    }
}
