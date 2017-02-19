package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * Created by vxd4 on 2/16/2017.
 */
@RestController
@RequestMapping("/customer")

public class Customer {

    @RequestMapping( method = RequestMethod.GET)
    public String SayHello(){
        return "Hello Test Customer";
    }


}
