package com.raynigon.rqms.domain.online.repositories;

import com.raynigon.rqms.domain.online.entities.ABTest;
import com.raynigon.rqms.domain.online.entities.TestVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ABTestRepository extends JpaRepository<ABTest, UUID> {

    Optional<ABTest> findByName(String name);

    Optional<ABTest> findByVariant(TestVariant variant);
}
