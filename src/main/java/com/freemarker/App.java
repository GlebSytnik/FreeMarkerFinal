package com.freemarker;

import com.freemarker.adapter.AdapterForInterfase;
import com.freemarker.generators.JavaDataInterfaseGeneratorWithTable;
import com.freemarker.generators.JavaDataModelGeneratorWithMetaDataInfoTable;
import com.freemarker.model.MetaDataInfoTable;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {
        AdapterForInterfase adapterForInterfase = new AdapterForInterfase();
        JavaDataInterfaseGeneratorWithTable javaDataInterfaseGeneratorWithTable = new JavaDataInterfaseGeneratorWithTable();
        List<MetaDataInfoTable> metaDataInfoTableList = adapterForInterfase.adapter(javaDataInterfaseGeneratorWithTable.getCatalog());

        File outputDirectory = new File("/home/gleb/IdeaProjects/FreeMarkerFinal/src/main/java/com/freemarker/i");
        javaDataInterfaseGeneratorWithTable.generateJavaInterfaseFilesWithTable(metaDataInfoTableList, outputDirectory);

        JavaDataModelGeneratorWithMetaDataInfoTable javaDataModelGeneratorWithMetaDataInfoTable = new JavaDataModelGeneratorWithMetaDataInfoTable();
        File outputDirectory1 = new File("/home/gleb/IdeaProjects/FreeMarkerFinal/src/main/java/com/freemarker/mod");
        javaDataModelGeneratorWithMetaDataInfoTable.generateJavaSourceFiles(metaDataInfoTableList, outputDirectory1);
    }
}
