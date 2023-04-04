package Panvel.com.br.DataPOABus.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItinerarioModel {
    private Integer idLinha;
    private String nome;
    private String codigo;
    private Map<String, BusStopModel> itinerary;

    @Override
    public String toString() {
        return "BusItineraryModel{" +
                "idlinha=" + idLinha +
                ", nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", itinerary=" + itinerary +
                '}';
    }

}
