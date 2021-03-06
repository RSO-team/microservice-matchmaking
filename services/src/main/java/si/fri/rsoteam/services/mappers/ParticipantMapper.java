package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.lib.dtos.ParticipantDto;
import si.fri.rsoteam.models.entities.ParticipantEntity;

import java.util.stream.Collectors;

public class ParticipantMapper {
    public static ParticipantDto entityToDto(ParticipantEntity et) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.userId = et.getUserId();
        return participantDto;
    }

    public static ParticipantEntity dtoToEntity(ParticipantDto participantDto) {
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setUserId(participantDto.userId);
        return  participantEntity;
    }
}