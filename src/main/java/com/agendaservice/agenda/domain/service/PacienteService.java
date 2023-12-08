package com.agendaservice.agenda.domain.service;

import com.agendaservice.agenda.domain.model.Paciente;
import com.agendaservice.agenda.domain.repository.PacienteRepository;
import com.agendaservice.agenda.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvar(Paciente paciente) {
        boolean existeCpf = false;
        Optional<Paciente> byCpf = pacienteRepository.findByCpf(paciente.getCpf());

        if (byCpf.isPresent()) {
            if (!byCpf.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }
        }

        if (existeCpf) {
            throw new BusinessException("Cpf ja cadastrado");
        }

        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos() {

        return pacienteRepository.findAll();
    }

    public Paciente alterar(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = this.buscarPorId(id);

        if (optionalPaciente.isEmpty()) {
            throw new BusinessException("Pacente ja cadastrado");
        }

        paciente.setId(id);
        return salvar(paciente);
    }

    public Optional<Paciente> buscarPorId (Long id) {
        return pacienteRepository.findById(id);
    }

    public void deletar(Long id){
        pacienteRepository.deleteById(id);
    }

}
