package com.rocs.osd.domain.station;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Represents the Station entity.
 */
@Entity
@Data
public class Station implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stationName;
}
