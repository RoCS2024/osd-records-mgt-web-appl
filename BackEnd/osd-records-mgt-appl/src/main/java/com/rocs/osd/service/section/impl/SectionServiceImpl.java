package com.rocs.osd.service.section.impl;

import com.rocs.osd.domain.section.Section;
import com.rocs.osd.repository.section.SectionRepository;
import com.rocs.osd.service.section.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of SectionService interface.
 */
@Service
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    /**
     * Constructs a SectionServiceImpl
     *
     * @param sectionRepository the SectionRepository to be used
     */
    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    @Override
    public List<Section> getAllSection() {
        return sectionRepository.findAll();
    }
}
