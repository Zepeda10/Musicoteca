package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ArtistaCrud {
    Conexion conectar = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Artista>datos = new ArrayList<>();
        String sql = "SELECT * FROM artista";
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Artista a = new Artista();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setAlias(rs.getString(3));
                datos.add(a);
            }
            
        }catch(Exception e){
            
        }
        return datos;
    }
}
