package Modells;
import Views.Program;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author edd_g
 */
public class Conexion 
{
    public static  String URL = "jdbc:mysql://192.168.1.72:3306/empresas_csp";
//    public static final String port="";
//    public static final String database="";
//    public static final String USER = "csp";
//    public static final String CLAVE = "csp_DataBase";
    
    public Connection getConnection()
    {
        Connection con = null;
        try{
                File file = new File("./env");
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String[] data = new String[5];
                String temp = "";
                int i = 0 ;
                while((temp = bf.readLine()) != null)
                {
                    data[i] = temp;
                    i++;
                }                       
                bf.close();

                URL ="jdbc:mysql://"+data[0]+":"+data[1]+"/"+data[2];
                 
            Class.forName("com.mysql.jdbc.Driver");
            con =  DriverManager.getConnection(URL, data[3], data[4]);
        }catch(Exception e )
        {
            System.out.println("Error: " + e.getMessage()+" Conexion.java");
        }
        return con;
    }
}
