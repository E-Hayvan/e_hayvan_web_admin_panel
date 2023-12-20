package com.production.ehayvanbackendapi.DTO;

import java.util.List;

public class UserTypeDTO {
    private Integer userTypeID;
    private String type;

    public UserTypeDTO(Integer userTypeID, String type, List<CustomerDTO> users) {
        this.userTypeID = userTypeID;
        this.type = type;
        this.users = users;
    }

    private List<CustomerDTO> users;


    public Integer getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(Integer userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<CustomerDTO> getUsers() {
        return users;
    }

    public void setUsers(List<CustomerDTO> users) {
        this.users = users;
    }



}
