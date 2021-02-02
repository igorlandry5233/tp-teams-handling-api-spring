package fr.sorbonne.paris.nord.university.api.modelmapper;

import fr.sorbonne.paris.nord.university.api.entity.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static void mapToDto(){

    }

    public TeamEntity mapToEntity(TeamDto teamDto){
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamDto.getId());
        teamEntity.setName(teamDto.getName());
        teamEntity.setSlogan(teamDto.getSlogan());
        return teamEntity;
    }
}
