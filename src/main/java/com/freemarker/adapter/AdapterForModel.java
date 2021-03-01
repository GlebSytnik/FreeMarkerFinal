package com.freemarker.adapter;

import com.freemarker.generators.GenerateNew;
import com.freemarker.model.MetaDataField;
import com.freemarker.model.MetaDataInfoTableForGenerateModel;
import com.freemarker.utill.NameHolder;
import schemacrawler.schema.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdapterForModel {

    public List<MetaDataInfoTableForGenerateModel> adapter(Catalog catalog) {
        List<MetaDataInfoTableForGenerateModel> metaDataInfoTableForGenerateModels = new ArrayList<>();

        for (Table table : catalog.getTables()) {
            String nameTable = table.getName();
            nameTable = NameHolder.toCamelCaseNameTable(nameTable);
            MetaDataInfoTableForGenerateModel metaDataInfoTableForGenerateModel = new MetaDataInfoTableForGenerateModel();
            metaDataInfoTableForGenerateModel.setName(nameTable);
            List<MetaDataField> metaDataFieldList = new ArrayList<>();

            for (schemacrawler.schema.Column column : table.getColumns()) {
                String fieldName = NameHolder.toCamelCaseFieldOrIndexName(column.getName());
                ColumnDataType columnType = column.getType();
                String columnTypeString = converterColumnTypeInFieldType(columnType);
//                JavaSqlType javaSqlType = column.getType().getJavaSqlType();
//                String columnTypeString = javaSqlType.getName();
                metaDataFieldList.add(new MetaDataField(fieldName, columnTypeString));
            }
            metaDataInfoTableForGenerateModel.setMetaDataFieldList(metaDataFieldList);
            metaDataInfoTableForGenerateModels.add(metaDataInfoTableForGenerateModel);
        }
        return metaDataInfoTableForGenerateModels;
    }

    private String converterColumnTypeInFieldType(ColumnDataType columnDataType) {
        if (columnDataType.getName().equalsIgnoreCase("varchar")) {
            return "String";
        } else if (columnDataType.getName().equalsIgnoreCase("integer")) {
            return "Long";
        } else if (columnDataType.getName().equalsIgnoreCase("timestamp")) {
            return "BigDecimal";
        } else if (columnDataType.getName().equalsIgnoreCase("serial")) {
            return "Long";
        } else if (columnDataType.getName().equalsIgnoreCase("bool")) {
            return "Boolean";
        } else if (columnDataType.getName().equalsIgnoreCase("int4")) {
            return "Integer";
        } else if (columnDataType.getName().equalsIgnoreCase("text")) {
            return "String";
        } else if (columnDataType.getName().equalsIgnoreCase("timestamptz")) {
            return "BigDecimal";
        } else if (columnDataType.getName().equalsIgnoreCase("numeric")) {
            return "BigDecimal";
        }


        return "IndifinedType";
    }

    public static void main(String[] args) throws Exception {
        AdapterForModel adapterForModel = new AdapterForModel();
        GenerateNew generateNew = new GenerateNew();
        List<MetaDataInfoTableForGenerateModel> metaDataInfoTableForGenerateModelList = adapterForModel.adapter(generateNew.getCatalog());
        File outputDirectory = new File("/home/gleb/IdeaProjects/FreeMarkerFinal/src/main/java/com/freemarker/new");
        generateNew.generateJavaSourceFiles(metaDataInfoTableForGenerateModelList, outputDirectory);
    }

}
