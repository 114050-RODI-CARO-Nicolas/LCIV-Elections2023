package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.client.DistrictDTO;
import ar.edu.utn.frc.tup.lc.iv.client.PositionDTO;
import ar.edu.utn.frc.tup.lc.iv.client.SectionDTO;
import ar.edu.utn.frc.tup.lc.iv.responses.DistrictResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.PositionsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.ResultsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.SectionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectionsService {
    List<DistrictResponse> getDistricts(String name);
    PositionsResponse getPositions(Integer districtId);
    List<SectionResponse> getSections(Integer districtId, Integer sectionId);
    ResultsResponse getResults(Integer districtId, Integer sectionId);
    String pingApi();
}
