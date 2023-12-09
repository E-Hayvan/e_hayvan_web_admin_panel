package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UserType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserTypeID;
    private String Type;
    @OneToMany(mappedBy = "UserType")
    private List<User> Users;

    public int getUserTypeID() {
        return UserTypeID;
    }
    public void setUserTypeID(int userTypeID) {
        UserTypeID = userTypeID;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }
}
