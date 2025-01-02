package br.com.xavecoding.regescweb.dto;

import br.com.xavecoding.regescweb.models.Professor;
import br.com.xavecoding.regescweb.models.StatusProfessor;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// é uma classe dto: data transfer object
public class RequisicaoFormProfessor {

    @NotNull(message = "O nome é obrigatório.")
    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotNull(message = "O status do professor é obrigatório.")
    private StatusProfessor statusProfessor;

    @NotNull(message = "O salário é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero.")
    private BigDecimal salario;

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }

    public Professor toProfessor() {
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setStatusProfessor(this.statusProfessor);
        professor.setSalario(this.salario);

        return professor;
    }

    public void fromProfessor(Professor professor) {
        this.nome = professor.getNome();
        this.statusProfessor = professor.getStatusProfessor();
        this.salario = professor.getSalario();
    }


    @Override
    public String toString() {
        return "RequisicaoFormProfessor{" +
                "nome='" + nome + '\'' +
                ", statusProfessor=" + statusProfessor +
                ", salario=" + salario +
                '}';
    }
}
