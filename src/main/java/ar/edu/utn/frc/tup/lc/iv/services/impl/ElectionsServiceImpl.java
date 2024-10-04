package ar.edu.utn.frc.tup.lc.iv.services.impl;

import ar.edu.utn.frc.tup.lc.iv.client.*;
import ar.edu.utn.frc.tup.lc.iv.responses.*;
import ar.edu.utn.frc.tup.lc.iv.services.ElectionsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
public class ElectionsServiceImpl implements ElectionsService {

    @Autowired
    private RestClient restClient;

    @Override
    public List<DistrictResponse> getDistricts(String name) {
        List<DistrictDTO> districtDTOS = restClient.getDistricts(name,null);
        List<DistrictResponse> responses = new ArrayList<>();
        for (DistrictDTO districtDTO: districtDTOS) {
            responses.add(new DistrictResponse(districtDTO.id(), districtDTO.name()));
        }
        return responses;
    }

    @Override
    public PositionsResponse getPositions(Integer districtId) {
        List<PositionDTO> positions = restClient.getPositions(districtId);
        DistrictDTO districtDTO = restClient.getDistricts(null, districtId).stream().findFirst().orElse(null);

        List<PositionResponse> responses = new ArrayList<>();
        for (PositionDTO position: positions) {
            responses.add(new PositionResponse(position.id(), position.position()));
        }
        if (Objects.isNull(districtDTO) ){
            districtDTO = new DistrictDTO(0,"");
        }
        return new PositionsResponse(new DistrictResponse(districtDTO.id(), districtDTO.name()),responses);
    }

    @Override
    public List<SectionResponse> getSections(Integer districtId, Integer sectionId) {
        List<SectionDTO> sectionsDTOS = restClient.getSections(districtId,sectionId);
        List<SectionResponse> responses = new ArrayList<>();
        for (SectionDTO sectionDTO: sectionsDTOS) {
            responses.add(new SectionResponse(sectionDTO.id(), sectionDTO.section()));
        }
        return responses;
    }
    @Override
    public ResultsResponse getResults(Integer districtId, Integer sectionId) {
        ResultsResponse resultResponse = new ResultsResponse();
        List<ResultDTO> results = restClient.getResults(sectionId);
        HashMap<Integer, ResultItemResponse> resultItemResponseHashMap = new HashMap<>();
        Integer total = 0;
        setHashMap(resultItemResponseHashMap);
        for (int i = 0; i < results.size(); i++) {
            ResultDTO result = results.get(i);
            if(result.groupId() == 0){
                Integer key = null;
                if (Objects.equals(result.voteType(), "EN BLANCO")){
                    key = -1;
                }
                if (Objects.equals(result.voteType(), "NULO")){
                    key = -2;
                }
                if (Objects.equals(result.voteType(), "IMPUGNADO")){
                    key = -3;
                }
                if (Objects.equals(result.voteType(), "RECURRIDO")){
                    key = -4;
                }
                if (key != null){
                    resultItemResponseHashMap.get(key).setVotes(resultItemResponseHashMap.get(key).getVotes() + result.groupVotes());
                }
            }
            else{
                if (resultItemResponseHashMap.containsKey(result.groupId())){
                    ResultItemResponse resultItemResponse = resultItemResponseHashMap.get(result.groupId());
                    resultItemResponse.setVotes(resultItemResponse.getVotes()+result.groupVotes());
                } else {
                    ResultItemResponse resultItemResponse = new ResultItemResponse();
                    resultItemResponse.setName(result.groupName());
                    resultItemResponse.setVotes(result.groupVotes());
                    resultItemResponseHashMap.put(result.groupId(),resultItemResponse);
                }

            }
            total += result.groupVotes();
        }
        if(results.isEmpty()){
            return resultResponse;
        }
        resultResponse.setDistrict(results.get(0).district());
        resultResponse.setSection(results.get(0).section());
        resultResponse.setResults(new ArrayList<>(resultItemResponseHashMap.values().stream().toList()));
         resultResponse.getResults().sort(Comparator.comparingInt(ResultItemResponse::getVotes).reversed());
        for (int i = 0; i< resultResponse.getResults().size(); i++) {
            resultResponse.getResults().get(i).setOrder(i + 1);
            resultResponse.getResults().get(i).setPercentage(resultResponse.getResults().get(i).getVotes().floatValue()/total.floatValue());
        }

        return resultResponse;
    }

    @Override
    public String pingApi() {
        return restClient.ping();
    }

    private void setHashMap(HashMap<Integer,ResultItemResponse> map){
        map.put(-1,new ResultItemResponse("EN BLANCO"));
        map.put(-2,new ResultItemResponse("NULO"));
        map.put(-3,new ResultItemResponse("IMPUGNADO"));
        map.put(-4,new ResultItemResponse("RECURRIDO"));
    }

}
