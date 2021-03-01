package com.freemarker.model;

import java.util.List;

public class MetaDataInfoTable {

    private String name;
    private List<Index> indexList;
    private List<MetaDataField> metaDataFieldList;

    public MetaDataInfoTable(String name, List<Index> indexList) {
        this.name = name;
        this.indexList = indexList;
    }

    public MetaDataInfoTable() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Index> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<Index> indexList) {
        this.indexList = indexList;
    }

    public void setMetaDataFieldList(List<MetaDataField> metaDataFieldList) {
        this.metaDataFieldList = metaDataFieldList;
    }

    public List<MetaDataField> getMetaDataFieldList() {
        return metaDataFieldList;
    }
}
