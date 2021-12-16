/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modells.ProductoModelo;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Modells.Conexion;
import java.sql.ResultSet;

/**
 *
 * @author edd_g
 */
public class productoControl {
    private Conexion conectar;
    private ProductoModelo producModel;
    private Connection con;
    
    public productoControl()
    {
        conectar = new Conexion();
        producModel = new ProductoModelo();
    }
    
    public void Insertar_img(String barras, String codigo, String modelo,String color, String talla, String size, byte [] imagen)
    {
        PreparedStatement ps;
        String sql;
        producModel.setId_barras(barras);
        producModel.setCodigo(codigo);
        producModel.setModelo(modelo);
        producModel.setColor(color);
        producModel.setTalla(talla);
        producModel.setSize(size);
        producModel.setImagen(imagen);
        
        try
        {
            con = conectar.getConnection();
            sql ="INSERT INTO productos(id_barras,codigo,modelo,color,talla,size,imagen) values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, producModel.getId_barras());
            ps.setString(2, producModel.getCodigo());
            ps.setString(3, producModel.getModelo());
            ps.setString(4, producModel.getColor());
            ps.setString(5, producModel.getTalla());
            ps.setString(6, producModel.getSize());
            ps.setBytes(7, producModel.getImagen());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
            

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
    }
    public void Insertar(String barras, String codigo, String modelo,String color, String talla, String size)
    {
        PreparedStatement ps;
        String sql;
        producModel.setId_barras(barras);
        producModel.setCodigo(codigo);
        producModel.setModelo(modelo);
        producModel.setColor(color);
        producModel.setTalla(talla);
        producModel.setSize(size);
        

        
        try
        {
            con = conectar.getConnection();
            sql ="INSERT INTO productos(id_barras,codigo,modelo,color,talla,size) values(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, producModel.getId_barras());
            ps.setString(2, producModel.getCodigo());
            ps.setString(3, producModel.getModelo());
            ps.setString(4, producModel.getColor());
            ps.setString(5, producModel.getTalla());
            ps.setString(6, producModel.getSize());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
    }
    
    
    public ProductoModelo consultar(String id)
    {
        Statement ps;
        
        ResultSet resultado = null;
        String sql = "SELECT  * FROM productos WHERE id_barras  = " + id;
        try
        {
         con = conectar.getConnection();
         ps = con.createStatement();
         resultado = ps.executeQuery(sql);
         resultado.next();
         producModel.setId(resultado.getInt("id"));
         producModel.setId_barras(resultado.getString("id_barras"));
         producModel.setCodigo(resultado.getString("codigo"));
         producModel.setModelo(resultado.getString("modelo"));
         producModel.setColor(resultado.getString("color"));
         producModel.setTalla(resultado.getString("talla"));
         producModel.setSize(resultado.getString("size"));
         producModel.setImagen(resultado.getBytes("imagen"));
         
       
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Producto no encontrado: Producto control");
            producModel.setId_barras("");
            producModel.setCodigo("");
            producModel.setModelo("");
            producModel.setColor("");
            producModel.setTalla("");
            producModel.setSize("");
            producModel.setImagen(null);
        }

        return producModel;
    }
    
    public ProductoModelo consultar(int id)
    {
        Statement ps;
        
        ResultSet resultado = null;
        String sql = "SELECT  * FROM productos WHERE id =" + Integer.toString(id);
        try
        {
         con = conectar.getConnection();
         ps = con.createStatement();
         resultado = ps.executeQuery(sql);
         resultado.next();
         producModel.setId(resultado.getInt("id"));
         producModel.setId_barras(resultado.getString("id_barras"));
         producModel.setCodigo(resultado.getString("codigo"));
         producModel.setModelo(resultado.getString("modelo"));
         producModel.setColor(resultado.getString("color"));
         producModel.setTalla(resultado.getString("talla"));
         producModel.setSize(resultado.getString("size"));
         producModel.setImagen(resultado.getBytes("imagen"));
         
       
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Producto no encontrado: Producto control");
            producModel.setId_barras("");
            producModel.setCodigo("");
            producModel.setModelo("");
            producModel.setColor("");
            producModel.setTalla("");
            producModel.setSize("");
            producModel.setImagen(null);
        }

        return producModel;
    }
    
    public ArrayList precarga ()
    {
        ArrayList<String> data = new ArrayList<String>();
        Statement ps;
        ResultSet resultado = null;
        String sql = "SELECT id_barras FROM productos";
        try
        {
            con = conectar.getConnection();
            ps = con.createStatement();
            resultado = ps.executeQuery(sql);
            
            while(resultado.next())
            {
                data.add(resultado.getString("id_barras"));
                
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e+"   Producto control");
        }
        
        return data;
    }
    
    public int update( int id, String barras, String codigo, String modelo,String color, String talla, String size,byte [] imagen)
    {
         int respuesta = 0;
        try
        {
            PreparedStatement ps;
            con = conectar.getConnection();
            String sql = "UPDATE productos SET id_barras =?, codigo=?, modelo=?, color=?,talla=?,size=?,imagen=?"
                    + " WHERE id =?";
            ps = con.prepareStatement(sql);
            ps.setString(1, barras);
            ps.setString(2,codigo);
            ps.setString(3,modelo);
            ps.setString(4,color);
            ps.setString(5, talla);
            ps.setString(6, size);
            ps.setBytes(7, imagen);
            ps.setInt(8,id);
            respuesta = ps.executeUpdate();
           
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage()+"   Producto control-update");
        }
         return respuesta;
    }
     
    
    public int Eliminar(int id)
    {
        int respuesta = 0;
        try
        {
            PreparedStatement ps;
            con = conectar.getConnection();
            String sql = "DELETE FROM productos WHERE id =?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            respuesta = ps.executeUpdate();
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e+"   Producto control-Elimnar");
        }
        return respuesta;
    }
}
