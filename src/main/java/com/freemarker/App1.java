package com.freemarker;

import com.freemarker.adapter.AdapterForInterfase;
import com.freemarker.adapter.AdapterForModel;
import com.freemarker.generators.GenerateNew;
import com.freemarker.generators.JavaDataInterfaseGeneratorWithTable;
import com.freemarker.model.MetaDataInfoTable;
import com.freemarker.model.MetaDataInfoTableForGenerateModel;

import java.io.File;
import java.util.List;

public class App1 {

    public static void main(String[] args) throws Exception {
//        generate interfase
        AdapterForInterfase adapterForInterfase = new AdapterForInterfase();
        JavaDataInterfaseGeneratorWithTable javaDataInterfaseGeneratorWithTable = new JavaDataInterfaseGeneratorWithTable();
        List<MetaDataInfoTable> metaDataInfoTableList = adapterForInterfase.adapter(javaDataInterfaseGeneratorWithTable.getCatalog());

        File outputDirectory = new File("/home/gleb/IdeaProjects/FreeMarkerFinal/src/main/java/com/freemarker/i");
        javaDataInterfaseGeneratorWithTable.generateJavaInterfaseFilesWithTable(metaDataInfoTableList, outputDirectory);

        //generate model
        AdapterForModel adapterForModel = new AdapterForModel();
        GenerateNew generateNew = new GenerateNew();
        List<MetaDataInfoTableForGenerateModel> metaDataInfoTableForGenerateModelList = adapterForModel.adapter(generateNew.getCatalog());
        File outputDirectory1 = new File("/home/gleb/IdeaProjects/FreeMarkerFinal/src/main/java/com/freemarker/mod");
        generateNew.generateJavaSourceFiles(metaDataInfoTableForGenerateModelList, outputDirectory1);
    }

}
