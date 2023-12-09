package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;
    private String Name;
    private String Surname;
    private String Email;
    private String Password;
    @ManyToOne
    @JoinColumn(name = "UserTypeID", referencedColumnName = "UserTypeID")
    private UserType UserTypeID;
    @OneToOne(mappedBy = "Veterinarian")
    private Veterinarian Vet;
    @OneToOne(mappedBy = "PetOwner")
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
