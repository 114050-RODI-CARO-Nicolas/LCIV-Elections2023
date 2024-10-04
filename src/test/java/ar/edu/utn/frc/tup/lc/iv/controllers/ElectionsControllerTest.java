package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.responses.PositionsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.ResultsResponse;
import ar.edu.utn.frc.tup.lc.iv.services.ElectionsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ElectionsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ElectionsService electionsService;

    @Test
    public void testGetDistricts() throws Exception {
        when(electionsService.getDistricts(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/distritos"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPositions() throws Exception {
        when(electionsService.getPositions(any())).thenReturn(new PositionsResponse());
        mockMvc.perform(get("/cargos"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSections() throws Exception {
        when(electionsService.getSections(any(), any())).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/secciones"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetResults() throws Exception {
        when(electionsService.getResults(any(), any())).thenReturn(new ResultsResponse());
        mockMvc.perform(get("/resultados"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPingApi() throws Exception {
        when(electionsService.pingApi()).thenReturn("pong");
        mockMvc.perform(get("/pingApi"))
                .andExpect(status().isOk());
    }
}
