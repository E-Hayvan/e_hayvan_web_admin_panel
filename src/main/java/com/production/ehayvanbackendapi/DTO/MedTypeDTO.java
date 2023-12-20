package com.production.ehayvanbackendapi.DTO;

import java.util.List;

public class MedTypeDTO {
    private Integer medTypeID;
    private String medTypeName;
    private List<MedicationDTO> medications;


    public List<MedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDTO> medications) {
        this.medications = medications;
    }


    public MedTypeDTO(Integer medTypeID, String medTypeName, List<MedicationDTO> medications) {
        this.medTypeID = medTypeID;
        this.medTypeName = medTypeName;
        this.medications = medications;
    }



    public Integer getMedTypeID() {
        return medTypeID;
    }

    public void setMedTypeID(Integer medTypeID) {
        this.medTypeID = medTypeID;
    }

    public String getMedTypeName() {
        return medTypeName;
    }

    public void setMedTypeName(String medTypeName) {
        this.medTypeName = medTypeName;
    }

}
