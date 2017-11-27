/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;
import br.edu.ifsul.modelo.Condominio;
import java.io.Serializable;

public class CondominioDAO<T> extends DAOGenerico<Condominio> implements Serializable{
   
    public CondominioDAO(){
        super();
        this.classePersistencia = Condominio.class;
        this.ordem = "nome";
    }
}
