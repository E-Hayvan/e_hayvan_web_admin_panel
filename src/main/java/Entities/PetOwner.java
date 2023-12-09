package Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PetOwner")
public class PetOwner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PetOwnerID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User User;
    @OneToMany(mappedBy = "Pet")
    private List<Pet> Pets;
    @ManyToOne
    @JoinColumn(name = "VetID", referencedColumnName = "VetID")
    private Veterinarian Vet;
    @OneToMany(mappedBy = "Appointment")
    private List<Appointment> Appointments;
}
