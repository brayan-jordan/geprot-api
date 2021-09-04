package net.weg.gestor.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    Long id;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "secoes_id")
    private Secao secao;

    @NotBlank
    @Size(max = 100, min = 3)
    String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 100)
    private String senha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "consultores_alocados", joinColumns =
        @JoinColumn(name = "usuarios_id", referencedColumnName = "id"), inverseJoinColumns =
        @JoinColumn(name = "projetos_id", referencedColumnName = "id"))
    private List<Projeto> projetos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "role_usuarios", joinColumns = @JoinColumn(name = "usuarios_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_nome", referencedColumnName = "nome"))
    private List<Role> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return (Collection<? extends GrantedAuthority>) this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
