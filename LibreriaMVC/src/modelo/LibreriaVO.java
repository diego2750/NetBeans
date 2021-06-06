package modelo;

public class LibreriaVO {
    
    /*TABLA AUTOR*/
    private int id_autor;
    private String autor;
    private long num_serie;

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public long getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(long num_serie) {
        this.num_serie = num_serie;
    }    
    
    /*TABLA LIBRO*/
    private int id_libro;
    private String libro;
    private String genero_libro;
    private int copias;
    private int precio;
    private long num_seriefk;

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getGenero_libro() {
        return genero_libro;
    }

    public void setGenero_libro(String genero_libro) {
        this.genero_libro = genero_libro;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public long getNum_seriefk() {
        return num_seriefk;
    }

    public void setNum_seriefk(long num_seriefk) {
        this.num_seriefk = num_seriefk;
    }
    
    
}
