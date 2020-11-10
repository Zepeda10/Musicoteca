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
    
    public int agregarArtista(Artista ar){
        String sql = " INSERT INTO artista (nombre,alias) VALUES (?,?) ";     
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,ar.getNombre());
            ps.setString(2,ar.getAlias());
            ps.executeUpdate();
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
        return 1;
    }
    
    public int actualizaArtista(Artista ar){
        int resultado =0;
        String sql = "UPDATE artista SET nombre=?, alias=? WHERE id_artista=?";
        
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,ar.getNombre());
            ps.setString(2,ar.getAlias());
            ps.setInt(3,ar.getId());
            resultado = ps.executeUpdate(); 
            
            if(resultado==1){
                return 1;
            }else{
                return 0;
            }
                     
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
        return resultado;
    }
    
    public void eliminarArtista(int id){
        String sql = "DELETE FROM artista WHERE id_artista ="+id;
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
