package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdatePetOwnerDTO {
    private CreateOrUpdateCustomerDTO user;

    public CreateOrUpdateCustomerDTO getUser() {
        return user;
    }

    public void setUser(CreateOrUpdateCustomerDTO user) {
        this.user = user;
    }
}
