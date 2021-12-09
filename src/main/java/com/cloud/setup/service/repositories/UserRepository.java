package com.cloud.setup.service.repositories;

import com.cloud.setup.service.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository()
public interface UserRepository extends CrudRepository<User, Long> {
}
