package br.edu.ifsul.controle;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.modelo.Locatario;
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
@ManagedBean(name = "controleLocatario")
@SessionScoped
public class ControleLocatario implements Serializable {

    private LocatarioDAO dao;
    private Locatario objeto;
    
    public ControleLocatario(){
        dao = new LocatarioDAO();
    }
    
    public String listar(){
        return "/privado/locatario/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Locatario();
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

    public LocatarioDAO getDao() {
        return dao;
    }

    public void setDao(LocatarioDAO dao) {
        this.dao = dao;
    }

    public Locatario getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }
}
