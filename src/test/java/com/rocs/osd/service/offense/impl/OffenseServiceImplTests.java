package com.rocs.osd.service.offense.impl;

import com.rocs.osd.domain.offense.Offense;
import com.rocs.osd.repository.offense.OffenseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OffenseServiceImplTests {

    @Mock
    private OffenseRepository offenseRepository;

    @InjectMocks
    private OffenseServiceImpl offenseService;

    @Test
    public void OffenseService_getAllOffense_ReturnsOffense() {
        List<Offense> offenses = Mockito.mock();
        when(offenseRepository.findAll()).thenReturn(offenses);

        List<Offense> saveOffense = offenseService.getAllOffense();

        Assertions.assertThat(saveOffense).isNotNull();
    }
    @Test
    public void OffenseService_getOffenseByType_ReturnsOffense() {
        String offenseByType = "Major";
        Offense mockOffense = new Offense();
        mockOffense.setType(offenseByType);

        List<Offense> mockOffenseList = Arrays.asList(mockOffense);

        when(offenseRepository.findByType(offenseByType)).thenReturn(mockOffenseList);

        List<Offense> offenseList = offenseService.getOffenseByType(offenseByType);

        Assertions.assertThat(offenseList).isNotNull();
        Assertions.assertThat(offenseList.get(0).getType()).isEqualTo(offenseByType);

        verify(offenseRepository, times(1)).findByType(offenseByType);
    }

    @Test
    public void OffenseService_getOffenseByDescription_ReturnsOffense() {
        String offenseByDescription = "Haircut";
        Offense mockOffense = new Offense();
        mockOffense.setDescription(offenseByDescription);
        when(offenseRepository.findByDescription(offenseByDescription)).thenReturn(mockOffense);

        Offense offense = offenseService.getOffenseByDescription(offenseByDescription);

        Assertions.assertThat(offense).isNotNull();
        Assertions.assertThat(offense.getDescription()).isEqualTo(offenseByDescription);

        verify(offenseRepository, times(1)).findByDescription(offenseByDescription);
    }

    @Test
    public void OffenseService_addOffense_ReturnsOffense() {
        Offense newOffense = new Offense();
        newOffense.setDescription("Haircut");

        when(offenseRepository.save(any(Offense.class))).thenReturn(newOffense);

        offenseService.addOffense(newOffense);

        verify(offenseRepository, times(1)).save(newOffense);
    }

    @Test
    public void OffenseService_updateOffense_returnsOffense() {
        Long offenseId = 1L;
        Offense existingOffense = new Offense();
        existingOffense.setId(offenseId);
        existingOffense.setDescription("Haircut");

        Offense updatedOffense = new Offense();
        updatedOffense.setId(offenseId);
        updatedOffense.setDescription("Cutting");

        when(offenseRepository.findById("1")).thenReturn(Optional.of(existingOffense));
        when(offenseRepository.save(any(Offense.class))).thenReturn(updatedOffense);

        Offense result = offenseService.updateOffense(updatedOffense);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getDescription()).isEqualTo("Cutting");

        verify(offenseRepository, times(1)).findById("1");
        verify(offenseRepository, times(1)).save(updatedOffense);
    }
}