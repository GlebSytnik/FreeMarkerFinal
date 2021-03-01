package com.freemarker.i;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ${metaDataInfoTable.name} {
<#--<#list metaDataInfoTable.indexList as indexListCurrent>-->
<#--    <#list indexListCurrent.metaDataFieldList as metaField>-->
<#--        private  ${metaField.type} ${metaField.name};-->
<#--    </#list>-->
<#--</#list>-->
<#list metaDataInfoTable.metaDataFieldList as field>
    private ${field.type} ${field.name};
</#list>

}