package com.freemarker.i;

import java.util.Optional;
import java.math.BigDecimal;
import com.freemarker.generic.GenericRepository;
import com.freemarker.mod.${metaDataInfoIndex.name};

public interface ${metaDataInfoIndex.name}Repository extends GenericRepository<${metaDataInfoIndex.name}> {

<#--<#assign x = metaDataInfoIndex.getIndexList()?size>-->
<#--<#if x == 1>-->
<#list metaDataInfoIndex.indexList as indexListCurrent>
    <#assign y = indexListCurrent.getMetaDataFieldList()?size>
    <#if y == 1>
     Optional<${metaDataInfoIndex.name}>  find${indexListCurrent.name?cap_first} (${indexListCurrent.metaDataFieldList[0].type} ${indexListCurrent.metaDataFieldList[0].name});
        <#elseif y == 2>
     Optional<${metaDataInfoIndex.name}>  find${indexListCurrent.name?cap_first} (${indexListCurrent.metaDataFieldList[0].type} ${indexListCurrent.metaDataFieldList[0].name},${indexListCurrent.metaDataFieldList[1].type} ${indexListCurrent.metaDataFieldList[1].name});
        <#elseif y==3>
     Optional<${metaDataInfoIndex.name}>  find${indexListCurrent.name?cap_first} (${indexListCurrent.metaDataFieldList[0].type} ${indexListCurrent.metaDataFieldList[0].name},${indexListCurrent.metaDataFieldList[1].type} ${indexListCurrent.metaDataFieldList[1].name},${indexListCurrent.metaDataFieldList[2].type} ${indexListCurrent.metaDataFieldList[2].name});
        </#if>
    </#list>
}



