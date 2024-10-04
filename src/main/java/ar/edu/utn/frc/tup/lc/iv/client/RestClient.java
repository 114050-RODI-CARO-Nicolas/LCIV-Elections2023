package ar.edu.utn.frc.tup.lc.iv.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class RestClient {

    private final RestTemplate restTemplate = new RestTemplate();

   // private final String url = "http://elections-api:8080/";
    private final String url = "http://localhost:8080/";

    public List<DistrictDTO> getDistricts(String name, Integer id){
        String endpointUrl;
        if (Objects.isNull(name) && Objects.isNull(id)){
            endpointUrl = url + "distritos";
        }
        else if(!Objects.isNull(id)){
            endpointUrl =  url + "distritos?distritoId=" + id.toString();
        }
        else {
            endpointUrl =  url + "distritos?distritoNombre=" + name;
        }
        DistrictDTO[] districts = restTemplate.getForObject(endpointUrl, DistrictDTO[].class);
        if (districts == null){
            return null;
        }
        return  Arrays.stream(districts).toList();
    }
    public List<PositionDTO> getPositions(Integer districtId){
        String endpointUrl;
        if (Objects.isNull(districtId)){
             endpointUrl = url + "cargos";
        }
        else {
             endpointUrl =  url + "cargos?distritoId=" + districtId.toString();
        }
        PositionDTO[] positions = restTemplate.getForObject(endpointUrl, PositionDTO[].class);
        if (positions == null){
            return null;
        }
        return  Arrays.stream(positions).toList();
    }
    public List<SectionDTO> getSections(Integer districtId, Integer sectionId){
        String endpointUrl;
        if (Objects.isNull(districtId)){
            return new ArrayList<>();
        }
        else {
            if (Objects.isNull(sectionId)){
                endpointUrl = url + "secciones?distritoId=" + districtId.toString();
            }
            else {
                endpointUrl =  url + "secciones?seccionId=" + sectionId.toString() + "&distritoId=" + districtId.toString();
            }
        }
        SectionDTO[] sections = restTemplate.getForObject(endpointUrl, SectionDTO[].class);
        if (sections == null){
            return null;
        }
        return  Arrays.stream(sections).toList();
    }
    public List<ResultDTO> getResults(Integer sectionId){
        String endpointUrl;
        if (Objects.isNull(sectionId)){
            return new ArrayList<>();
        }
        endpointUrl =  url + "resultados?seccionId=" + sectionId.toString();
        ResultDTO[] sections = restTemplate.getForObject(endpointUrl, ResultDTO[].class);
        if (sections == null){
            return null;
        }
        return  Arrays.stream(sections).toList();
    }

    public String ping() {
        return restTemplate.getForObject(url + "ping",String.class);
    }
}
