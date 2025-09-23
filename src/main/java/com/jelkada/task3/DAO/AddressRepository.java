package com.jelkada.task3.DAO;

import com.jelkada.task3.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
