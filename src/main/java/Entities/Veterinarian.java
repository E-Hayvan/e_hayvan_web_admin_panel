package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Veterinarian")
public class Veterinarian{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int VetID;
    @OneToOne
    private User UserID;
    private String Clinic;

    public String getClinic() {
        return Clinic;
    }
    public void setClinic(String clinic) {
        Clinic = clinic;
    }
    public User getUserID() {
        return UserID;
    }
    public void setUserID(User userID) {
        UserID = userID;
    }
    public int getVetID() {
        return VetID;
    }
    public void setVetID(int vetID) {
        VetID = vetID;
    }
}
