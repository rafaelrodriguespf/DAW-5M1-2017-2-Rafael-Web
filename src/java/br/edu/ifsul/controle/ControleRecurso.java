package br.edu.ifsul.controle;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@ManagedBean(name = "controleRecurso")
@SessionScoped
public class ControleRecurso implements Serializable {

    private RecursoDAO dao;
    private Recurso objeto;
    
    public ControleRecurso(){
        dao = new RecursoDAO();
    }
    
    public String listar(){
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Recurso();
        return "formulario?faces-redirect=true";
    }
    
     public String voltar(){
        return "/index?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public RecursoDAO getDao() {
        return dao;
    }

    public void setDao(RecursoDAO dao) {
        this.dao = dao;
    }

    public Recurso getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }
}
