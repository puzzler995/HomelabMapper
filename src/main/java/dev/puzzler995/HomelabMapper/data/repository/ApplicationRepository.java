package dev.puzzler995.HomelabMapper.data.repository;

import dev.puzzler995.HomelabMapper.data.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
