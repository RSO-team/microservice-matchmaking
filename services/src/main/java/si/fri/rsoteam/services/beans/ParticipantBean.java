package si.fri.rsoteam.services.beans;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rsoteam.lib.dtos.ParticipantDto;
import si.fri.rsoteam.models.entities.ParticipantEntity;
import si.fri.rsoteam.services.mappers.ParticipantMapper;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ParticipantBean {
    private Logger log = LogManager.getLogger(ParticipantBean.class.getName());

    @Inject
    private EntityManager em;

    @Timed
    public ParticipantDto getParticipant(Integer id){
        return ParticipantMapper.entityToDto(em.find(ParticipantEntity.class,id));
    }

    @Timed
    public List<ParticipantDto> getAllParticipants(){
        return em.createNamedQuery("Participant.getAll", ParticipantEntity.class).getResultList().stream().map(ParticipantMapper::entityToDto).collect(Collectors.toList());
    }

    public void deleteParticipant(Integer id) {
        ParticipantEntity participantEntity = em.find(ParticipantEntity.class, id);
        if ( participantEntity != null) {
            this.beginTx();
            em.remove(participantEntity);
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
