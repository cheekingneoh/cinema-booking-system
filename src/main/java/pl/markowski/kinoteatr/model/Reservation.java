package pl.markowski.kinoteatr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "spektacle_id")
    private Spectacle spectacle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "repertoire_id")
    private Repertoire repertoire;

    @OneToMany(mappedBy = "reservation", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    @Column(name = "order_completed", nullable = false, columnDefinition = "boolean default true")
    private Boolean orderCompleted;


}