package com.agendaservice.agenda.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    @NotBlank(message ="nome do paciente obrigatorio")
    private String nome;
    @NotBlank(message ="sobrenome do paciente obrigatorio")
    private String sobrenome;
    private String email;
    @NotBlank(message ="cpf do paciente obrigatorio")
    @CPF
    private String cpf;
    @NotBlank(message ="endereço do paciente obrigatorio")
    private String endereco;
}
