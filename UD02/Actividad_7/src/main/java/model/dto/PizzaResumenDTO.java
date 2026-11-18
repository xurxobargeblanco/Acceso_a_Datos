package model.dto;

import java.math.BigDecimal;

public class PizzaResumenDTO {
    private String nome;
    private BigDecimal prezo;

    public PizzaResumenDTO(String nome, BigDecimal prezo) {
        this.nome = nome;
        this.prezo = prezo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrezo() {
        return prezo;
    }

    public void setPrezo(BigDecimal prezo) {
        this.prezo = prezo;
    }

    @Override
    public String toString() {
        return "PizzaResumenDTO{" +
                "nome='" + nome + '\'' +
                ", prezo=" + prezo +
                '}';
    }
}
