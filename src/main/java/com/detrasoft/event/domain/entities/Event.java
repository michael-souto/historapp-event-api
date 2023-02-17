package com.detrasoft.event.domain.entities;

import com.detrasoft.event.domain.entities.date.HistoricalDate;
import com.detrasoft.framework.crud.entities.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
@Table(name = "event")
public class Event extends GenericEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String tags;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "historical_first_period_id", nullable = true, foreignKey = @ForeignKey(name = "fk1_event"))
    private HistoricalDate firstPeriod;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "historical_final_period_id", nullable = true, foreignKey = @ForeignKey(name = "fk2_event"))
    private HistoricalDate finalPeriod;

    @ElementCollection
    @CollectionTable(name = "characters_event", joinColumns = @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "fk1_characters_event")))
    @Column(name = "character_id")
    private List<Long> idsCharacters;

    @Column(nullable = false, updatable = false)
    private Instant createAt;

    @Column(nullable = true)
    private Instant updateAt;
}
