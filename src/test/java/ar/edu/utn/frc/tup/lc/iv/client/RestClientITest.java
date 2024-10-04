package ar.edu.utn.frc.tup.lc.iv.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestClientITest {

    @Autowired
    private RestClient restClient;

    @Test
    void getDistricts(){
        List<DistrictDTO> result = restClient.getDistricts(null,null);
        assertEquals(24, Objects.requireNonNull(result).size());
    }
    @Test
    void getDistrictsWithName(){
        List<DistrictDTO> result = restClient.getDistricts("Córdoba",null);
        assertEquals(1, Objects.requireNonNull(result).size());
    }
    @Test
    void getDistrictsWithId(){
        List<DistrictDTO> result = restClient.getDistricts(null,1);
        assertEquals(1, Objects.requireNonNull(result).size());
    }
    @Test
    void getPositions(){
        List<PositionDTO> result = restClient.getPositions(null);
        assertEquals(118, Objects.requireNonNull(result).size());
    }
    @Test
    void getPositionsWithId(){
        List<PositionDTO> result = restClient.getPositions(1);
        assertEquals(7, Objects.requireNonNull(result).size());
    }
    @Test
    void getSections(){
        List<SectionDTO> result = restClient.getSections(1,null);
        assertEquals(15, Objects.requireNonNull(result).size());
    }
    @Test
    void getSectionsWithoutDistrictId(){
        List<SectionDTO> result = restClient.getSections(null,null);
        assertEquals(0, Objects.requireNonNull(result).size());
    }
    @Test
    void getSectionsWithSectionId(){
        List<SectionDTO> result = restClient.getSections(1,1);
        assertEquals(1, Objects.requireNonNull(result).size());
    }
    @Test
    void getResults(){
        List<ResultDTO> result = restClient.getResults(26);
        assertEquals(2930, Objects.requireNonNull(result).size());
    }
    @Test
    void getResultsWithoutSectionId(){
        List<ResultDTO> result = restClient.getResults(null);
        assertEquals(0, Objects.requireNonNull(result).size());
    }

}
