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
        this.visAlbm.btnGuardarAl.addActionListener(this);
        
        this.visArt.btnEditarAr.addActionListener(this);
        this.visArt.btnOkAr.addActionListener(this);
        this.visAlbm.btnEditarAl.addActionListener(this);
        this.visAlbm.btnOkAl.addActionListener(this);
        
        this.visArt.btnEliminarAr.addActionListener(this);
        this.visAlbm.btnEliminarAl.addActionListener(this);
          
        listarArtista(visArt.tablaArtista);
        listarAlbum(visAlbm.tablaAlbum);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==visArt.btnListar){
            limpiarTablaArt();
            listarArtista(visArt.tablaArtista);
        } 
        if(ae.getSource()==visAlbm.btnListar){
            limpiarTablaAlbm();
            listarAlbum(visAlbm.tablaAlbum);
        }
        
        if(ae.getSource()==visArt.btnGuardarAr){
            agregarArtista();
            limpiarTablaArt();
            listarArtista(visArt.tablaArtista);
        } 
        if(ae.getSource()==visAlbm.btnGuardarAl){
            agregarAlbum();
            limpiarTablaAlbm();
            listarAlbum(visAlbm.tablaAlbum);
        }
        if(ae.getSource()==visArt.btnEditarAr){
            int fila = visArt.tablaArtista.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(visArt,"Debe seleccionar un registro");
            }else{
                int id_artista = Integer.parseInt((String) visArt.tablaArtista.getValueAt(fila, 0).toString());
                String nombre =  (String) visArt.tablaArtista.getValueAt(fila,1);
                String alias =  (String) visArt.tablaArtista.getValueAt(fila,2);
                visArt.txtId.setText(""+id_artista);
                visArt.txtNombre.setText(nombre);
                visArt.txtAlias.setText(alias);
            }
        }
        
        if(ae.getSource()==visArt.btnOkAr){
            actualizarArtista();
            limpiarTablaArt();
            listarArtista(visArt.tablaArtista);
        }
        
        if(ae.getSource()==visArt.btnEliminarAr){
            eliminarArtista();
            limpiarTablaArt();
            listarArtista(visArt.tablaArtista);
        } 
        
        if(ae.getSource()==visAlbm.btnEditarAl){
            int fila = visAlbm.tablaAlbum.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(visAlbm,"Debe seleccionar un registro");
            }else{
                int id_album = Integer.parseInt((String) visAlbm.tablaAlbum.getValueAt(fila, 0).toString());
                String titulo =  (String) visAlbm.tablaAlbum.getValueAt(fila,1);
                int anio = Integer.parseInt((String) visAlbm.tablaAlbum.getValueAt(fila, 2).toString());
                String genero =  (String) visAlbm.tablaAlbum.getValueAt(fila,3);
                int id_artista = Integer.parseInt((String) visAlbm.tablaAlbum.getValueAt(fila, 4).toString());
                visAlbm.txtId.setText(""+id_album);
                visAlbm.txtTitulo.setText(titulo);
                visAlbm.txtAnio.setText(""+anio);
                visAlbm.txtGenero.setText(genero);
                visAlbm.txtIdArtista.setText(""+id_artista);
            }
        }
        
        if(ae.getSource()==visAlbm.btnOkAl){
            actualizarAlbum();
            limpiarTablaAlbm();
            listarAlbum(visAlbm.tablaAlbum);
        }
        
        if(ae.getSource()==visAlbm.btnEliminarAl){
            eliminarAlbum();
            limpiarTablaAlbm();
            listarAlbum(visAlbm.tablaAlbum);
        } 
    }
    
    //eliminar artista
    public void eliminarArtista(){
        int fila = visArt.tablaArtista.getSelectedRow(); 
            if(fila==-1){
                JOptionPane.showMessageDialog(visArt,"Debe seleccionar un registro");
            }else{
                int id_artista = Integer.parseInt((String) visArt.tablaArtista.getValueAt(fila, 0).toString());
                art.eliminarArtista(id_artista);
                 JOptionPane.showMessageDialog(visArt,"Usuario eliminado");
            }
    }
    
    //eliminar álbum
    public void eliminarAlbum(){
        int fila = visAlbm.tablaAlbum.getSelectedRow(); 
            if(fila==-1){
                JOptionPane.showMessageDialog(visAlbm,"Debe seleccionar un registro");
            }else{
                int id_album = Integer.parseInt((String) visAlbm.tablaAlbum.getValueAt(fila, 0).toString());
                albm.eliminarAlbum(id_album);
                 JOptionPane.showMessageDialog(visAlbm,"Álbum eliminado");
            }
    }
    
    //limpiar tabla artista
    public void limpiarTablaArt(){
        for (int i = 0; i < visArt.tablaArtista.getRowCount(); i++) {
            tablaArt.removeRow(i);
            i=i-1;
        }
    }
    
    //limpiar tabla álbum
    public void limpiarTablaAlbm(){
        for (int i = 0; i < visAlbm.tablaAlbum.getRowCount(); i++) {
            tablaAlbm.removeRow(i);
            i=i-1;
        }
    }
     
    //Método para actualizar artista
    public void actualizarArtista(){
        if (visArt.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(visArt, "No se identifica el Id, debe selecionar la opción Editar");
        }else{
            int id = Integer.parseInt(visArt.txtId.getText());
            String nombre = visArt.txtNombre.getText();
            String alias = visArt.txtAlias.getText();

            a.setId(id);
            a.setNombre(nombre);
            a.setAlias(alias);

            int resultado = art.actualizaArtista(a);

            if(resultado==1){
                 JOptionPane.showMessageDialog(visArt, "Artista actualizado correctamente");
            }else{
                 JOptionPane.showMessageDialog(visArt, "Error");
            }
        }
    }
    
    //Método para actualizar álbum
    public void actualizarAlbum(){
        if (visAlbm.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(visAlbm, "No se identifica el Id, debe selecionar la opción Editar");
        }else{
            
            
            int id = Integer.parseInt(visAlbm.txtId.getText());
            String titulo = visAlbm.txtTitulo.getText();
            int anio = Integer.parseInt(visAlbm.txtAnio.getText());
            String genero = visAlbm.txtGenero.getText();
            int idArtista = Integer.parseInt(visAlbm.txtIdArtista.getText());

            al.setId(id);
            al.setTitulo(titulo);
            al.setAnio(anio);
            al.setGenero(genero);
            al.setId_artista(idArtista);

            int resultado = albm.actualizaAlbum(al);

            if(resultado==1){
                 JOptionPane.showMessageDialog(visAlbm, "Álbum actualizado correctamente");
            }else{
                 JOptionPane.showMessageDialog(visAlbm, "Error");
            }
        }
    }
    
    //Método para agregar artista 
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
    
    //Método para agregar álbum
    public void agregarAlbum(){
        String titulo = visAlbm.txtTitulo.getText();
        int anio =  Integer.parseInt(visAlbm.txtAnio.getText());
        String genero = visAlbm.txtGenero.getText();
        int id_artista =  Integer.parseInt(visAlbm.txtIdArtista.getText());
        al.setTitulo(titulo);
        al.setAnio(anio);
        al.setGenero(genero);
        al.setId_artista(id_artista);
        int resultado = albm.agregarAlbum(al);
        
        if(resultado==1){
            JOptionPane.showMessageDialog(visAlbm, "Álbum agregado");
        }else{
            JOptionPane.showMessageDialog(visAlbm, "Error");
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
