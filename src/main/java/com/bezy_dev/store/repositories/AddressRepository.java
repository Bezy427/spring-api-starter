package com.bezy_dev.store.repositories;

import com.bezy_dev.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
