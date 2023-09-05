package ec.edu.espol.proyecto2p.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Usuario implements Serializable{
    private String correoe;
    private String contrasena;
    private String nombres;
    private String apellidos;
    private String organizacion;
    private static final long serialVersionUID = 8799656478674716638L;

    public Usuario() {
    }

    public Usuario(String correoe, String contrasena, String nombres, String apellidos, String organizacion) {
        this.correoe = correoe;
        this.contrasena = contrasena;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.organizacion= organizacion;
    }

    public String getCorreoe() {
        return correoe;
    }

    public void setCorreoe(String correousuario) {
        this.correoe = correousuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
    
    
    public static void saveSer(String n, ArrayList<Usuario> user){
        try(ObjectOutputStream bo = new ObjectOutputStream(new FileOutputStream(n))){
            bo.writeObject(user);
        }catch(IOException e){}
    }
    
    public static ArrayList<Usuario> readSer(String n){
        ArrayList<Usuario> user = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(n))){
            user = (ArrayList<Usuario>)in.readObject();
        }catch(IOException e){
        }catch(ClassNotFoundException c){}
        
        System.out.println(user);
        return user;
    }

    @Override
    public String toString() {
        return "Usuario{" + "correoe=" + correoe + ", contrasena=" + contrasena + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o== null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Usuario other = (Usuario) o;
        if (!Objects.equals(this.correoe, other.correoe)) {
            return false;
        }
        return Objects.equals(this.contrasena, other.contrasena);
    }
    
    
}
