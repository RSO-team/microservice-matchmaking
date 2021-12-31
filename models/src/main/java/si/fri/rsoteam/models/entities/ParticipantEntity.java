package si.fri.rsoteam.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "participant")
@NamedQuery(name = "Participant.getAll", query = "SELECT participant from ParticipantEntity participant")
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private MatchmakingEntity matchmaking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchmakingEntity getMatchmaking() {
        return matchmaking;
    }

    public void setMatchmaking(MatchmakingEntity matchmaking) {
        this.matchmaking = matchmaking;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
