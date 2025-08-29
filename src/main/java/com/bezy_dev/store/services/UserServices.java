package com.bezy_dev.store.services;

import com.bezy_dev.store.entities.*;
import com.bezy_dev.store.repositories.AddressRepository;
import com.bezy_dev.store.repositories.CategoryRepository;
import com.bezy_dev.store.repositories.ProfileRepository;
import com.bezy_dev.store.repositories.UserRepository;
import com.bezy_dev.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserServices {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("Bezel Rusamu")
                .email("bezelrusamu@gmail.com")
                .password("password")
                .build();
        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else
            System.out.println("Transient/Detached");

        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else
            System.out.println("Transient/Detached");

    }

    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(5L).orElseThrow();
        System.out.println(profile.getBio());
    }

    public void fetchAddress(){
        var address = addressRepository.findById(5L).orElseThrow();
    }

    public void persistRelated(){
        var user = User.builder()
                .name("Bezy")
                .email("bezy@gmail.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("Rujeko")
                .city("Bulawayo")
                .state("Zimbabwe")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);

    }

    public void PersistRelated(){
        var user = User.builder()
                .name("Anopa")
                .email("anopa@gmail.com")
                .password("password12")
                .build();

        var address = Address.builder()
                .street("Usavi")
                .city("Harare")
                .state("Zimbabwe")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated(){
        var user = userRepository.findById(9L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts(){

        productRepository.deleteById(6L);
    }

    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1);
    }

    @Transactional
    public void fetchProducts(){
       var product = new Product();
       product.setName("product");

       var matcher = ExampleMatcher.matching()
               .withIncludeNullValues()
               .withIgnorePaths("id", "description")
               .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

       var example = Example.of(product, matcher);
       var products = productRepository.findAll(example);
       products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria(){
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1),  null);
        products.forEach(System.out::println);
    }

//     

    @Transactional
    public void fetchUsers(){
        var users = userRepository.findAllWithTags();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void printLoyalUsers(){
        var users = userRepository.findLoyalUsers(2);
        users.forEach(u -> System.out.println(u.getId() + ": " + u.getEmail()));
    }

}
