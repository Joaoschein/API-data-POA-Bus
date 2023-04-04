package Panvel.com.br.DataPOABus.models;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BusStopModel {

    private String lat;
    private String lng;
}
