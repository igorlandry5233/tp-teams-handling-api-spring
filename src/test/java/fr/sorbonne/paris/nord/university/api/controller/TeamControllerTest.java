package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamDto;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamControllerTest {

    private final TeamEntity newTeam = new TeamEntity(6L,"SNK","TITAN");

    private final TeamDto teamDto = new TeamDto(6L,"SNK1","TITAN1");

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(teamController);
    }

    @Test
    public void whenRequestGetAllTeam_thenOK(){
        when(teamService.getAllTeams()).thenReturn(Arrays.asList(newTeam, newTeam));

        given()
                .when()
                .get("teams")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void whenRequestGetOneTeamById_ThenOK(){
        when(teamService
                .getTeamById(1L))
                .thenReturn(newTeam);

        given()
                .when()
                .get("teams/{id}", 1L)
                .then()
                .body("name",containsString("SNK"))
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void whenRequestPostTeam_Then0K(){
        when(teamService
                .addTeam(Mockito.any(TeamEntity.class)))
                .thenReturn(newTeam);

        given()
                .contentType("application/json")
                .body(teamDto)
                .when()
                .post("teams")
                .then()
                .body("name",containsString("SNK"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenRequestPutTeam_ThenOK(){
        when(teamService
                .updateTeam(Mockito.any(TeamEntity.class)))
                .thenReturn(newTeam);

        given()
                .contentType("application/json")
                .body(teamDto)
                .when()
                .put("teams/{id}", newTeam.getId())
                .then()
                .body("name",containsString("SNK"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void WhenRequestDeleteTeam_ThenOK(){
        given()
                .contentType("application/json")
                .when()
                .delete("teams/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
