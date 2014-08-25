/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ementas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author alex
 */
@Entity
public class Usuario extends AbstractEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name="IDUSUARIO")
    private Long id;
    
    @Column(name="NOME")
    private String nome;
    
    @Column(name="USUARIO")
    private String usuario;
    
    @Column(name="SENHA")
    private String senha;
    
    @ManyToMany
    @JoinTable(name = "USUARIO_PERFIL", joinColumns = { @JoinColumn(name = "IDUSUARIO") }, inverseJoinColumns = { @JoinColumn(name = "IDPERFIL") })
    private List<Perfil> perfis;
    
    @Column(name="ATIVO")
    private String ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	

          
}
