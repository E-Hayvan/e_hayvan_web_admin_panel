package com.production.ehayvanbackendapi.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private String Surname;
    @Column(nullable = false)
    private String Email;
    @Column(nullable = false)
    private String Password;
    @ManyToOne
    @JoinColumn(name = "UserTypeID", referencedColumnName = "UserTypeID")
    private UserType UserTypeID;
    @OneToOne(mappedBy = "User")
    private Veterinarian Vet;
    @OneToOne(mappedBy = "User")
    private PetOwner Owner;

    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getSurname() {
        return Surname;
    }
    public void setSurname(String surname) {
        Surname = surname;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
}
