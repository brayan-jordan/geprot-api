package net.weg.gestor.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 100)
    private String senha;

    @ManyToMany
    @JoinTable(name = "role_usuarios", joinColumns = @JoinColumn(name = "usuarios_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nome_role", referencedColumnName = "nome"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return (Collection<? extends GrantedAuthority>) this.roles;
    }

    @Override
    public String getPassword(){ return this.senha; }

    @Override
    public String getUsername(){ return this.email; }

    @Override
    public boolean isAccountNonExpired(){ return true; }

    @Override
    public boolean isAccountNonLocked(){ return true; }

    @Override
    public boolean isCredentialsNonExpired(){ return true; }

    @Override
    public boolean isEnabled(){ return true; }

}
