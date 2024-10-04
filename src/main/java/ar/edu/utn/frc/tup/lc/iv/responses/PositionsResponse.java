package ar.edu.utn.frc.tup.lc.iv.responses;

import ar.edu.utn.frc.tup.lc.iv.client.DistrictDTO;
import ar.edu.utn.frc.tup.lc.iv.client.PositionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PositionsResponse {
    @JsonProperty("distrito")
    private DistrictResponse district;

    public PositionsResponse() {
        this.district = new DistrictResponse(0,"");
        this.positions = new ArrayList<>();
    }

    @JsonProperty("cargos")
    private List<PositionResponse> positions;
}
