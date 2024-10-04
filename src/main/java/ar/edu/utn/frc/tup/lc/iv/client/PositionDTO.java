package ar.edu.utn.frc.tup.lc.iv.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PositionDTO(
        @JsonProperty("cargoId")
        Integer id,
        @JsonProperty("cargoNombre")
        String position,
        @JsonIgnore
        @JsonProperty("distritoId")
        Integer districtId) {
}
