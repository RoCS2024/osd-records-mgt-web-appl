package com.rocs.osd.controller.external;

import com.rocs.osd.domain.external.External;
import com.rocs.osd.service.external.ExternalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * REST controller for managing external.
 */
@RestController
@RequestMapping("/External")
public class ExternalController {

    private ExternalService externalService;
    /**
     * Constructs a new ExternalController with ExternalService.
     *
     * @param externalService handles external entity operations
     */
    public ExternalController(ExternalService externalService) {
        this.externalService = externalService;
    }
    /**
     * Retrieves the list of all external entities.
     *
     * @return the list of all external
     */
    @GetMapping("/externals")
    public ResponseEntity<List<External>> getAllExternal() {
        return new ResponseEntity<>(externalService.getAllExternal(), HttpStatus.OK);
    }
}
