package com.agendaservice.agenda.api.controller;

import com.agendaservice.agenda.api.mapper.PacienteMapper;
import com.agendaservice.agenda.api.request.PacienteRequest;
import com.agendaservice.agenda.api.response.PacienteResponse;
import com.agendaservice.agenda.domain.model.Paciente;
import com.agendaservice.agenda.domain.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {


    @Autowired
    private PacienteService service;
    private final PacienteMapper mapper;

    public PacienteController(PacienteMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest request) {

        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponse> pacienteResponses = mapper.toPacienteList(pacientes);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> pacienteId = service.buscarPorId(id);
        if (pacienteId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(pacienteId.get()));
    }

    @PutMapping
    public ResponseEntity<PacienteResponse> alterar(@Valid @PathVariable Long id,  @RequestBody PacienteRequest request) {
        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.alterar(id, paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
