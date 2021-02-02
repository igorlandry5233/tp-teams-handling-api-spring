package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@DataJpaTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void givenExistingId_whenGetTeamById_thenExpectedTeamInResult() {
        // Given.
        String psg = "PSG";
        // When.
        TeamEntity teamById = teamService.getTeamById(1L);
        // Then.
        Assertions.assertThat(teamById.getName()).isEqualTo(psg);
    }

    @Test
    public void givenNumberOfTeams_whenGetAllTeamBy_thenExpectedListOfTeamInResult() {
        // Given.
        int size = 5;
        // When.
        List<TeamEntity> listTeam = teamService.getAllTeams();
        // Then.
        Assertions.assertThat(listTeam).asList().size().isEqualTo(size);
    }

    @Test
    public void givenTeam_whenAddTeamInBD_thenExpectedTheSameName(){
        // Given.
        TeamEntity teamEntity = new TeamEntity(6L,"TITAN","Snk");
        // When.
        teamService.addTeam(teamEntity);
        TeamEntity teamById = teamService.getTeamById(teamEntity.getId());
        // Then.
        Assertions
                .assertThat(teamById.getName())
                .isNotNull()
                .isEqualTo("TITAN");
    }

    @Test
    public void givenTeam_whenUpdateTeamInBD_thenExpectedNotThePreviousNameOrSlogan(){
        // Given.
        long id = 1L;
        String name = "PSG";
        String slogan = "Ici c'est PARIS !!!";
        // When.
        TeamEntity teamEntity = teamService.updateTeam(new TeamEntity(id, name, slogan));
        // Then.
        TeamEntity teamByIdNewest = teamService.getTeamById(id);
        Assertions
                .assertThat(teamByIdNewest.getSlogan())
                .isNotNull()
                .isEqualTo(teamEntity.getSlogan());
    }

    @Test
    public void givenTeamId_WhenDeleteTeam_thenExpectedNothingByGettingTeam(){
        // Given.
        long id = 1L;
        // When.
        teamService.deleteTeam(id);
        // Then.
        Assertions
                .assertThat(teamService.getTeamById(id))
                .isNull();
    }
}
