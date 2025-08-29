package com.bezy_dev.store.repositories;

import com.bezy_dev.store.dtos.UserSummary;
import com.bezy_dev.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query("select p.id, p.user.email from Profile p where p.loyaltyPoints > ?1 order by p.user.email")
    @EntityGraph(attributePaths = "user")
    List<UserSummary> findLoyalProfile(int loyaltyPoints);
}
