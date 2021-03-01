package com.freemarker.utill;

public class NameHolder {

    public static String toCamelCaseFieldOrIndexName(String nameFieldOrIndex) {
        while (nameFieldOrIndex.contains("_")) {
            nameFieldOrIndex =
                    nameFieldOrIndex.replaceFirst("_[a-z0-9]", String.valueOf(Character.toUpperCase(nameFieldOrIndex.charAt(nameFieldOrIndex.indexOf("_") + 1))));
            nameFieldOrIndex = nameFieldOrIndex.substring(0, 1).toUpperCase() + nameFieldOrIndex.substring(1);
        }
        char c[] = nameFieldOrIndex.toCharArray();
        c[0] = Character.toLowerCase(c[0]);

        nameFieldOrIndex = new String(c);
        return nameFieldOrIndex;
    }
    public static String toCamelCaseNameTable(String nameTable) {
        while (nameTable.contains("_")) {
            nameTable =
                    nameTable.replaceFirst("_[a-z0-9]", String.valueOf(Character.toUpperCase(nameTable.charAt(nameTable.indexOf("_") + 1))));
            nameTable = nameTable.substring(0, 1).toUpperCase() + nameTable.substring(1);
        }
        char c[] = nameTable.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        nameTable = new String(c);
        return nameTable;
    }
}
