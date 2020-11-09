package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Artista;
import modelo.ArtistaCrud;
import vista.VistaArtista;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Album;
import modelo.AlbumCrud;
import vista.VistaAlbum;

public class Controlador implements ActionListener {
    //artista
    ArtistaCrud art = new ArtistaCrud();
    Artista a = new Artista();
    VistaArtista visArt = new VistaArtista();
    DefaultTableModel tablaArt = new DefaultTableModel();
    
    //álbum
    AlbumCrud albm = new AlbumCrud();
    Album al = new Album();
    VistaAlbum visAlbm = new VistaAlbum();
    DefaultTableModel tablaAlbm = new DefaultTableModel();
    
    public Controlador(VistaArtista va, VistaAlbum valb){
        this.visArt = va;
        this.visAlbm = valb;
        this.visAlbm.btnListar.addActionListener(this);
        this.visArt.btnListar.addActionListener(this);
        
        this.visArt.btnGuardarAr.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==visArt.btnListar){
            listarArtista(visArt.tablaArtista);
        } 
        if(ae.getSource()==visAlbm.btnListar){
            agregarArtista();
        }
        
        if(ae.getSource()==visArt.btnGuardarAr){
            agregarArtista();
        } 
    }
    
        public void agregarArtista(){
        String nombre = visArt.txtNombre.getText();
        String alias = visArt.txtAlias.getText();
        a.setNombre(nombre);
        a.setAlias(alias);
        int resultado = art.agregarArtista(a);
        
        if(resultado==1){
            JOptionPane.showMessageDialog(visArt, "Artista agregado");
        }else{
            JOptionPane.showMessageDialog(visArt, "Error");
        }
        
    }
    
    
    //Método para mostrar tabla Artista al ejecutar
    public void listarArtista(JTable tabla){
        tablaArt = (DefaultTableModel)tabla.getModel();
        List<Artista>listaArtista = art.listar();
        Object[]objeto = new Object[3];
        
        for (int i = 0; i < listaArtista.size(); i++) {
            objeto[0] = listaArtista.get(i).getId();
            objeto[1] = listaArtista.get(i).getNombre();
            objeto[2] = listaArtista.get(i).getAlias();

            tablaArt.addRow(objeto);
        }
        visArt.tablaArtista.setModel(tablaArt);
    }
    

     //Método para mostrar tabla Album al ejecutar
    public void listarAlbum(JTable tabla){
        tablaAlbm = (DefaultTableModel)tabla.getModel();
        List<Album>listaAlbum = albm.listar();
        Object[]objeto2 = new Object[5];
        
        for (int i = 0; i < listaAlbum.size(); i++) {
            objeto2[0] = listaAlbum.get(i).getId();
            objeto2[1] = listaAlbum.get(i).getTitulo();
            objeto2[2] = listaAlbum.get(i).getAnio();
            objeto2[3] = listaAlbum.get(i).getGenero();
            objeto2[4] = listaAlbum.get(i).getId_artista();
            
            tablaAlbm.addRow(objeto2);
        }
        visAlbm.tablaAlbum.setModel(tablaAlbm);
    }
    
    
}
