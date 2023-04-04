package Panvel.com.br.DataPOABus.controller;

import Panvel.com.br.DataPOABus.models.ItinerarioModel;
import Panvel.com.br.DataPOABus.models.LinhasModel;
import Panvel.com.br.DataPOABus.service.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bus")
public class BusController {
    @Autowired
    private BusLineService busLineService;

    @GetMapping
    public List<LinhasModel> getAll () {
        return busLineService.getAllBusLines();
    }
    @GetMapping(path = "/busName/{name}")
    public LinhasModel GetByName(@PathVariable String name) {
        return busLineService.getBusByName(name);
    }

    @GetMapping(path = "/itinerario/{id}")
    public ItinerarioModel getItinerario(@PathVariable int id) {
        return busLineService.getItinerario(id);
    }

    @PostMapping(path = "/saveBusLine")
    public LinhasModel saveLine(@RequestBody LinhasModel linhasModel) {
        return busLineService.save(linhasModel);
    }
    @PutMapping(path = "/updateBusLine")
    public void updateLine(@RequestBody LinhasModel linhasModel) {
        busLineService.update(linhasModel);
    }
}