package modelo;

public class Album {
    int id;
    String titulo;
    int anio;
    String genero;
    int id_artista;
    
    public Album(){
        
    }

    public Album(int id, String titulo, int anio, String genero, int id_artista) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.genero = genero;
        this.id_artista = id_artista;
    }
    
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnio() {
        return anio;
    }

    public String getGenero() {
        return genero;
    }

    public int getId_artista() {
        return id_artista;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setId_artista(int id_artista) {
        this.id_artista = id_artista;
    }
     
    
}
