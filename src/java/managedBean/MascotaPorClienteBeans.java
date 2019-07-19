/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClientesDao;
import dao.MascotaDao;
import dao.MascotaporclienteDao;
import entidades.Mascotaporcliente;
import entidades.MascotaporclienteId;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@ViewScoped
public class MascotaPorClienteBeans {
   private boolean banderaSelect = false;
   private ArrayList listaCliente; 
    private ArrayList listaMascota; 
   private int idCliente;
   private int idMascota;
   private Mascotaporcliente mascotaporcliente;
   private MascotaporclienteId mascotaporclienteId;
   public  MascotaPorClienteBeans(){
    listaCliente=new ArrayList();
    mascotaporcliente=new Mascotaporcliente();
    mascotaporclienteId=new MascotaporclienteId();
     listarCombos();
}
public  void   listarCombos(){
    ClientesDao  clienteDao=new ClientesDao();
    listaCliente=clienteDao.listarCliente();
    MascotaDao  mascotaDao=new MascotaDao();
    listaMascota=mascotaDao.listarMascotas();
            
}
public String guardarMascotaporCliente() {

     try {

            MascotaporclienteDao mascotaPorClienteDao = new MascotaporclienteDao();
              mascotaporclienteId.setClienteIdCliente(idCliente);
        mascotaporclienteId.setMascotaIdMascota(idMascota);

        mascotaporcliente.setId(mascotaporclienteId);
            boolean respuesta= mascotaPorClienteDao.guardarMascotaPorCliente(mascotaporcliente);
            if(respuesta){
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("correcto", " Se registro exitoso"));
            }else{
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("error", "No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/RegistroMascotaPorCliente";
    }

    
    public ArrayList<Mascotaporcliente> listarMascotaporcliente() {
        ArrayList<Mascotaporcliente> lista = new ArrayList<>();
        MascotaporclienteDao Dao = new MascotaporclienteDao();
        lista = Dao.listarMascotaPorCliente();
        return lista;
    }

    public String eliminarMascotaporcliente() {
        MascotaporclienteDao Dao = new MascotaporclienteDao();
        boolean respuesta = Dao.eliminarMascotaPorCliente(mascotaporcliente);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/RegistroReservaCita";
    }
    
    public String limpiar() {
        return "/RegistroReservaCita";
    }
    
   public void selectBandera() {
        banderaSelect = true;
    }

    public ArrayList getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(ArrayList listaCliente) {
        this.listaCliente = listaCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public ArrayList getListaMascota() {
        return listaMascota;
    }

    public void setListaMascota(ArrayList listaMascota) {
        this.listaMascota = listaMascota;
    }

    public Mascotaporcliente getMascotaporcliente() {
        return mascotaporcliente;
    }

    public void setMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        this.mascotaporcliente = mascotaporcliente;
    }

    public MascotaporclienteId getMascotaporclienteId() {
        return mascotaporclienteId;
    }

    public void setMascotaporclienteId(MascotaporclienteId mascotaporclienteId) {
        this.mascotaporclienteId = mascotaporclienteId;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }
    
    
    
    

    
    
}
