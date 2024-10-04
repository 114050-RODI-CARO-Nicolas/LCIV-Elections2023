package ar.edu.utn.frc.tup.lc.iv.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DistrictDTO(
        @JsonProperty("distritoId")
        Integer id,
        @JsonProperty("distritoNombre")
        String name) {
}
