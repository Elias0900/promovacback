package com.tresorerie.voyage.utils;

public class MariaDBDialect extends org.hibernate.dialect.MariaDBDialect {
    @Override
    public String getTableTypeString() {
        // Appel de la méthode parent et ajout de la clause ROW_FORMAT
        return super.getTableTypeString() + " ROW_FORMAT=DYNAMIC";
    }
}
