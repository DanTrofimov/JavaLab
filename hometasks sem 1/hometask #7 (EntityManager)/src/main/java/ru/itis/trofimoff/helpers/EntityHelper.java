package ru.itis.trofimoff.helpers;

public class EntityHelper {
    public static StringBuilder getSetterName(String fieldName) {
        StringBuilder result = new StringBuilder("set");
        result.append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return result;
    }

    public static String convertMultipleComparingValues(String[] values) {
        StringBuilder result = new StringBuilder();
        result.append("(");
        for (int i = 0; i < values.length; i++) {
            result.append(values[i]);
            if (i != values.length - 1) result.append(", ");
        }
        result.append(")");
        return result.toString();
    }
}
