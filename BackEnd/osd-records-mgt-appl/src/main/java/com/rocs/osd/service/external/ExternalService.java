package com.rocs.osd.service.external;

import com.rocs.osd.domain.external.External;

import java.util.List;
/**
 * Service interface for managing external.
 */
public interface ExternalService {

    /**
     * Retrieves all external entities.
     *
     * @return list of all external
     */
    List<External> getAllExternal();
}
