package ar.edu.utn.frc.tup.lc.iv.responses;

import ar.edu.utn.frc.tup.lc.iv.client.DistrictDTO;
import ar.edu.utn.frc.tup.lc.iv.client.PositionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultsResponse {

    @JsonProperty("distrito")
    private String district;

    public ResultsResponse() {
        this.results = new ArrayList<>();
    }

    @JsonProperty("seccion")
    private String section;

    @JsonProperty("resultados")
    private List<ResultItemResponse> results;
}
