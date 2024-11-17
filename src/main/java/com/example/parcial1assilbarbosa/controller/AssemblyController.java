package com.example.parcial1assilbarbosa.controller;

import com.example.parcial1assilbarbosa.service.AssemblyLineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class AssemblyController {

    private final AssemblyLineService assemblyLineService;

    public AssemblyController(AssemblyLineService assemblyLineService) {
        this.assemblyLineService = assemblyLineService;
    }

    @GetMapping("/assembled")
    public Flux<String> getAssembledComponents() {
        // Aquí puedes devolver los componentes ensamblados almacenados en memoria o base de datos
        return Flux.just("Component-A", "Component-B");
    }
}
