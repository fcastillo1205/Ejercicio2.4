package com.example.ejercicio24.Configuracion;

public class transacciones {
    public static final String NameDatabase = "PMO1DB";

    //tablas de la DB en SQLite

    public static final String signaturess = "signaturess";

    //campo de la tabla personas de la DB en SQLite
    public static final String id = "id";
    public static final String firmaDigital = "firmaDigital";
    public static final String descripcion = "descripcion";


    //transacciones DDL(DATA DEFINITION LENGUAGE) tabla personas
    public static final String CreateTableSignaturess = "CREATE TABLE signaturess (id INTEGER PRIMARY KEY AUTOINCREMENT "+
            ",firmaDigital BLOB, descripcion TEXT)";
    public static final String DROPTableSignaturess = "DROP TABLE IF EXISTS signaturess";
}
