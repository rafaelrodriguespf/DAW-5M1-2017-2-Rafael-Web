package br.edu.ifsul.controle;


import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.PessoaDAObkp;

import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;

import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private UnidadeCondominial unidadeCondominial;
    private Boolean novoUnidadeCondominial;
    private RecursoDAO<Recurso> daoRecurso;
    private PessoaDAO<Pessoa> daoPessoa;
    private Recurso recurso;
    
    public ControleCondominio(){
        dao = new CondominioDAO<>();
        daoRecurso = new RecursoDAO<>();
        novoUnidadeCondominial=false;
        daoPessoa = new PessoaDAO<>();
               
    }
//    public void imprimeCondominios(){
//        HashMap parametros = new HashMap();
//        UtilRelatorios.imprimeRelatorio("relatorioCondominios", parametros, dao.getListaTodos());
//    }
    
      public void imprimeCondominios (Integer id) {
        objeto = dao.localizar(id);
        List<Condominio> lista = new ArrayList<>();
        lista.add(objeto);
          System.out.println(lista);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominios", parametros, lista);
    }
    
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    public void adicionarRecurso(){
        if (recurso != null){
            if(!objeto.getCondominio_recurso().contains(recurso)){
                objeto.getCondominio_recurso().add(recurso);
                Util.mensagemInformacao("Desejo adiconado com sucesso!");
            } else{
                Util.mensagemErro("Recurso já existe na lista de desejos!");
            }
        }
    }
    
    public  void removerRecurso(int index){
        objeto.getCondominio_recurso().remove(index);
        Util.mensagemInformacao("Desejo removido com sucesso");
    }
    
    public void alterarUnidadeCondominial(int index){
        unidadeCondominial = objeto.getUnidadeCondominial().get(index);
        novoUnidadeCondominial = false;
    }
    
    public void salvarUnidadeCondominial(){
        if (novoUnidadeCondominial){
            objeto.adicionarUnidadeCondominial(unidadeCondominial);
        }
        
        Util.mensagemInformacao("UnidadeCondominial salva com sucesso!");
    }
    
    public void RemoverUnidadeCondominial(int index){
        objeto.removerUnidadeCondominial(index);
        Util.mensagemInformacao("UnidadeCondominial removido com sucesso!");
    }
    public void novoUnidadeCondominial(){
        unidadeCondominial = new UnidadeCondominial();
        novoUnidadeCondominial = true;
    }
    
    public void novo(){
        objeto = new Condominio();
        
    }
    
     public String voltar(){
        return "/index?faces-redirect=true";
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else{
            persistiu = dao.merge(objeto);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
           
        } else {
            Util.mensagemErro(dao.getMensagem());
           
        }
    }
    
   
    
    public void editar(Integer id){
        objeto = dao.localizar(id);
        
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public CondominioDAO getDao() {
        return dao;
    }

    public void setDao(CondominioDAO dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }



    /**
     * @return the unidadeCondominial
     */
    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    /**
     * @param unidadeCondominial the unidadeCondominial to set
     */
    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    /**
     * @return the novoUnidadeCondominial
     */
    public Boolean getNovoUnidadeCondominial() {
        return novoUnidadeCondominial;
    }

    /**
     * @param novoUnidadeCondominial the novoUnidadeCondominial to set
     */
    public void setNovoUnidadeCondominial(Boolean novoUnidadeCondominial) {
        this.novoUnidadeCondominial = novoUnidadeCondominial;
    }

    /**
     * @return the daoRecurso
     */
    public RecursoDAO<Recurso> getDaoRecurso() {
        return daoRecurso;
    }

    /**
     * @param daoRecurso the daoRecurso to set
     */
    public void setDaoRecurso(RecursoDAO<Recurso> daoRecurso) {
        this.daoRecurso = daoRecurso;
    }

    /**
     * @return the recurso
     */
    public Recurso getRecurso() {
        return recurso;
    }

    /**
     * @param recurso the recurso to set
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    /**
     * @return the daoPessoa
     */
    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    /**
     * @param daoPessoa the daoPessoa to set
     */
    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }
}
