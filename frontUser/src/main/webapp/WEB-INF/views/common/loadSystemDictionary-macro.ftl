<#macro loadDictionary sn>
	<#assign list = _GUtils._SDUtil.loadItems(sn) />
	<#list list as item>
		<option value="${item.id}">${item.title}</option>
	</#list>
</#macro>