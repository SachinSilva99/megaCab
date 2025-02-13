package org.example.demo1.repository;

import org.example.demo1.repository.repo.UserRepository;
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
        repositoryMap.put(RepositoryType.USER, userRepository);
    }

    public <T extends SuperRepository> T getRepo(RepositoryType repositoryType) {
        return (T) repositoryMap.get(repositoryType);
    }
}



