/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyecto2p;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ariel
 */
public class Usuario implements Serializable{
    private String correoe;
    private String contrasena;
    private static final long serialVersionUID = 8799656478674716638L;

    public Usuario(String correoe, String contrasena) {
        this.correoe = correoe;
        this.contrasena = contrasena;
    }

    public String getCorreoe() {
        return correoe;
    }

    public void setCorreoe(String correoe) {
        this.correoe = correoe;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        
        return user;
    }

    @Override
    public String toString() {
        return "Usuario{" + "correoe=" + correoe + ", contrasena=" + contrasena + '}';
    }
    
    
}
