package com.detrasoft.event.domain.entities.date;

import com.detrasoft.framework.crud.entities.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calendar_date")
public class CalendarDate extends GenericEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_historicalDate", nullable = false, foreignKey = @ForeignKey(name = "fk1_calendar_date"))
    private HistoricalDate historicalDate;

    @Column(nullable = true)
    private int historicDay;

    @Column(nullable = true)
    private int historicMonth;

    @Column(nullable = true)
    private int historicYear;

    // used for a.C(A.E.C) or d.C(E.C)
    @Column(nullable = true)
    private boolean negativeYear;

    @Column(nullable = true)
    private Long idHistoricMonth;

    @Column(nullable = true)
    private String nameHistoricMonth;

    @Column(nullable = true)
    private Long idCalendar;

    @Column(nullable = true)
    private String nameCalendar;
}
