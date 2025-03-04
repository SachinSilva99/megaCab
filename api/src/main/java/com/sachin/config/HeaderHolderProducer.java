package com.sachin.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import com.sachin.dto.other.HeaderHolder;

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
