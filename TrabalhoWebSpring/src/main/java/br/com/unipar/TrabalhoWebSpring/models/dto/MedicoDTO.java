package br.com.unipar.TrabalhoWebSpring.models.dto;

import br.com.unipar.TrabalhoWebSpring.enums.EspecialidadeEnum;
import br.com.unipar.TrabalhoWebSpring.models.Endereco;
import br.com.unipar.TrabalhoWebSpring.models.Medico;

public class MedicoDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String CRM;
    private EspecialidadeEnum especialidade;
    private Long endereco;
    private boolean stAtivo;

    public MedicoDTO() {
    }

    public MedicoDTO (Medico medico){
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNome(medico.getNome());
        medicoDTO.setEndereco(medico.getEndereco().getId());
        medicoDTO.setCRM(medico.getCRM());
        medicoDTO.setEmail(medico.getEmail());
        medicoDTO.setTelefone(medico.getTelefone());
        medicoDTO.setEspecialidade(medico.getEspecialidade());
        medicoDTO.setStAtivo(medico.getStAtivo());
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public EspecialidadeEnum getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEnum especialidade) {
        this.especialidade = especialidade;
    }

    public Long getEndereco() {
        return endereco;
    }

    public void setEndereco(Long endereco) {
        this.endereco = endereco;
    }

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }
}
