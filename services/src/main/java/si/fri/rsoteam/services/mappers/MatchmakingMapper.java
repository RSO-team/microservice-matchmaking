package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.lib.dtos.MatchmakingDto;
import si.fri.rsoteam.models.entities.MatchmakingEntity;

import java.util.stream.Collectors;

public class MatchmakingMapper {
    public static MatchmakingDto entityToDto(MatchmakingEntity et) {
        MatchmakingDto matchmakingDto = new MatchmakingDto();
        matchmakingDto.id = et.getId();
        matchmakingDto.name = et.getName();
        matchmakingDto.match = et.getMatch().stream().map(ParticipantMapper::entityToDto).collect(Collectors.toList());
        matchmakingDto.date = et.getDate();
        return matchmakingDto;
    }

    public static MatchmakingEntity dtoToEntity(MatchmakingDto matchmakingDto) {
        MatchmakingEntity matchmakingEntity = new MatchmakingEntity();
        matchmakingEntity.setName(matchmakingDto.name);
        matchmakingEntity.setId(matchmakingDto.id);
        matchmakingEntity.setDate(matchmakingDto.date);
        matchmakingEntity.setMatch(matchmakingDto.match.stream().map(ParticipantMapper::dtoToEntity).collect(Collectors.toList()));
        return  matchmakingEntity;
    }
}
