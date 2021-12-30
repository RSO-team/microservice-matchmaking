package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.lib.dtos.ParticipantDto;
import si.fri.rsoteam.models.entities.ParticipantEntity;

import java.util.stream.Collectors;

public class ParticipantMapper {
    public static ParticipantDto entityToDto(ParticipantEntity et) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.userId = et.getId();
        return participantDto;
    }

    public static ParticipantEntity dtoToEntity(ParticipantDto participantDto) {
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setId(participantDto.userId);
        return  participantEntity;
    }
}