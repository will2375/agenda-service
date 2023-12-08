package com.agendaservice.agenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaResponse {

    private Long id;
    private String descricao;
    private LocalDateTime horario;
    private LocalDateTime dataCriacao;
    private PacienteResponse paciente;
}
