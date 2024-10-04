package ar.edu.utn.frc.tup.lc.iv.responses;

import ar.edu.utn.frc.tup.lc.iv.client.PositionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResultItemResponse {

    @JsonProperty("orden")
    private Integer order;

    @JsonProperty("nombre")
    private String name;

    public ResultItemResponse(String name) {
        this.name = name;
        this.votes = 0;
    }

    @JsonProperty("votos")
    private Integer votes;

    @JsonProperty("porcentaje")
    private float percentage;
}
