package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdatePetOwnerDTO {
    private CreateOrUpdateCustomerDTO user;

    // added for testing at the moment
    public CreateOrUpdatePetOwnerDTO(CreateOrUpdateCustomerDTO user, Integer veterinarianID) {
        this.user = user;
        this.veterinarianID = veterinarianID;
    }
  
    public CreateOrUpdateCustomerDTO getUser() {
        return user;
    }

    public void setUser(CreateOrUpdateCustomerDTO user) {
        this.user = user;
    }
}