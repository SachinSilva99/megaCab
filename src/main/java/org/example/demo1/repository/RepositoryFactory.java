package org.example.demo1.repository;

import org.example.demo1.repository.repo.BookingRepository;
import org.example.demo1.repository.repo.UserRepository;
import org.example.demo1.repository.repo.impl.BookingRepositoryImpl;
import org.example.demo1.repository.repo.impl.UserRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : SachinSilva
 */
public class RepositoryFactory {
    private static final RepositoryFactory REPOSITORY_FACTORY = new RepositoryFactory();

    public static RepositoryFactory getInstance() {
        return REPOSITORY_FACTORY;
    }

    private final Map<RepositoryType, SuperRepository> repositoryMap = new HashMap<>();

    public RepositoryFactory() {
        UserRepository userRepository = new UserRepositoryImpl();
        BookingRepository bookingRepository = new BookingRepositoryImpl();
        repositoryMap.put(RepositoryType.USER, userRepository);
        repositoryMap.put(RepositoryType.BOOKING, bookingRepository);
    }

    public <T extends SuperRepository> T getRepo(RepositoryType repositoryType) {
        return (T) repositoryMap.get(repositoryType);
    }
}



