package com.scaler.firstspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


@SpringBootApplication
public class FirstSpringApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstSpringApiApplication.class, args);
    }

    List<List<Integer>> set(){
        List<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> a1 = new ArrayList<>();
        list.add(a1);
        return list;
    }


}
