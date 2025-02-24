package org.example.demo1.service;

import org.example.demo1.service.impl.UserServiceImpl;
import org.example.demo1.service.impl.CarServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : SachinSilva
 */
public class ServiceFactory {
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    private final Map<ServiceType, SuperService> serviceRegistry = new HashMap<>();

    public ServiceFactory() {
        serviceRegistry.put(ServiceType.USER, new UserServiceImpl());
        serviceRegistry.put(ServiceType.CAR, new CarServiceImpl());
    }

    public <T extends SuperService> T getService(ServiceType serviceType) {
        return (T) serviceRegistry.get(serviceType);
    }
}

