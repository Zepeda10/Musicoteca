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
    
    public int agregarAlbum(Album al){
        String sql = " INSERT INTO album (titulo,anio,genero,id_artista) VALUES (?,?,?,?) ";     
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,al.getTitulo());
            ps.setInt(2,al.getAnio());
            ps.setString(3,al.getGenero());
            ps.setInt(4,al.getId_artista());
            ps.executeUpdate();
        }catch(Exception e){
            
        }
        return 1;
    }
    
    public int actualizaAlbum(Album al){
        int resultado =0;
        String sql = "UPDATE album SET titulo=?, anio=?, genero=?, id_artista=? WHERE id_album=?";
        
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,al.getTitulo());
            ps.setInt(2,al.getAnio());
            ps.setString(3,al.getGenero());
            ps.setInt(4,al.getId_artista());
            ps.setInt(5,al.getId());
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
    
    public void eliminarAlbum(int id){
        String sql = "DELETE FROM album WHERE id_album ="+id;
        try{
            conn = conectar.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
