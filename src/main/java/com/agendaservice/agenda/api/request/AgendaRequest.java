package com.agendaservice.agenda.api.request;

import com.agendaservice.agenda.api.response.PacienteResponse;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {
    @NotBlank
    private String descricao;
    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime horario;
    @NotNull
    private Long pacienteId;
}
