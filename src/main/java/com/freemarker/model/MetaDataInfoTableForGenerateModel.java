package com.freemarker.model;

import java.util.List;

public class MetaDataInfoTableForGenerateModel {
    private String name;
    private List<MetaDataField> metaDataFieldList;

    public MetaDataInfoTableForGenerateModel(String name, List<MetaDataField> metaDataFieldList) {
        this.name = name;
        this.metaDataFieldList = metaDataFieldList;
    }
    public MetaDataInfoTableForGenerateModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MetaDataField> getMetaDataFieldList() {
        return metaDataFieldList;
    }

    public void setMetaDataFieldList(List<MetaDataField> metaDataFieldList) {
        this.metaDataFieldList = metaDataFieldList;
    }
}
