package com.example.Service;

import com.example.Data.CustomDbRepository;
import com.example.Data.UserAccountDbRepository;
import com.example.domain.UserAccount;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by vxd4 on 2/19/2017.
 */
@Component
public class UserAccountService {

    @Autowired
    UserAccountDbRepository userAccountDbRepository;

    //UserDetails userDetails = new User()
    public User loadByUserName (String userName){

        //UserAccount userAccount= userAccountDbRepository.getUserAccountByUserName(userName);
        UserAccount userAccount = userAccountDbRepository.getUserAccountByUserName(userName);
        Collection<GrantedAuthority> gas;
        gas= new HashSet<>();
        for (int i=0; i<userAccount.getRoles().size();i++)        {

            JSONObject jsonObject = new JSONObject(userAccount.getRoles().get(i));
            String role = jsonObject.getString("role");
            gas.add(new SimpleGrantedAuthority(role));
        }
        return new User(userAccount.getUserName(),userAccount.getPassword(),userAccount.getEnabled(),true,true,true,gas);
    }


}
