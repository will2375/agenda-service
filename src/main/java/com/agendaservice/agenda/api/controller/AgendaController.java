package com.agendaservice.agenda.api.controller;

import com.agendaservice.agenda.api.mapper.AgendaMapper;
import com.agendaservice.agenda.api.request.AgendaRequest;
import com.agendaservice.agenda.api.response.AgendaResponse;
import com.agendaservice.agenda.domain.model.Agenda;
import com.agendaservice.agenda.domain.service.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService service;

    private final AgendaMapper mapper;

    public AgendaController(AgendaMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos() {
        List<Agenda> agenda = service.listarTodos();
        List<AgendaResponse> agendaResponse = mapper.toAgendaResponseList(agenda);

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Agenda> agenda = service.buscarPorId(id);

        if (agenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = mapper.toAgendaResponse(agenda.get());

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);

    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest request) {
        Agenda agenda = mapper.toAgenda(request);
        Agenda agendaSalva = service.salvar(agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }
}
