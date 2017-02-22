package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vxd4 on 2/17/2017.
 */
@RestController
@RequestMapping("/order")

public class CustomerOrder {

    @RequestMapping(method = RequestMethod.GET)
    public String SayHello(){
        return "Hello here is my order";
    }

}
