package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping("/teams")
    public List<TeamEntity> all() {
        return teamService.getAllTeams();

    }

    @GetMapping("/teams/{id}")
    public TeamEntity teamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping("/teams")
    public TeamDto createTeam(@RequestBody TeamDto newTeam) {
        teamService.addTeam(mapToEntity(newTeam));
        return newTeam;
    }

    @PutMapping("/teams/{id}")
    public TeamDto updateTeam(@RequestBody TeamDto newTeam, @PathVariable Long id) {
        teamService.updateTeam(mapToEntity(newTeam));
        return newTeam;
    }

    @DeleteMapping("/teams/{id}")
    void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

    static TeamEntity mapToEntity(TeamDto teamDto){
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(teamDto.getName());
        teamEntity.setSlogan(teamDto.getSlogan());
        return teamEntity;
    }
}

