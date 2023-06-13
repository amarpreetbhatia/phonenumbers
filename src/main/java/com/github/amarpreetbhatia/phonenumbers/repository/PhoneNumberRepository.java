package com.github.amarpreetbhatia.phonenumbers.repository;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> findAll();
    List<PhoneNumber> findByCustomer(Customer customer);
    PhoneNumber findByNumber(String number);
    @Modifying
    @Transactional
    @Query("UPDATE PhoneNumber p SET p.active = :active WHERE p.id = :id")
    void updatePhoneNumberActivation(@Param("id") Long id, @Param("active") boolean active);
}

