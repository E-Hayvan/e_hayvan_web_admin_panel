package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdatePetOwnerDTO {
    private CreateOrUpdateCustomerDTO user;
    private Integer veterinarianID;

    public Integer getVeterinarianID() {
        return veterinarianID;
    }

    public void setVeterinarianID(Integer veterinarianID) {
        this.veterinarianID = veterinarianID;
    }

    public CreateOrUpdateCustomerDTO getUser() {
        return user;
    }

    public void setUser(CreateOrUpdateCustomerDTO user) {
        this.user = user;
    }
}
