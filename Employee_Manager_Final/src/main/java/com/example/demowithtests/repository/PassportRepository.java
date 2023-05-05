package com.example.demowithtests.repository;

import com.example.demowithtests.domain.employee.Employee;
import com.example.demowithtests.domain.passport.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {
    @Query(value = " select * from passports join registrations on passports.id=registrations.passport_id where registrations.id=:id",nativeQuery = true)
    Passport findPassportByRegistrations(Integer id);
}