package com.agendaservice.agenda.domain.service;

import com.agendaservice.agenda.domain.model.Agenda;
import com.agendaservice.agenda.domain.model.Paciente;
import com.agendaservice.agenda.domain.repository.AgendaRepository;
import com.agendaservice.agenda.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private PacienteService pacienteService;

    public List<Agenda> listarTodos() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda salvar(Agenda agenda) {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if (optionalPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> byHorario = agendaRepository.findByHorario(agenda.getHorario());

        if (byHorario.isPresent()) {
            throw new BusinessException("Ja existe agendaento para esse horario");
        }

        agenda.setPaciente(optionalPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());

        return agendaRepository.save(agenda);

    }


}
