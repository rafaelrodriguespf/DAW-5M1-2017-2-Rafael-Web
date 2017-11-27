/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Rafael
 */
public class DAOGenerico<T> {
    private List<T> listaObjetos;
    private List<T> listaTodos;
    protected EntityManager em;
    protected Class classePersistencia;
    protected String mensagem = "";
    protected String ordem = "id";
    protected String filtro ="";
    protected Integer maximoObjeto = 2;
    protected Integer posicaoAtual =0;
    protected Integer totalObjetos =0;
    protected Calendar dataIni;
    protected Calendar dataFin;
    
    
    
    public DAOGenerico(){
        em = EntityManagerUtil.getEntityManager();
    }

    /**
     * @return the listaObjetos
     */
    public List<T> getListaObjetos() {
        String jpql = "from "+ classePersistencia.getSimpleName();
        String where ="";
        filtro = filtro.replaceAll("[';-]", "");// remover caracteres para proteger de injecao  sql
        if(filtro.length() >0){
            if(ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem +" = '" + filtro +"' "; 
                } catch (Exception e) {
                }
            } else{
                where += " where upper("+ ordem+") like '"+ filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjeto).getResultList();
    }
    
     public List<T> getListaTodos() {
        String jpql = " from "+ classePersistencia.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
     
   
     
    public void primeiro(){
        posicaoAtual =0;
    } 
    public void anterior(){
        posicaoAtual -= maximoObjeto;
        if (posicaoAtual <0){
            posicaoAtual =0;
        }
    }
    public void proximo(){
        if(posicaoAtual + maximoObjeto < totalObjetos){
            posicaoAtual += maximoObjeto;
        }
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoObjeto;
        if (resto > 0){
            posicaoAtual = totalObjetos - resto;
        }else{
            posicaoAtual = totalObjetos - maximoObjeto;
        }
    }
    
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoObjeto;
        if (ate > totalObjetos){
            ate = totalObjetos;
        }
        return "Listando de "+ (posicaoAtual +1) + " até " +ate + " de " +  totalObjetos+ " registros";
    }
     
    public void roolback(){
        if (em.getTransaction().isActive() == false){
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
    } 
    
    public boolean persist(T obj){
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso";
            return true;
        } catch (Exception e) {
            roolback();
            mensagem = "Erro ao persistir objeto" + Util.getMensagemErro(e);
            return false;
        }
    }
    
       public boolean merge(T obj){
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso";
            return true;
        } catch (Exception e) {
            roolback();
            mensagem = "Erro ao persistir objeto" + Util.getMensagemErro(e);
            return false;
        }
    }
       
       public boolean remover(T obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso";
            return true;
        } catch (Exception e) {
            roolback();
            mensagem = "Erro ao remover o objeto" + Util.getMensagemErro(e);
            return false;
        }
    }  
    
    public T localizar (Object id){
        roolback();
        T obj = (T)em.find(classePersistencia, id);
        return obj;
    }
       

    /**
     * @param listaObjetos the listaObjetos to set
     */
    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * @return the listaTodos
     */
   

    /**
     * @param listaTodos the listaTodos to set
     */
    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the classePersistencia
     */
    public Class getClassePersistencia() {
        return classePersistencia;
    }

    /**
     * @param classePersistencia the classePersistencia to set
     */
    public void setClassePersistencia(Class classePersistencia) {
        this.classePersistencia = classePersistencia;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the ordem
     */
    public String getOrdem() {
        return ordem;
    }

    /**
     * @param ordem the ordem to set
     */
    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    /**
     * @return the filtro
     */
    public String getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the maximoObjeto
     */
    public Integer getMaximoObjeto() {
        return maximoObjeto;
    }

    /**
     * @param maximoObjeto the maximoObjeto to set
     */
    public void setMaximoObjeto(Integer maximoObjeto) {
        this.maximoObjeto = maximoObjeto;
    }

    /**
     * @return the posicaoAtual
     */
    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    /**
     * @param posicaoAtual the posicaoAtual to set
     */
    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    /**
     * @return the totalObjetos
     */
    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    /**
     * @param totalObjetos the totalObjetos to set
     */
    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }

    /**
     * @return the dataIini
     */
    public Calendar getDataIni() {
        return dataIni;
    }

    /**
     * @param dataIini the dataIini to set
     */
    public void setDataIni(Calendar dataIini) {
        this.dataIni = dataIini;
    }

    /**
     * @return the dataFin
     */
    public Calendar getDataFin() {
        return dataFin;
    }

    /**
     * @param dataFin the dataFin to set
     */
    public void setDataFin(Calendar dataFin) {
        this.dataFin = dataFin;
    }
    
    
 }
