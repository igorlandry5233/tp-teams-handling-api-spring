package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    final private TeamRepository teamRepository;

    public List<TeamEntity> getAllTeams(){
        List<TeamEntity> listAllTeam = teamRepository.findAll();
        return listAllTeam.isEmpty() ? null : listAllTeam;
    }

    public TeamEntity getTeamById(long id){
        return teamRepository.findById(id).orElse(null);
    }

    @Transactional
    public TeamEntity addTeam(TeamEntity teamEntity){
        teamRepository.saveAndFlush(teamEntity).getId();
        return teamEntity;
    }

    @Transactional
    public TeamEntity updateTeam(TeamEntity oldTeamEntity){
        TeamEntity teamEntity = teamRepository.findById(oldTeamEntity.getId()).orElse(null);
        if(teamEntity != null) {
            teamEntity.setName(oldTeamEntity.getName());
            teamEntity.setSlogan(oldTeamEntity.getSlogan());
            teamRepository.saveAndFlush(teamEntity);
        }
        return teamEntity;
    }

    @Transactional
    public void deleteTeam(long id){
        teamRepository.deleteById(id);
    }
}
