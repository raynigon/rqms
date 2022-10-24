package com.raynigon.rqms.domain.offline.repositories;

import com.raynigon.rqms.domain.offline.entities.RelevanceTestRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestRunRepository extends JpaRepository<RelevanceTestRun, UUID> {
}
