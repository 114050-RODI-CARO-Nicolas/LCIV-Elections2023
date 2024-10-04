package ar.edu.utn.frc.tup.lc.iv.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SectionDTO(
        @JsonProperty("seccionId")
        Integer id,
        @JsonProperty("seccionNombre")
        String section,
        @JsonIgnore
        @JsonProperty("distritoId")
        Integer districtId) {
}
