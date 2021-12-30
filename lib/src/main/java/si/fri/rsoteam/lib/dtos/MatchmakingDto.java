package si.fri.rsoteam.lib.dtos;

import java.time.Instant;
import java.util.List;

public class MatchmakingDto {
    public Integer id;
    public String name;
    public Instant date;
    public List<ParticipantDto>  match;

    public MatchmakingDto(){
    }
}
