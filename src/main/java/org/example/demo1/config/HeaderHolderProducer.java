package org.example.demo1.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import org.example.demo1.dto.other.HeaderHolder;

/**
 * Author : SachinSilva
 */
@RequestScoped
public class HeaderHolderProducer {

    @Produces
    @RequestScoped
    public HeaderHolder headerHolder() {
        return new HeaderHolder();
    }
}
