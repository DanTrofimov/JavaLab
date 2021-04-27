package ru.itis.trofimoff;

import java.lang.reflect.Field;
import java.util.HashMap;

public class QueryConstructor {

    private static final HashMap<String, String> types = new HashMap<>();

    public static void init() {
        types.put("string", "varchar");
        types.put("long", "bigint");
        types.put("integer", "int");
        types.put("uuid", "uuid");
    }

    public static String getTypeForDataBase(String fieldType) {
        return types.get(fieldType);
    }

    public static StringBuilder createTable(String tableName, Field[] fields) {
        // constructing full sql query
        StringBuilder sql = new StringBuilder(DBQueries.SQL_CREATE_TABLE);
        sql.append(tableName.trim()).append("(");
        for (int i = 0; i < fields.length; i++) {
            sql.append(fields[i].getName()).append(" ");
            sql.append(getTypeForDataBase(fields[i].getType().getSimpleName().toLowerCase()));
            if (i + 1 != fields.length) {
                sql.append(",");
            }
        }
        sql.append(");");
        return sql;
    }

    public static StringBuilder dropTable(String tableName) {
        StringBuilder sql = new StringBuilder(DBQueries.SQL_DROP_TABLE);
        sql.append(tableName);
        return sql;
    }

    public static StringBuilder save(String tableName, Field[] fields) {
        // constructing full sql query
        StringBuilder sql = new StringBuilder(DBQueries.SQL_SAVE);
        // adding field-name
        sql.append(tableName.trim()).append(" values(");
        for (int i = 0; i < fields.length; i++) {
            sql.append("?");
            if (i + 1 != fields.length) {
                sql.append(", ");
            }
        }
        sql.append(");");
        return sql;
    }

    public static StringBuilder findById(String tableName) {
        // constructing sql query
        StringBuilder sql = new StringBuilder(DBQueries.SQL_FIND);
        sql.append(tableName.trim()).append(" WHERE id=?");

        return sql;
    }
}
