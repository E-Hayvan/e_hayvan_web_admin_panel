package com.production.ehayvanbackendapi.DTO.request;

public class CreateOrUpdatePetOwnerDTO {
    private CreateOrUpdateCustomerDTO customerDTO;
    private Integer veterinarianID;

    public CreateOrUpdateCustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CreateOrUpdateCustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Integer getVeterinarianID() {
        return veterinarianID;
    }

    public void setVeterinarianID(Integer veterinarianID) {
        this.veterinarianID = veterinarianID;
    }
}
