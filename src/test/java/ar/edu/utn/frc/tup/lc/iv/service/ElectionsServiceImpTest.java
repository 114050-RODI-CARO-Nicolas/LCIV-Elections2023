package ar.edu.utn.frc.tup.lc.iv.service;


import ar.edu.utn.frc.tup.lc.iv.client.*;
import ar.edu.utn.frc.tup.lc.iv.responses.DistrictResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.PositionsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.ResultsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.SectionResponse;
import ar.edu.utn.frc.tup.lc.iv.services.impl.ElectionsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ElectionsServiceImpTest {

    @Mock
    private RestClient restClient;

    @InjectMocks
    private ElectionsServiceImpl electionsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getDistrictsTest() {
        when(restClient.getDistricts(any(),any())).thenReturn(new ArrayList<>());
        List<DistrictResponse> result = electionsService.getDistricts("someName");
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void getPositionsTest() {
        when(restClient.getPositions(any())).thenReturn(new ArrayList<>());
        when(restClient.getDistricts(any(),any())).thenReturn(new ArrayList<>());
        PositionsResponse result = electionsService.getPositions(1);
        assertEquals(new PositionsResponse().getPositions().size(), result.getPositions().size());
    }

    @Test
    public void getSectionsTestEmpty() {
        when(restClient.getSections(1, 2)).thenReturn(new ArrayList<>());
        List<SectionResponse> result = electionsService.getSections(1, 2);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void getResultsTest() {
        List<ResultDTO> resultDTOS = new ArrayList<>();
        resultDTOS.add(new ResultDTO("test","test","",1,0,"EN BLANCO"));
        resultDTOS.add(new ResultDTO("test","test","",1,0,"NULO"));
        resultDTOS.add(new ResultDTO("test","test","",1,0,"IMPUGNADO"));
        resultDTOS.add(new ResultDTO("test","test","",1,0,"RECURRIDO"));
        resultDTOS.add(new ResultDTO("test","test","TEST",1,1,"POSITIVO"));
        resultDTOS.add(new ResultDTO("test","test","TEST",1,1,"POSITIVO"));
        when(restClient.getResults(2)).thenReturn(resultDTOS);
        ResultsResponse result = electionsService.getResults(1, 2);
        assertEquals(1, result.getResults().stream().filter(x -> Objects.equals(x.getName(), "EN BLANCO")).findFirst().get().getVotes());
        assertEquals(1, result.getResults().stream().filter(x -> Objects.equals(x.getName(), "NULO")).findFirst().get().getVotes());
        assertEquals(1, result.getResults().stream().filter(x -> Objects.equals(x.getName(), "IMPUGNADO")).findFirst().get().getVotes());
        assertEquals(1, result.getResults().stream().filter(x -> Objects.equals(x.getName(), "RECURRIDO")).findFirst().get().getVotes());
        assertEquals(2, result.getResults().stream().filter(x -> Objects.equals(x.getName(), "TEST")).findFirst().get().getVotes());
        assertEquals("test",result.getDistrict());
        assertEquals("test",result.getSection());
    }

    @Test
    public void pingApiTest() {
        when(restClient.ping()).thenReturn("pong");
        String result = electionsService.pingApi();
        assertEquals("pong", result);
    }
}
