                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;
import dao.AtencionDao;
import dao.ClientesDao;
import dao.MascotaDao;
import dao.PersonalDao;
import dao.TipoAtencionDao;
import entidades.Atencion;
import entidades.Cliente;
import entidades.Mascota;
import entidades.Mascotaporcliente;
import entidades.MascotaporclienteId;

import entidades.Personal;
import entidades.Tipoatencion;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Computer
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class AtencionBeans implements Serializable {

    private ArrayList listaMascota;
    private ArrayList listaCliente;
    private ArrayList listaPersonal;
    private ArrayList listaTipoAtencion;
    private int idMascota;
    private int idCliente;
    private int idPersonal;
    private int idTipoAtencion;
    private Mascota mascota;
    private Cliente cliente;
    private Personal personal;
    private Tipoatencion tipoatencion;
   

    private Mascotaporcliente mascotaporcliente;
    private MascotaporclienteId mascotaporclienteId;

    private Atencion atencion;
    private boolean banderaSelect = false;

    private Date date = new Date();

    public AtencionBeans() {
        this.atencion = new Atencion();
        listaMascota = new ArrayList();
        listaCliente = new ArrayList();
        listaPersonal = new ArrayList();
        listaTipoAtencion = new ArrayList();
        mascota = new Mascota();
        cliente = new Cliente();
        personal = new Personal();
        tipoatencion = new Tipoatencion();
        mascotaporcliente=new Mascotaporcliente();
        mascotaporclienteId=new MascotaporclienteId();
       
        combosSeleccion();
    }

    public void combosSeleccion() {
        MascotaDao mascotaDao = new MascotaDao();
        listaMascota = mascotaDao.listarMascotas();
        ClientesDao clienteDao = new ClientesDao();
        listaCliente = clienteDao.listarCliente();
        PersonalDao personalDao = new PersonalDao();
        listaPersonal = personalDao.listarPersonal();
        TipoAtencionDao tipoatencionDao = new TipoAtencionDao();
        listaTipoAtencion = tipoatencionDao.listarTipoatencion();
    }

    public String guardarAtencion() {
        try {
      
                AtencionDao atencionDao = new AtencionDao();
                mascotaporclienteId.setMascotaIdMascota(idMascota);
                mascotaporclienteId.setClienteIdCliente(idCliente);
                mascotaporcliente.setId(mascotaporclienteId);
                personal.setIdPersonal(idPersonal);
                tipoatencion.setIdTipoAtencion(idTipoAtencion);

                atencion.setMascotaporcliente(mascotaporcliente);
                atencion.setPersonal(personal);
                atencion.setTipoatencion(tipoatencion);

                    DateFormat hora = new SimpleDateFormat("HH:mm:ss");
                    String stringHora = hora.format(date);
                    Date convertido = hora.parse(stringHora);
                    System.out.println(convertido);
                    atencion.setHoraAtencion(convertido);
          
                boolean respuesta = atencionDao.guardarAtencion(atencion);
                if (respuesta) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
                }
         

        } catch (ParseException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Atencion";
    }

    public String actualizarAtencion() {
        try {
            AtencionDao atencionDao = new AtencionDao();
            boolean respuesta = atencionDao.ActualizarAtencion(atencion);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Atencion";

    }

    public ArrayList<Atencion> listarAtenciones() {
        ArrayList<Atencion> lista = new ArrayList<>();
        AtencionDao atencionDao = new AtencionDao();
        lista = atencionDao.listarAtencion();
        return lista;
    }

    public String eliminar() {
        AtencionDao atencionDao = new AtencionDao();
        boolean respuesta = atencionDao.eliminarAtencion(atencion);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Atencion";
    }

    public String limpiar() {
        return "/Atencion";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public ArrayList getListaMascota() {
        return listaMascota;
    }

    public void setListaMascota(ArrayList listaMascota) {
        this.listaMascota = listaMascota;
    }

    public ArrayList getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(ArrayList listaCliente) {
        this.listaCliente = listaCliente;
    }

    public ArrayList getListaPersonal() {
        return listaPersonal;
    }

    public void setListaPersonal(ArrayList listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    public ArrayList getListaTipoAtencion() {
        return listaTipoAtencion;
    }

    public void setListaTipoAtencion(ArrayList listaTipoAtencion) {
        this.listaTipoAtencion = listaTipoAtencion;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdTipoAtencion() {
        return idTipoAtencion;
    }

    public void setIdTipoAtencion(int idTipoAtencion) {
        this.idTipoAtencion = idTipoAtencion;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Tipoatencion getTipoatencion() {
        return tipoatencion;
    }

    public void setTipoatencion(Tipoatencion tipoatencion) {
        this.tipoatencion = tipoatencion;
    }

  
    public Atencion getAtencion() {
        return atencion;
    }

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

    public Mascotaporcliente getMascotaporcliente() {
        return mascotaporcliente;
    }

    public void setMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        this.mascotaporcliente = mascotaporcliente;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
