package br.edu.ifsul.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class EntityManagerUtil {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("DAW-5M1-2017-2-Rafael-ModelPU");
        }
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

}
