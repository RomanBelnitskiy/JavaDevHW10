package org.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Parameter;

import java.time.OffsetDateTime;

@Entity
@Immutable
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(generator = "tickets_id_generator")
    @GenericGenerator(
            name = "tickets_id_generator",
            strategy = "sequence",
            parameters = {
                    @Parameter(name = "sequence_name", value = "tickets_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanet;
    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanet;

    protected Ticket() {
    }

    public Ticket(Client client, Planet fromPlanet, Planet toPlanet) {
        this.client = client;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", client=" + client +
                ", fromPlanet=" + fromPlanet +
                ", toPlanet=" + toPlanet +
                '}';
    }
}
