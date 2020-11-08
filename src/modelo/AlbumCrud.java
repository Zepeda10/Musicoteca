package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlbumCrud {
    Conexion conectar = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Album>datos = new ArrayList<>();
        String sql = "SELECT * FROM album";
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Album a = new Album();
                a.setId(rs.getInt(1));
                a.setTitulo(rs.getString(2));
                a.setAnio(rs.getInt(3));
                a.setGenero(rs.getString(4));
                a.setId_artista(rs.getInt(5));
                datos.add(a);
            }
            
        }catch(Exception e){
            
        }
        return datos;
    }
    
}
