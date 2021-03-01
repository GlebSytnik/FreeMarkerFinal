package com.freemarker.adapter;


import com.freemarker.generators.JavaDataInterfaseGeneratorWithTable;
import com.freemarker.model.Index;
import com.freemarker.model.MetaDataField;
import com.freemarker.model.MetaDataInfoTable;
import com.freemarker.utill.NameHolder;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.ColumnDataType;
import schemacrawler.schema.Table;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdapterForInterfase {

    public List<MetaDataInfoTable> adapter(Catalog catalog) {
        List<MetaDataInfoTable> metaDataInfoIndexList = new ArrayList<>();

        for (Table table : catalog.getTables()) {
            String nameTable = table.getName();
            nameTable = NameHolder.toCamelCaseNameTable(nameTable);
            MetaDataInfoTable metaDataInfoTable = new MetaDataInfoTable();
            metaDataInfoTable.setName(nameTable);

            List<Index> indexesForMyModel = new ArrayList<>();

            for (schemacrawler.schema.Index index : table.getIndexes()) {
                String indexName = NameHolder.toCamelCaseFieldOrIndexName(index.getName());

                List<MetaDataField> fieldsForMyModel = new ArrayList<>();
                for (Column column : index.getColumns()) {
                    String columnName = NameHolder.toCamelCaseFieldOrIndexName(column.getName());
                    ColumnDataType columnType = column.getType();
                    String columnTypeString = converterColumnTypeInFieldType(columnType);

                    fieldsForMyModel.add(new MetaDataField(columnName, columnTypeString));
                }

                Index indexForMyTable = new Index(indexName, fieldsForMyModel);
                indexesForMyModel.add(indexForMyTable);
            }

            metaDataInfoTable.setIndexList(indexesForMyModel);

            metaDataInfoIndexList.add(metaDataInfoTable);
        }
        return metaDataInfoIndexList;
    }

    private String converterColumnTypeInFieldType(ColumnDataType columnDataType) {
        if (columnDataType.getName().equalsIgnoreCase("varchar")) {
            return "String";
        } else if (columnDataType.getName().equalsIgnoreCase("integer")) {
            return "Long";
        } else if (columnDataType.getName().equalsIgnoreCase("timestamp")) {
            return "BigDecimal";
        }
        else if (columnDataType.getName().equalsIgnoreCase("serial")) {
            return "Long";
        }

        return "IndifinedType";
    }
    public static void main(String[] args) throws Exception {
//        AdapterForInterfase adapterForInterfase = new AdapterForInterfase();
//        AdapterForModel adapterForModel = new AdapterForModel();
//        GenerateInterfaseFinal generateInterfase = new GenerateInterfaseFinal();
//
//        List<MetaDataInfoTable> metaDataInfoTableList = adapterForInterfase.adapter(generateInterfase.getCatalog());
//        List<MetaDataInfoTable> metaDataInfoTableListForModel = adapterForModel.adapterForModel(generateInterfase.getCatalog());
//
//        JavaDataModelGeneratorWithMetaDataInfoTable javaDataModelGeneratorWithMetaDataInfoTable = new JavaDataModelGeneratorWithMetaDataInfoTable();
//        File outputDirectory1 = new File("/home/gleb/IdeaProjects/FreeMarker/src/main/java/com/freemarker/mod");
//        javaDataModelGeneratorWithMetaDataInfoTable.generateJavaSourceFiles(metaDataInfoTableListForModel,outputDirectory1);
//
//
//        JavaDataInterfaseGeneratorWithTable javaDataInterfaseGeneratorWithTable = new JavaDataInterfaseGeneratorWithTable();
//        File outputDirectory = new File("/home/gleb/IdeaProjects/FreeMarker/src/main/java/com/freemarker/interf");
//        javaDataInterfaseGeneratorWithTable.generateJavaInterfaseFilesWithTable(metaDataInfoTableList, outputDirectory);

        AdapterForInterfase adapterForInterfase = new AdapterForInterfase();
        JavaDataInterfaseGeneratorWithTable javaDataInterfaseGeneratorWithTable = new JavaDataInterfaseGeneratorWithTable();
        List<MetaDataInfoTable> metaDataInfoTableList = adapterForInterfase.adapter(javaDataInterfaseGeneratorWithTable.getCatalog());


        File outputDirectory = new File("/home/gleb/IdeaProjects/FreeMarkerFinal/src/main/java/com/freemarker/mod");
        javaDataInterfaseGeneratorWithTable.generateJavaInterfaseFilesWithTable(metaDataInfoTableList, outputDirectory);
    }
}


