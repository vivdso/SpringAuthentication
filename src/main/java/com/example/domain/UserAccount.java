package com.example.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by vxd4 on 2/10/2017.
 */
@Document(collection = "UserAccounts")
public class UserAccount {

    @Id
    private ObjectId id;


    @Field("userName")
    private String userName;


    @Field("password")
    private String password;

    @Field("roles")
    private List<String> roles;


    @Field("displayName")
    private String displayName;

    @Field("email")
    private String email;

    @Field("enabled")
    private Boolean enabled;


    @PersistenceConstructor
    public UserAccount(){}

    @PersistenceConstructor
    public UserAccount(String userName,String password ) {
        this.userName = userName;
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
