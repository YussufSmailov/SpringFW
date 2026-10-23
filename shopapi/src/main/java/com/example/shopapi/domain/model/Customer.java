package com.example.shopapi.domain.model;

public class Customer {
    private final Long id;
    private String name;
    private String email;

    private Customer(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public static Customer create(String name, String email){
        return new Customer(null, name, normalize(email));
    }
    public static Customer restore(Long id, String name, String email){
        return new Customer(id, name, email);
    }
    public void update(String name, String email){
        this.name = name;
        this.email = email;
    }
    public boolean hasEmail(String otherEmail) {
        return email.equals(normalize(otherEmail));
    }
    private static String normalize(String email) {
        return email.trim().toLowerCase();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

