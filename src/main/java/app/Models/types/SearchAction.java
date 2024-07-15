package app.Models.types;

@FunctionalInterface
public interface SearchAction {
    void perform(String search);
}
