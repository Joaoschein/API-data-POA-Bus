package Panvel.com.br.DataPOABus.service;

import Panvel.com.br.DataPOABus.models.BusStopModel;
import Panvel.com.br.DataPOABus.models.ItinerarioModel;
import Panvel.com.br.DataPOABus.models.LinhasModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BusLineService {
    final String urlBusLine = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
    final String urlItinerary = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";
    public LinhasModel getBusByName(String name) {
        List<LinhasModel> busLines = getAllBusLines();
        return busLines.stream().filter(busline -> busline.getNome().equals(name)).findAny().orElse(null);
    }

    public List<LinhasModel> getAllBusLines() {
        List<LinhasModel> busLines;
        Gson gson = new Gson();

        RestTemplate restTemplate = new RestTemplate();
        String result = Objects.requireNonNull(restTemplate.getForObject(urlBusLine, String.class));
        LinhasModel[] arrayBusLineModel = gson.fromJson(result, LinhasModel[].class);

        busLines = Arrays.stream(arrayBusLineModel).toList();

        return busLines;
    }

    public ItinerarioModel getItinerario(int id) {

        String uriItinerary = urlItinerary + id;

        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();

        String result = Objects.requireNonNull(restTemplate.getForObject(uriItinerary, String.class));
        ItinerarioModel busItineraryModel = gson.fromJson(result, ItinerarioModel.class);
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
            jsonObject.remove("idlinha");
            jsonObject.remove("nome");
            jsonObject.remove("codigo");

        Map<String, BusStopModel> map = gson.fromJson(jsonObject, Map.class);
            busItineraryModel.setItinerary(map);

            return busItineraryModel;
    }

    public LinhasModel save(LinhasModel linhasModel) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate.postForObject(urlBusLine, linhasModel, LinhasModel.class);
    }

   public void update(LinhasModel linhasModel) {
        RestTemplate restTemplate = new RestTemplate();
        if(mustReturnTrueWithExistBusLine(linhasModel))
            restTemplate.put(urlBusLine, linhasModel, LinhasModel.class);
        else
           save(linhasModel);

    }

    public boolean mustReturnTrueWithExistBusLine (LinhasModel linhasModel) {
        List<LinhasModel> teste = getAllBusLines();

        return teste.stream().anyMatch(busLine -> busLine.getNome().equals(linhasModel.getNome()));
    }
}
