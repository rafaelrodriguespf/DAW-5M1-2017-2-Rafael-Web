package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class RecursoDAO<T> extends DAOGenerico<Recurso> implements Serializable {
    
     public RecursoDAO(){
       super();
       classePersistencia = Recurso.class;
       this.ordem = "nome";
   
   }
   

}

