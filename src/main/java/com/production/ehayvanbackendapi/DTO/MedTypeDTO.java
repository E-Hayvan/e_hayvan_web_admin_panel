package com.production.ehayvanbackendapi.DTO;

public class MedTypeDTO {
    private Integer medTypeID;
    private String medTypeName;

    public MedTypeDTO(Integer medTypeID, String medTypeName) {
        this.medTypeID = medTypeID;
        this.medTypeName = medTypeName;
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
