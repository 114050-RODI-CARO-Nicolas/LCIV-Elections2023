package ar.edu.utn.frc.tup.lc.iv.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DistrictResponse(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("nombre")
        String name) {
}
