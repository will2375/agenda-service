package com.agendaservice.agenda.api.mapper;

import com.agendaservice.agenda.api.request.PacienteRequest;
import com.agendaservice.agenda.api.response.PacienteResponse;
import com.agendaservice.agenda.domain.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request) {
        return mapper.map(request, Paciente.class);
    }

//    public static Paciente toPaciente(PacienteRequest request) {
//        Paciente paciente = new Paciente();
//        paciente.setNome(request.getNome());
//        paciente.setSobrenome(request.getSobrenome());
//        paciente.setCpf(request.getCpf());
//        paciente.setEndereco(request.getEndereco());
//        return paciente;
//    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

//    public static PacienteResponse toPacienteResponse(Paciente paciente) {
//        PacienteResponse response = new PacienteResponse();
//        response.setId(paciente.getId());
//        response.setNome(paciente.getNome());
//        response.setSobrenome(paciente.getSobrenome());
//        response.setCpf(paciente.getCpf());
//        response.setEndereco(paciente.getEndereco());
//        return response;
//    }

    public List<PacienteResponse> toPacienteList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toPacienteResponse)
                .collect(Collectors.toList());
    }

//    public static List<PacienteResponse> toPacienteList(List<Paciente> pacientes) {
//        List<PacienteResponse> responses = new ArrayList<>();
//        for (Paciente paciente : pacientes) {
//            responses.add(toPacienteResponse(paciente));
//        }
//
//        return responses;
//    }
}
