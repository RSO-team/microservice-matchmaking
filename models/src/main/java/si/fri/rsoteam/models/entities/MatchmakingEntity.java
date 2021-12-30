package si.fri.rsoteam.models.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matchmaking")
@NamedQuery(name = "Matchmaking.getAll", query = "select e from MatchmakingEntity e ")
public class MatchmakingEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @OneToMany(mappedBy = "matchmaking",cascade={CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval=true)
    private List<ParticipantEntity> match = new ArrayList<ParticipantEntity>();

    private String name;

    private Instant date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ParticipantEntity> getMatch() {
        return match;
    }

    public void setMatch(List<ParticipantEntity> match) {
        this.match = match;
    }
}
