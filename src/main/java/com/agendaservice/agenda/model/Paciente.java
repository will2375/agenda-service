package com.agendaservice.agenda.model;

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
public class Pasciente {

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

    @JsonIgnore
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Agenda> agenda;
}
