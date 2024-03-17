package dev.patika.vetsystem.dao;

import dev.patika.vetsystem.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {
}
