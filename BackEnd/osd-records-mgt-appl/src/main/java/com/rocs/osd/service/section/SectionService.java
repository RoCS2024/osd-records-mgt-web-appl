package com.rocs.osd.service.section;

import com.rocs.osd.domain.section.Section;

import java.util.List;
/**
 * Service interface for managing Section.
 */
public interface SectionService {
    /**
     * Retrieves all sections.
     *
     * @return list of all sections
     */
    List<Section> getAllSection();
}
