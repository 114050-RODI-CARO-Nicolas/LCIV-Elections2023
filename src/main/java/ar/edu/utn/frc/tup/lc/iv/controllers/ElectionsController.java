package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.client.DistrictDTO;
import ar.edu.utn.frc.tup.lc.iv.client.PositionDTO;
import ar.edu.utn.frc.tup.lc.iv.client.SectionDTO;
import ar.edu.utn.frc.tup.lc.iv.responses.DistrictResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.PositionsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.ResultsResponse;
import ar.edu.utn.frc.tup.lc.iv.responses.SectionResponse;
import ar.edu.utn.frc.tup.lc.iv.services.ElectionsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ElectionsController {

    @Autowired
    private ElectionsService electionsService;
    @GetMapping("/distritos")
    public List<DistrictResponse> GetDistricts(@RequestParam(value="distrito_nombre") Optional<String> districtName) {
        return electionsService.getDistricts(districtName.orElse(null));
    }
    @GetMapping("/cargos")
    public PositionsResponse GetPositions(@RequestParam(value="distrito_id") Optional<Integer> districtId) {
        return electionsService.getPositions(districtId.orElse(null));
    }
    @GetMapping("/secciones")
    public List<SectionResponse> GetSections(@RequestParam(value="distrito_id") Optional<Integer> districtId,
                                             @RequestParam(value="seccion_id") Optional<Integer> sectionId) {
        return electionsService.getSections(districtId.orElse(null),sectionId.orElse(null));
    }
    @GetMapping("/resultados")
    public ResultsResponse GetResultas(@RequestParam(value="distrito_id") Optional<Integer> districtId,
                                       @RequestParam(value="seccion_id") Optional<Integer> sectionId) {
        return electionsService.getResults(districtId.orElse(null),sectionId.orElse(null));
    }

    @GetMapping("/pingApi")
    public String pong() {
        return electionsService.pingApi();
    }


}
