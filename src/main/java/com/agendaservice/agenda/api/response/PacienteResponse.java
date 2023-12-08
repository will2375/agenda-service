package com.agendaservice.agenda.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
    private String endereco;
}
