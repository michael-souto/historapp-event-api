package com.detrasoft.event.domain.entities.date;

import com.detrasoft.framework.crud.entities.GenericEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historical_date")
public class HistoricalDate extends GenericEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Instant date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historicalDate", orphanRemoval = true)
    private List<CalendarDate> datesCalendars;
}
