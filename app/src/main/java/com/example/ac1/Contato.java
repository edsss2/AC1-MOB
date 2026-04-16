package com.example.ac1;

public class Contato {

    public Integer id;
    public String nome;
    public String telefone;
    public String email;
    public String categoria;
    public String cidade;
    public Boolean favorito;

    @Override
    public String toString() {
        return "nome= '" + nome + '\'' +
                ", telefone= " + telefone + '\'' +
                ", email= '" + email + '\'' +
                ", categoria= '" + categoria + '\'' +
                ", cidade= '" + cidade + "'";
    }
}
