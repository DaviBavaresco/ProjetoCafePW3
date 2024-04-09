package br.edu.ifrs.projetocafe.entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
@Entity
public class Cafe implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    private String descricao;
    private String valor;

    public Cafe(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
