
package com.example.Data;

import com.example.domain.UserAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vxd4 on 2/20/2017.
 */
@Component
public class CustomDbRepository {

    private List<UserAccount> userAccounts = new ArrayList<>();

    public CustomDbRepository(){

        List<String> roles = new ArrayList<>(1);
        roles.add("ROLE_USER");
        UserAccount userAccount=new UserAccount("user","$2a$10$T4f05olrX1IJlB4rI/JRtOqYOJh.9QXz0ZfHcSFLYjFG/Ihj0RePe",roles,"user","user@aaa.com",true);
        userAccounts.add(userAccount);
        roles = new ArrayList<>(1);
        roles.add("ROLE_ADMIN");
        userAccount=new UserAccount("admin","$2a$10$T4f05olrX1IJlB4rI/JRtOqYOJh.9QXz0ZfHcSFLYjFG/Ihj0RePe",roles,"user","user@aaa.com",true);
        userAccounts.add(userAccount);
    }

    public UserAccount getUserByUserName(String userName){

        int i=0;
        while (i<userAccounts.size()){
            String namTest=userAccounts.get(i).getUserName();
            if (namTest.equals(userName)){
                return userAccounts.get(i);
            }
            i++;
        }
        return null;

    }
}
