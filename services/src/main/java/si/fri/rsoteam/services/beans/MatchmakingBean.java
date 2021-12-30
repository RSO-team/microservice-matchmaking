package si.fri.rsoteam.services.beans;

import org.eclipse.persistence.internal.libraries.antlr.runtime.tree.ParseTree;
import si.fri.rsoteam.lib.dtos.MatchmakingDto;
import si.fri.rsoteam.models.entities.MatchmakingEntity;
import si.fri.rsoteam.services.mappers.MatchmakingMapper;
import si.fri.rsoteam.services.mappers.ParticipantMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class MatchmakingBean {
    private Logger log = Logger.getLogger(MatchmakingBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private ParticipantBean participantBean;

    // TODO Implement CRUD and other required methods
    public MatchmakingDto getMatchmaking(Integer id) {
        return MatchmakingMapper.entityToDto(em.find(MatchmakingEntity.class, id));
    }

    public List<MatchmakingDto> getAllMatches() {
        return em.createNamedQuery("Matchmaking.getAll", MatchmakingEntity.class)
                .getResultList()
                .stream()
                .map(MatchmakingMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public MatchmakingDto createMatchmaking(MatchmakingDto matchmakingDto) {
        MatchmakingEntity matchmakingEntity = MatchmakingMapper.dtoToEntity(matchmakingDto);
        this.beginTx();
        em.persist(matchmakingEntity);
        this.commitTx();
        return MatchmakingMapper.entityToDto(matchmakingEntity);
    }
    
    public MatchmakingDto updateMatchmaking(MatchmakingDto matchmakingDto, Integer id) {
        this.beginTx();
        MatchmakingEntity matchmakingEntity = em.find(MatchmakingEntity.class, id);
        matchmakingEntity.setName(matchmakingDto.name);
        matchmakingEntity.setDate(matchmakingDto.date);
        matchmakingEntity.setMatch(matchmakingDto.match.stream().map(ParticipantMapper::dtoToEntity).collect(Collectors.toList()));
        matchmakingEntity.getMatch().forEach(participantEntity -> participantEntity.setMatchmaking(matchmakingEntity));
        em.persist(matchmakingEntity);
        this.commitTx();
        return MatchmakingMapper.entityToDto(matchmakingEntity);
    }
    public void deleteMatchmaking(Integer id) {
        MatchmakingEntity matchmakingEntity = em.find(MatchmakingEntity.class, id);
        if (matchmakingEntity != null) {
            matchmakingEntity.getMatch()
                    .forEach(participantEntity -> participantBean.deleteParticipant(participantEntity.getId()));
            this.beginTx();
            em.remove(matchmakingEntity);
            this.commitTx();
        }
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
