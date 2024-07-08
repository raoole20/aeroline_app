package app.Models.Views;

public abstract class DefaultView {
    public int width = 800;
    public int height = 600;
    public boolean visible;
    public String title;

    public DefaultView() {
        System.out.println("DefaultView constructor");
    }

    public Object getView() {
        return null;
    }   
}
