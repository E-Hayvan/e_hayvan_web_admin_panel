package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdatePetOwnerDTO {
    private CreateOrUpdateCustomerDTO user;

    // added for testing at the moment
    public CreateOrUpdatePetOwnerDTO(CreateOrUpdateCustomerDTO user) {
        this.user = user;
    }
  
    public CreateOrUpdateCustomerDTO getUser() {
        return user;
    }

    public void setUser(CreateOrUpdateCustomerDTO user) {
        this.user = user;
    }
}
