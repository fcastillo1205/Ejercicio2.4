package com.example.ejercicio24.tablas;

public class signaturess {
    private Integer id;
    private byte[] firmaDigital;
    private String descripcion;

    public signaturess(Integer id, byte[] imagen, String descripcion) {
        this.id = id;
        this.firmaDigital = imagen;
        this.descripcion = descripcion;
    }

    public signaturess(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return firmaDigital;
    }

    public void setImagen(byte[] imagen) {
        this.firmaDigital = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
