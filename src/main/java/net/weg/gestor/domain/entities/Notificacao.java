package net.weg.gestor.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    @JoinColumn(name = "usuarios_id")
    @ManyToOne
    private Usuario usuario;

    private LocalDate data;

    private boolean statusLeitura;

    public Notificacao(String descricao, Usuario usuario, LocalDate data, boolean statusLeitura) {
        this.descricao = descricao;
        this.usuario = usuario;
        this.data = data;
        this.statusLeitura = statusLeitura;
    }
}
