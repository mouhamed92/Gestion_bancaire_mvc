package org.sid.dao;

import org.sid.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienRepository extends JpaRepository<Client, Long> {

}
