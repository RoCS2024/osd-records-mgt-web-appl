package com.rocs.osd.service.external.impl;

import com.rocs.osd.domain.external.External;
import com.rocs.osd.repository.external.ExternalRepository;
import com.rocs.osd.service.external.ExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementation of External interface.
 */
@Service
public class ExternalServiceImpl implements ExternalService {
    private ExternalRepository externalRepository;
    /**
     * Constructor for injecting the ExternalRepository.
     *
     * @param externalRepository external repository
     */
    public ExternalServiceImpl(ExternalRepository externalRepository) {
        this.externalRepository = externalRepository;
    }

    @Override
    public List<External> getAllExternal() {
        return externalRepository.findAll();
    }
}
