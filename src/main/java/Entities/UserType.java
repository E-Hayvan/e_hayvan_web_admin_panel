package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "UserType")
public class UserType {
    @Id
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
