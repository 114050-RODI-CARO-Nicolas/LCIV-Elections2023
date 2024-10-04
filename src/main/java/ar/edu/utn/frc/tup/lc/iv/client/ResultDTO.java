package ar.edu.utn.frc.tup.lc.iv.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultDTO(
        @JsonProperty("distritoNombre")
        String district,
        @JsonProperty("seccionNombre")
        String section,
        @JsonProperty("agrupacionNombre")
        String groupName,
        @JsonProperty("votosCantidad")
        Integer groupVotes,
        @JsonIgnore
        @JsonProperty("agrupacionId")
        Integer groupId,
        @JsonIgnore
        @JsonProperty("votosTipo")
        String voteType) {
}
