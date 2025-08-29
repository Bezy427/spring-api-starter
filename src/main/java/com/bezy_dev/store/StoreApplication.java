package com.bezy_dev.store;

import com.bezy_dev.store.entities.User;
import com.bezy_dev.store.repositories.UserRepository;
import com.bezy_dev.store.services.UserServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
       var service = context.getBean(UserServices.class);
       service.fetchProductsByCriteria();
    }



}
