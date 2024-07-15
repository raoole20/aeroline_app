package app.Models.DTOs;

public class Customers {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String identitycard;

    public Customers(int id, String name, String lastName, String email, String identitycard) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.identitycard = identitycard;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getIdentityCard() {
        return identitycard;
    }
}
