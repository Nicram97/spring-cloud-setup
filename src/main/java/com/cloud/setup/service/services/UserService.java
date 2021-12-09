package com.cloud.setup.service.services;

import com.cloud.setup.service.entities.User;
import com.cloud.setup.service.repositories.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class UserService implements CrudRepository<User, Long> {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public <S extends User> S save(S entity) {
        return this.userRepository.save(entity);
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return this.userRepository.saveAll(entities);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return this.userRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return this.userRepository.existsById(aLong);
    }

    @Override
    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return this.userRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return this.userRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        this.userRepository.deleteById(aLong);
    }

    @Override
    public void delete(User entity) {
        this.userRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        this.userRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        this.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        this.deleteAll();
    }
}
