package com.example.Data;

import com.example.domain.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vxd4 on 2/19/2017.
 */
@Repository
public interface UserAccountDbRepository extends MongoRepository<UserAccount,String> {

    UserAccount getUserAccountByUserName(String userName);
}
