package com.raynigon.rqms.domain.offline.repositories;

import com.raynigon.rqms.domain.offline.entities.RelevanceCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RelevanceCaseRepository extends JpaRepository<RelevanceCase, UUID> {
}
