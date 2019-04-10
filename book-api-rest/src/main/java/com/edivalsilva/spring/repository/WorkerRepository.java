package com.edivalsilva.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edivalsilva.spring.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
