package com.freemarker.model;

import java.util.List;

public class Index {
    private String name;
    private List<MetaDataField> metaDataFieldList;

    public Index(String name, List<MetaDataField> metaDataFieldList) {
        this.name = name;
        this.metaDataFieldList = metaDataFieldList;
    }
    public Index(String name) {
        this.name = name;

    }
    public Index(List<MetaDataField> metaDataFieldList) {

        this.metaDataFieldList = metaDataFieldList;
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
