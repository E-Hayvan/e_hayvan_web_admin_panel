package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PetOwner")
public class PetOwner{
    @Id
    private int PetOwnerID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User UserID;
    private int PetID;

    private int VetID;
}
