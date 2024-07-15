package app.Models.DTOs;

public class Customers {
    public int id;
    public String name;
    public String lastName;
    public String email;
    public String identitycard;

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
