package com.agendaservice.agenda.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String sobrenome;
    @Column
    private String email;
    @Column
    private String cpf;
    @Column
    private String endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Agenda> agendas;
}
