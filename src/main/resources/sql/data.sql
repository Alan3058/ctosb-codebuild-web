INSERT INTO CONFIG(CONFIG_ID, CONFIG_NAME, DATASOURCE_ID) VALUES
('297e5d005ec7d642015ec7d7676e0000', STRINGDECODE('\u672c\u5730\u914d\u7f6e'), '297e5d005ec7e131015ec7f036e60000');  

INSERT INTO DATASOURCE(DATASOURCE_ID, DATASOURCE_NAME, JDBC_DRIVER, JDBC_PASSWORD, JDBC_URL, JDBC_USER_NAME) VALUES
('297e5d005ec7e131015ec7f036e60000', STRINGDECODE('\u672c\u5730\u6570\u636e\u6e90'), 'com.mysql.jdbc.Driver', '', 'jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8', 'root');      


INSERT INTO PUBLIC.TEMPLATE(TEMPLATE_ID, CONFIG_ID, TEMPLATE_NAME, TEMPLATE_TYPE, GENERATE_BASE_PACKAGE_PATH, GENERATE_PACKAGE_PATH, GENERATE_SOURCE_FOLDER, GENERATE_SUFFIX, TEMPLATE_CONTENT) VALUES
('f72a43271e444fb498a636f93f24100e', '297e5d005ec7d642015ec7d7676e0000', 'item.ftl', 'bySql', 'com/ctosb/test', 'com/ctosb/test/vo', 'src/main/java', 'Item.java', STRINGDECODE('package ${packageName};\r\n\r\nimport javax.persistence.Column;\r\nimport javax.persistence.Entity;\r\nimport javax.persistence.Table;\r\n\r\nimport com.alan.core.model.BaseModel;\r\n\r\n/**\r\n * ${tableInfo.tabComment}\r\n * \r\n * @author alan\r\n * @date ${.now}\r\n */\r\n@Entity\r\n@Table(name = \"${tableInfo.tabName}\")\r\npublic class ${tableInfo.upperCamelTabName} extends BaseModel {\r\n\t\r\n\tprivate static final long serialVersionUID = -4643466141706527625L;\r\n\t<#list columnInfos as columnInfo>\r\n\t/**\r\n\t * ${columnInfo.colComment}\r\n \t */\r\n\tprivate ${columnInfo.javaColType} ${columnInfo.camelColName};\r\n\t</#list>\r\n\r\n\tpublic ${tableInfo.upperCamelTabName}() {\r\n\r\n\t}\r\n\t<#list columnInfos as columnInfo>\r\n\t\r\n\t@Column(name=\"${columnInfo.colName}\")\r\n\tpublic ${columnInfo.javaColType} get${columnInfo.upperCamelColName}() {\r\n\t\treturn ${columnInfo.camelColName};\r\n\t}\r\n\r\n\tpublic void set${columnInfo.upperCamelColName}(${columnInfo.javaColType} ${columnInfo.camelColName}) {\r\n\t\tthis.${columnInfo.camelColName} = ${columnInfo.camelColName};\r\n\t}\r\n\t</#list>\r\n\r\n\r\n}')),
('e8cb04e29f3448bdb93aaeb2bd0a89fb', '297e5d005ec7d642015ec7d7676e0000', 'mapper.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/mapper', 'src/main/java', 'Mapper.java', STRINGDECODE('package ${packageName};\r\n\r\nimport java.util.List;\r\n\r\nimport ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;\r\nimport com.ctosb.core.mybatis.Page;\r\nimport com.ctosb.core.mybatis.PageList;\r\n\r\npublic interface ${tableInfo.upperCamelTabName}Mapper {\r\n\r\n\t/**\r\n     * \u5206\u9875\u67e5\u8be2\u65b9\u6cd5\r\n     * @param condition\r\n     * @param page\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public PageList<${tableInfo.upperCamelTabName}Model> get(${tableInfo.upperCamelTabName}Model condition,Page page);\r\n\r\n    /**\r\n     * \u67e5\u8be2\u65b9\u6cd5\r\n     * @param condition\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public List<${tableInfo.upperCamelTabName}Model> get(${tableInfo.upperCamelTabName}Model condition);\r\n    \r\n    /**\r\n     * \u65b0\u589e\u65b9\u6cd5\r\n     * @param model\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public int insert(${tableInfo.upperCamelTabName}Model model);\r\n    \r\n    /**\r\n     * \u4fee\u6539\u65b9\u6cd5\r\n     * @param model\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public int update(${tableInfo.upperCamelTabName}Model model);\r\n    \r\n    /**\r\n     * \u5220\u9664\u65b9\u6cd5\r\n     * @param ids\r\n     * @return\r\n   \t * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public int delete(List<Long> ids);\r\n\r\n}')),
('d4b071e1195843ee8209a277eaf42b7c', '297e5d005ec7d642015ec7d7676e0000', 'mapperXml.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/mapper', 'src/main/resources', 'Mapper.xml', STRINGDECODE('<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \r\n\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n\r\n<mapper namespace=\"${packageName}.${tableInfo.upperCamelTabName}Mapper\">\r\n\t<resultMap id=\"${tableInfo.camelTabName}ModelResultMap\" type=\"${basePackageName}.model.${tableInfo.upperCamelTabName}Model\"/>\r\n\t<sql id=\"select\">\r\n\t\t<![CDATA[\r\n\t\t\tSELECT \r\n\t\t\t<#list columnInfos as columnInfo>\r\n\t\t\t<#if columnInfo_has_next>\r\n\t\t\t\tT.${columnInfo.colName},\r\n\t\t\t<#else>\r\n\t\t\t\tT.${columnInfo.colName}\r\n\t\t\t</#if>\r\n\t\t\t</#list>\r\n\t\t]]>\r\n\t</sql>\r\n\t\r\n\t<sql id=\"findByExample\">\r\n\t\tWHERE 1=1\r\n\t\t<#list columnInfos as columnInfo>\r\n\t\t<if test=\"${columnInfo.camelColName} != null and ${columnInfo.camelColName} != ''''\"> AND ${columnInfo.colName} = ${''#''}{${columnInfo.camelColName}}</if>\r\n\t\t</#list>\r\n\t</sql>\r\n\t\r\n\t<select id=\"get\" resultMap=\"${tableInfo.camelTabName}ModelResultMap\">\r\n\t\t<include refid=\"select\"/>\r\n\t\tFROM  ${tableInfo.tabName} T\r\n\t\t<include refid=\"findByExample\"/>\r\n\t</select>\r\n\t\r\n\t<insert id=\"insert\">\r\n\t\t<#list columnInfos as columnInfo>\r\n\t\t\t<#if columnInfo.primary>\r\n\t\t<selectKey resultType=\"${columnInfo.javaColType}\" order=\"AFTER\" keyProperty=\"${columnInfo.camelColName}\">\r\n            </#if>\r\n\t\t</#list>\r\n            SELECT LAST_INSERT_ID();        \r\n        </selectKey>\r\n\t\tINSERT INTO ${tableInfo.tabName} (\r\n\t\t\t\t<#list columnInfos as columnInfo>\r\n\t\t\t\t<#if columnInfo_has_next>\r\n\t\t\t\t\t${columnInfo.colName},\r\n\t\t\t\t<#else>\r\n\t\t\t\t\t${columnInfo.colName}\r\n\t\t\t\t</#if>\r\n\t\t\t\t</#list>\r\n\t\t) VALUES (\r\n\t\t\t\t<#list columnInfos as columnInfo>\r\n\t\t\t\t<#if columnInfo_has_next>\r\n\t\t\t\t\t${''#''}{${columnInfo.camelColName}},\r\n\t\t\t\t<#else>\r\n\t\t\t\t\t${''#''}{${columnInfo.camelColName}}\r\n\t\t\t\t</#if>\r\n\t\t\t\t</#list>\r\n\t\t);\r\n\t</insert>\r\n\t\r\n\t<update id=\"update\">\r\n\t\tUPDATE ${tableInfo.tabName} SET\r\n\t\t\t\t<#list columnInfos as columnInfo>\r\n\t\t\t\t<#if columnInfo_has_next>\r\n\t\t\t\t<if test=\"${columnInfo.camelColName} != null and ${columnInfo.camelColName} != ''''\"> ${columnInfo.colName} = ${''#''}{${columnInfo.camelColName}},</if>\r\n\t\t\t\t<#else>\r\n\t\t\t\t<if test=\"${columnInfo.camelColName} != null and ${columnInfo.camelColName} != ''''\"> ${columnInfo.colName} = ${''#''}{${columnInfo.camelColName}},</if>\r\n\t\t\t\t</#if>\r\n\t\t\t\t</#list>\r\n\t\t\t\tMODIFY_TIME = ${''#''}{modifyTime}\r\n\t\tWHERE 1=1\r\n\t\t<#list columnInfos as columnInfo>\r\n\t\t\t   <#if columnInfo.primary>\r\n               AND ${columnInfo.colName} = ${''#''}{${columnInfo.camelColName}}\r\n               </#if>\r\n\t\t</#list>\r\n\t</update>\r\n\t<delete id=\"delete\" parameterType=\"java.util.List\">\r\n        DELETE FROM ${tableInfo.tabName} WHERE \r\n         <choose>\r\n            <when test=\"null!=list and list.size!=0\">\r\n            <#list columnInfos as columnInfo>\r\n\t\t\t   <#if columnInfo.primary>\r\n                ${columnInfo.colName} IN\r\n               </#if>\r\n\t\t</#list>\r\n                <foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\",\" close=\")\">\r\n                    ${''#''}{item}\r\n                </foreach>\r\n            </when>\r\n            <otherwise>\r\n               1 != 1\r\n            </otherwise>\r\n        </choose>\r\n    </delete>\r\n</mapper>\r\n')); 
INSERT INTO PUBLIC.TEMPLATE(TEMPLATE_ID, CONFIG_ID, TEMPLATE_NAME, TEMPLATE_TYPE, GENERATE_BASE_PACKAGE_PATH, GENERATE_PACKAGE_PATH, GENERATE_SOURCE_FOLDER, GENERATE_SUFFIX, TEMPLATE_CONTENT) VALUES
('b0bba4d6941f49438f67411f17101a0a', '297e5d005ec7d642015ec7d7676e0000', 'model.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/model', 'src/main/java', 'Model.java', STRINGDECODE('package ${packageName};\r\n\r\nimport java.io.Serializable;\r\nimport java.util.Date;\r\n\r\n/**\r\n * ${tableInfo.tabComment}\r\n * \r\n * @author alan\r\n * @date ${.now}\r\n */\r\npublic class ${tableInfo.upperCamelTabName}Model implements Serializable {\r\n\t\r\n\tprivate static final long serialVersionUID = ${(.now?long)?replace(\",\",\"\")}l;\r\n\t<#list columnInfos as columnInfo>\r\n\t/**\r\n\t * ${columnInfo.colComment}\r\n \t */\r\n\tprivate ${columnInfo.javaColType} ${columnInfo.camelColName};\r\n\t</#list>\r\n\r\n\tpublic ${tableInfo.upperCamelTabName}Model() {\r\n\r\n\t}\r\n\t<#list columnInfos as columnInfo>\r\n\t\r\n\tpublic ${columnInfo.javaColType} get${columnInfo.upperCamelColName}() {\r\n\t\treturn ${columnInfo.camelColName};\r\n\t}\r\n\r\n\tpublic ${tableInfo.upperCamelTabName}Model set${columnInfo.upperCamelColName}(${columnInfo.javaColType} ${columnInfo.camelColName}) {\r\n\t\tthis.${columnInfo.camelColName} = ${columnInfo.camelColName};\r\n\t\treturn this;\r\n\t}\r\n\t</#list>\r\n\r\n}')),
('2bb5d600b7d04a50819e6eda5440ed1c', '297e5d005ec7d642015ec7d7676e0000', 'js.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/js', 'src/main/webapp', 'Js.js', STRINGDECODE('/**\r\n * @Title \u8fd0\u5355\u7533\u62a5\u7ba1\u7406\r\n * @Description\r\n * @author\r\n * \u4fee\u6539\u8bb0\u5f55\r\n * \u4fee\u6539\u540e\u7248\u672c      \t\u4fee\u6539\u4eba           \t\u4fee\u6539\u65f6\u95f4            \t\u4fee\u6539\u5185\u5bb9\r\n * 2015-10-22     \tAlan.Li      \t2016-01-25        \tcreate\r\n */\r\nvar ${tableInfo.upperCamelTabName}Query = function() {\r\n\t\r\n\tvar mmGrid = null;\r\n\t\r\n\treturn {\r\n\t\t/**\r\n\t\t * \u521d\u59cb\u5316\u65b9\u6cd5\r\n\t\t */\r\n\t\tinit: function() {\r\n\t\t\t\r\n\t\t\t//\u521d\u59cb\u5316\u8868\u5355\r\n\t\t\tPUI.plugin.init({}, $(\"#${tableInfo.camelTabName}SearchForm\"));\r\n\t\t\t//grid\u6570\u636e\u6a21\u578b\r\n\t\t\tvar cols = [\r\n\t\t\t             { title:''\u5ba2\u6237\u540d\u79f0'', name:''distributionOrderName'' ,width:100, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u8fd0\u5355\u53f7'', name:''distributionOrderNo'' ,width:150, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u8fd0\u5355\u72b6\u6001'', name:''distributionOrderCode'' ,width:100, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u6536\u4ef6\u4eba'', name:''telephone'' ,width:100, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u8bc1\u4ef6\u7c7b\u578b'', name:''address'' ,width:100, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u6536\u4ef6\u4eba\u8bc1\u4ef6\u53f7\u7801'', name:''industryTypeName'' ,width:100, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u6536\u4ef6\u5730\u5740'', name:''distributionOrderStatusName'' ,width:70, align:''center'', sortable: true, type: ''String''},\r\n\t\t\t             { title:''\u6bdb\u91cd'', name:''creator'' ,width:70, align:''center'', sortable: true, type: ''String'',hidden:true},\r\n\t\t\t             { title:''\u51c0\u91cd'', name:''creator'' ,width:70, align:''center'', sortable: true, type: ''String'',hidden:true},\r\n\t\t\t             { title:''\u8fdb\u51fa\u53e3\u6807\u8bc6'', name:''creator'' ,width:70, align:''center'', sortable: true, type: ''String'',hidden:true},\r\n\t\t\t             { title:''\u521b\u5efa\u65f6\u95f4'', name:''createTime'' ,width:120, align:''center'', sortable: true, type: ''String'',hidden:true,\r\n\t\t\t            \t renderer:function(val,item,rowIndex){\r\n\t\t\t            \t\t return date2format(val,\"yyyy-MM-dd hh:mm:ss\");\r\n\t\t\t             \t }}\r\n\t\t\t         ];\r\n\t\t\t\r\n\t\t\tmmGrid = $(\"#mmg\").mmGrid({\r\n\t\t\t\theight : getAutoHeightByMmGrid($(\".page-content\")),\r\n\t\t\t\twidth : ''auto'', // \u8868\u683c\u5bbd\u5ea6\u3002\u53c2\u6570\u4e3a''auto''\u548c\u4ee5''%''\u7ed3\u5c3e,\u8868\u683c\u53ef\u5728\u8c03\u6574\u7a97\u53e3\u5c3a\u5bf8\u65f6\u6839\u636e\u7236\u5143\u7d20\u8c03\u6574\u5c3a\u5bf8\u3002\r\n\t\t\t\tcols : cols, //\u6570\u636e\u6a21\u578b,\u7528\u4e8e\u8868\u5934\u5206\u7ec4\uff0c\u76ee\u524d\u53ea\u652f\u6301\u4e24\u5c42\u3002\r\n\t\t\t\turl : ''cdci/${tableInfo.camelTabName}/page.shtml'', // AJAX\u8bf7\u6c42\u6570\u636e\u7684\u5730\u5740\u3002\r\n\t\t     \tparams : $(\"#distributionOrderSearchForm\").serialize(), // AJAX\u8bf7\u6c42\u7684\u53c2\u6570\u3002\r\n\t\t\t\tmethod : ''post'',\r\n\t\t\t\tnowrap : true, // \u8868\u683c\u663e\u793a\u7684\u6570\u636e\u8d85\u51fa\u5217\u5bbd\u65f6\u662f\u5426\u6362\u884c\u3002\r\n\t\t\t\tcheckCol : true, // \u8868\u683c\u663e\u793acheckbox\r\n\t\t\t\tautoLoad : false, // \u662f\u5426\u8868\u683c\u51c6\u5907\u597d\u65f6\u52a0\u8f7d\u6570\u636e\u3002\r\n\t\t\t\tsortStatus : ''desc'',\r\n\t\t\t\tfullWidthRows : true, // true:\u8868\u683c\u7b2c\u4e00\u6b21\u52a0\u8f7d\u6570\u636e\u65f6\u5217\u4f38\u5c55,\u81ea\u52a8\u5145\u6ee1\u8868\u683c\u3002\r\n\t\t\t\tcache : false,\r\n\t\t\t\tmultiSelect : true,\r\n\t\t\t\tplugins : [$(''#pg'').mmPaginator({})]\r\n\t\t\t});\r\n\t\t},\r\n\t\t\r\n\t\t/**\r\n\t\t * \u6839\u636e\u67e5\u8be2\u6761\u4ef6\uff0c\u8fdb\u884c\u67e5\u8be2\r\n\t\t */\r\n\t\tqueryHandler: function() {\r\n\t\t\t//\u6821\u9a8c\u8868\u5355\r\n\t\t\tif(!${tableInfo.upperCamelTabName}Query.validateForm()){\r\n\t\t\t\treturn;\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tmmGrid.load($(\"#${tableInfo.camelTabName}SearchForm\").serialize());\r\n\t\t},\r\n\t\t\r\n\t\t/**\r\n\t\t * \u6821\u9a8c\u8868\u5355\r\n\t\t */\r\n\t\tvalidateForm : function() {\r\n\t\t\t\r\n\t\t\tvar isValid = true;\r\n\t\t\t//\r\n\t\t\t\r\n\t\t\treturn isValid;\r\n\t\t},\r\n\t\t\r\n\t\t\r\n\t\t/**\r\n\t\t * \u6e05\u7a7a\u67e5\u8be2\u8868\u5355form\r\n\t\t */\r\n\t\tresetHandler: function(){\r\n\t\t\tPUI.util.resetForm($(\"#${tableInfo.camelTabName}SearchForm\"));\r\n\t\t},\r\n\t\t\r\n\t\t/**\r\n\t\t * \u65b0\u589e\r\n\t\t */\r\n\t\taddHandler: function() {\r\n\t\t\t$(\"#${tableInfo.camelTabName}_add_content\").html(PUI.String.format($(\"#user-detail-template\").html(), {}));\r\n\t\t\t${tableInfo.upperCamelTabName}Details.init(null);\r\n\t\t\t$(\"#tabwin_add\").popup();\r\n\t\t},\r\n\t\t\r\n\t\t/**\r\n\t\t * \u4fee\u6539\r\n\t\t */\r\n\t\tupdateHandler: function() {\r\n\t\t\tvar selectedRowCount = mmGrid.selectedRowsIndex().length;\r\n\t\t\tif (selectedRowCount < 1){\r\n\t\t\t\tPUI.MessageBox.alert(\"\u8bf7\u5148\u9009\u4e2d\u4e00\u6761\u8bb0\u5f55\");\r\n\t\t\t\treturn;\r\n\t\t\t}\r\n\t\t\tif (selectedRowCount > 1){\r\n\t\t\t\tPUI.MessageBox.alert(\"\u6bcf\u6b21\u53ea\u80fd\u9009\u62e9\u4e00\u6761\u8bb0\u5f55\u8fdb\u884c\u4fee\u6539\");\r\n\t\t\t\treturn;\r\n\t\t\t}\r\n\t\t\tvar selectedItem = mmGrid.selectedRows()[0];\r\n\t\t\tvar rowIndex = mmGrid.selectedRowsIndex;\r\n\t\t\t// \u83b7\u53d6\u6a21\u677f\r\n\t\t\tvar templateHtml = PUI.String.format($(\"#user-detail-template\").html(),$.extend(selectedItem, {rowIndex: rowIndex}));\r\n\t\t\t$(\"#${tableInfo.camelTabName}_add_content\").html(templateHtml);\r\n\t\t\t${tableInfo.upperCamelTabName}Details.init(selectedItem);\r\n\t\t\t$(\"#tabwin_add\").popup();\r\n\t\t},\r\n\t\t\r\n\t\t/**\r\n\t\t * \u5220\u9664\r\n\t\t */\r\n\t\tdeleteHandler: function() {\r\n\t\t\t\r\n\t\t\tif (mmGrid.selectedRowsIndex().length < 1){\r\n\t\t\t\tPUI.MessageBox.alert(\"\u8bf7\u81f3\u5c11\u9009\u4e2d\u4e00\u6761\u8bb0\u5f55\");\r\n\t\t\t\treturn;\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tvar params = [];\r\n\t\t\tvar items = mmGrid.selectedRows();\r\n\t\t\tfor (var i = 0;i < items.length; i++) {\r\n\t\t\t\tparams.push({name: \"ids\", value: items[i].${tableInfo.camelTabName}Id});\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\tPUI.MessageBox.show({\r\n\t\t\t    title: \"\u5220\u9664\u4fe1\u606f\",\r\n\t\t\t    content: \"\u662f\u5426\u5220\u9664\uff1f\",\r\n\t\t\t    type: \"confirm\",\r\n\t\t\t    buttons: [{ value: \"\u662f\" },{ value: \"\u5426\" }],\r\n\t\t\t    success: function (result) {\r\n\t\t\t        if (result === \"\u662f\") {\r\n\t\t\t        \t$.post(\"cdci/${tableInfo.camelTabName}/deleteByIds.shtml\", params, function(data) {\r\n\t\t\t\t\t\t\tif(data[''result''] == true){\r\n\t\t\t\t\t\t\t\tPUI.MessageBox.info(\"\u64cd\u4f5c\u6210\u529f\uff01\", null, {autoClose: true, timeOut: 997, afterClose: function() {\r\n\t\t\t\t\t\t\t\t\t${tableInfo.upperCamelTabName}Query.queryHandler();\r\n\t\t\t\t\t\t\t\t}});\r\n\t\t\t\t\t\t\t} else{\r\n\t\t\t\t\t\t\t\tPUI.MessageBox.error(\"\u64cd\u4f5c\u5931\u8d25\");\r\n\t\t\t\t\t\t\t}\r\n\t\t\t        \t}, \"json\");\r\n\t\t\t        }\r\n\t\t\t    }\r\n\t\t\t});\r\n\t\t}\r\n\t};\r\n}();\r\n\r\n$().ready(function() {\r\n\t${tableInfo.upperCamelTabName}Query.init();\r\n});\r\n'));               
INSERT INTO PUBLIC.TEMPLATE(TEMPLATE_ID, CONFIG_ID, TEMPLATE_NAME, TEMPLATE_TYPE, GENERATE_BASE_PACKAGE_PATH, GENERATE_PACKAGE_PATH, GENERATE_SOURCE_FOLDER, GENERATE_SUFFIX, TEMPLATE_CONTENT) VALUES
('856a4c2899584314920827d4f57e1e31', '297e5d005ec7d642015ec7d7676e0000', 'controller.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/controller', 'src/main/java', 'Controller.java', STRINGDECODE('package ${packageName};\r\n\r\nimport java.util.List;\r\n\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.stereotype.Controller;\r\nimport org.springframework.web.bind.annotation.*;\r\n\r\nimport ${basePackageName}.ResultMessage;\r\nimport ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;\r\nimport ${basePackageName}.services.${tableInfo.upperCamelTabName}Service;\r\nimport com.ctosb.core.mybatis.Page;\r\n\r\n@Controller\r\n@RequestMapping(\"${tableInfo.camelTabName}\")\r\npublic class ${tableInfo.upperCamelTabName}Controller {\r\n\r\n    @Autowired\r\n    private ${tableInfo.upperCamelTabName}Service ${tableInfo.camelTabName}Service;\r\n\r\n    /**\r\n     * \u83b7\u53d6\u5217\u8868\r\n     * \r\n     * @param condition\r\n     * @return\r\n\t * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    @RequestMapping(value = \"/page\")\r\n    @ResponseBody\r\n    public Object page(@ModelAttribute ${tableInfo.upperCamelTabName}Model condition, @ModelAttribute Page page) {\r\n        return ${tableInfo.camelTabName}Service.getPageByCondition(condition, page);\r\n    }\r\n\r\n    /**\r\n     * \u4fdd\u5b58\r\n     * \r\n     * @param model\r\n     * @return\r\n\t * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    @RequestMapping(\"/save\")\r\n    @ResponseBody\r\n    public Object save(@ModelAttribute ${tableInfo.upperCamelTabName}Model model) {\r\n    \t// \u4fdd\u5b58\r\n\t\tint resultNum = ${tableInfo.camelTabName}Service.save(model);\r\n\t\t// \u8fd4\u56de\u662f\u5426\u6210\u529f\r\n\t\treturn new ResultMessage(resultNum > 0, model.getId());\r\n    }\r\n\r\n    /**\r\n     * \u5220\u9664\r\n     * \r\n     * @param ids\r\n     * @return\r\n\t * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    @RequestMapping(value = \"/deleteByIds\", method = RequestMethod.POST)\r\n    @ResponseBody\r\n    public Object deleteByIds(@RequestParam(\"ids\") List<Long> ids) {\r\n    \tif (null != ids) {\r\n\t\t\tint resultNum = ${tableInfo.camelTabName}Service.deleteByIds(ids);\r\n\t\t\treturn new ResultMessage(resultNum > 0);\r\n\t\t} else {\r\n\t\t\treturn new ResultMessage(false);\r\n\t\t}\r\n    }\r\n}\r\n')),
('af9edba6c601468182edb4db0c2f39dc', '297e5d005ec7d642015ec7d7676e0000', 'serviceImpl.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/service/impl', 'src/main/java', 'ServiceImpl.java', STRINGDECODE('package ${packageName};\r\n\r\nimport java.util.List;\r\n\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.stereotype.Service;\r\nimport ${basePackageName}.mapper.${tableInfo.upperCamelTabName}Mapper;\r\nimport ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;\r\nimport ${basePackageName}.services.${tableInfo.upperCamelTabName}Service;\r\nimport com.ctosb.core.mybatis.Page;\r\nimport com.ctosb.core.mybatis.PageList;\r\n\r\n@Service\r\npublic class ${tableInfo.upperCamelTabName}ServiceImpl implements ${tableInfo.upperCamelTabName}Service{\r\n\r\n    @Autowired\r\n    private ${tableInfo.upperCamelTabName}Mapper ${tableInfo.camelTabName}Mapper;\r\n\r\n    @Override\r\n    public PageList<${tableInfo.upperCamelTabName}Model> getPageByCondition(${tableInfo.upperCamelTabName}Model condition, Page page) {\r\n        return ${tableInfo.camelTabName}Mapper.get(condition, page);\r\n    }\r\n\r\n    /**\r\n     * \u4fdd\u5b58\u65b9\u6cd5\r\n     * \r\n     * @param model\r\n     * @return\r\n     */\r\n    public int save(${tableInfo.upperCamelTabName}Model model) {\r\n        Long id = model.getId();\r\n        if (id == null) {\r\n            return ${tableInfo.camelTabName}Mapper.insert(model);\r\n        } else {\r\n            return ${tableInfo.camelTabName}Mapper.update(model);\r\n        }\r\n    }\r\n\r\n    /**\r\n     * \u5220\u9664\u65b9\u6cd5\r\n     * \r\n     * @param id\r\n     * @return\r\n     */\r\n    public int deleteByIds(List<Long> ids) {\r\n        return ${tableInfo.camelTabName}Mapper.delete(ids);\r\n    }\r\n\r\n}\r\n'));      
INSERT INTO PUBLIC.TEMPLATE(TEMPLATE_ID, CONFIG_ID, TEMPLATE_NAME, TEMPLATE_TYPE, GENERATE_BASE_PACKAGE_PATH, GENERATE_PACKAGE_PATH, GENERATE_SOURCE_FOLDER, GENERATE_SUFFIX, TEMPLATE_CONTENT) VALUES
('91ef961ee4b24f5694b63ea05a0ee070', '297e5d005ec7d642015ec7d7676e0000', 'service.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/service', 'src/main/java', 'Service.java', STRINGDECODE('package ${packageName};\r\n\r\nimport java.util.List;\r\n\r\nimport ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;\r\nimport com.ctosb.core.mybatis.Page;\r\nimport com.ctosb.core.mybatis.PageList;\r\n\r\npublic interface ${tableInfo.upperCamelTabName}Service {\r\n\r\n    /**\r\n     * \u5206\u9875\u67e5\u8be2\u65b9\u6cd5\r\n     * @param condition\r\n     * @param page\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public PageList<${tableInfo.upperCamelTabName}Model> getPageByCondition(${tableInfo.upperCamelTabName}Model condition,Page page);\r\n    \r\n    /**\r\n     * \u65b0\u589e\u65b9\u6cd5\r\n     * @param model\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public int save(${tableInfo.upperCamelTabName}Model model);\r\n    \r\n    /**\r\n     * \u5220\u9664\u65b9\u6cd5\r\n     * @param id\r\n     * @return\r\n     * @author alan\r\n\t * @date ${.now}\r\n     */\r\n    public int deleteByIds(List<Long> ids);\r\n\r\n}\r\n')),
('f619dc6a13a6481daff93478847bbe27', '297e5d005ec7d642015ec7d7676e0000', 'jsp.ftl', 'byTable', 'com/ctosb/test', 'com/ctosb/test/jsp', 'src/main/webapp', 'Jsp.jsp', STRINGDECODE('<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>\r\n<%@ taglib uri=\"/pui-tag\" prefix=\"slt\"%>\r\n<script type=\"text/javascript\" src=\"cdci/js/${tableInfo.camelTabName}Details.js\"></script>\r\n<div class=\"form-search\" id=\"user-detail-template\">\r\n    <form class=\"form-horizontal\" id=\"${tableInfo.camelTabName}EditForm\" style=\"margin-top: 50px;\">\r\n    \t<input  type=\"hidden\" name=\"condition.distributionOrderId\" value=\"{{${tableInfo.camelTabName}Id}}\" />\r\n  <#list columnInfos as columnInfo>\r\n    <#if columnInfo_index%3 == 0 >\r\n        </div>\r\n        <#if columnInfo_has_next>\r\n        <div class=\"row-fluid\">\r\n        </#if>\r\n    </#if>\r\n            <div class=\"span4 control-group full\">\r\n                <label class=\"control-label\" for=\"\">${columnInfo.colComment}</label>\r\n                <div class=\"controls txt\">\r\n                    <input type=\"text\" name=\"condition.${columnInfo.camelColName}\" value=\"{{${columnInfo.camelColName}}}\" >\r\n                </div>\r\n            </div>\r\n  </#list>\r\n        \r\n    </form>\r\n    <div class=\"row-fluid\" style=\"margin-bottom: 30px;\">\r\n    <div class=\"form-search-btn\">\r\n        <a class=\"btn btn-info\" href=\"javascript:void(0)\" onclick=\"javascript:${tableInfo.upperCamelTabName}Details.saveHandler();\">\u4fdd\u5b58</a>\r\n        <a class=\"btn btn-info\" href=\"javascript:void(0)\" onclick=\"javascript:${tableInfo.upperCamelTabName}Details.cancelHandler();\">\u53d6\u6d88</a>\r\n    </div>\r\n    </div>\r\n</div>')),
('935868a989f6473496c520e6033bd044', '297e5d005ec7d642015ec7d7676e0000', 'js.ftl', 'bySql', 'com/ctosb/test', 'com/ctosb/test/js', 'src/main/webapp', 'Js.js', STRINGDECODE('/**\r\n * \u7f16\u8f91\r\n * @param id\r\n * @author Alan\r\n * @date ${.now}\r\n */\r\nfunction edit(id) {\r\n\t$.ajax({\r\n\t\turl : \"subforum/get.shtml\",\r\n\t\t// \u6570\u636e\u53d1\u9001\u65b9\u5f0f\r\n\t\ttype : \"post\",\r\n\t\t// \u63a5\u53d7\u6570\u636e\u683c\u5f0f\r\n\t\tdataType : \"json\",\r\n\t\t// \u8981\u4f20\u9012\u7684\u6570\u636e\r\n\t\tdata : {\r\n\t\t\t\"id\" : id\r\n\t\t},\r\n\t\t// \u56de\u8c03\u51fd\u6570\uff0c\u63a5\u53d7\u670d\u52a1\u5668\u7aef\u8fd4\u56de\u7ed9\u5ba2\u6237\u7aef\u7684\u503c\uff0c\u5373result\u503c\r\n\t\tsuccess : function(result) {\r\n\t\t\tPopUtil.popForm(''form'',result);\r\n//\t\t\tfor ( var key in result) {\r\n//\t\t\t\t// \u6587\u672c\u6846\u8d4b\u503c\r\n//\t\t\t\t$(\"#\" + key).val(result[key]);\r\n//\t\t\t}\r\n\t\t}\r\n\t});\r\n}\r\n\r\n/**\r\n * \u590d\u5236\r\n * @param id\r\n * @author Alan\r\n * @date ${.now}\r\n */\r\nfunction copy(id) {\r\n\t$.ajax({\r\n\t\turl : \"subforum/get.shtml\",\r\n\t\t// \u6570\u636e\u53d1\u9001\u65b9\u5f0f\r\n\t\ttype : \"post\",\r\n\t\t// \u63a5\u53d7\u6570\u636e\u683c\u5f0f\r\n\t\tdataType : \"json\",\r\n\t\t// \u8981\u4f20\u9012\u7684\u6570\u636e\r\n\t\tdata : {\r\n\t\t\t\"id\" : id\r\n\t\t},\r\n\t\t// \u56de\u8c03\u51fd\u6570\uff0c\u63a5\u53d7\u670d\u52a1\u5668\u7aef\u8fd4\u56de\u7ed9\u5ba2\u6237\u7aef\u7684\u503c\uff0c\u5373result\u503c\r\n\t\tsuccess : function(result) {\r\n\t\t\tresult[\"id\"]=\"\";\r\n\t\t\tPopUtil.popForm(''form'',result);\r\n//\t\t\tfor ( var key in result) {\r\n//\t\t\t\t// \u6587\u672c\u6846\u8d4b\u503c\r\n//\t\t\t\t$(\"#\" + key).val(result[key]);\r\n//\t\t\t\t$(\"#id\").val('''');\r\n//\t\t\t}\r\n\t\t}\r\n\t});\r\n}\r\n/**\r\n * \u5220\u9664\r\n * @param id\r\n * @param obj\r\n * @author Alan\r\n * @date ${.now}\r\n */\r\nfunction del(id,obj) {\r\n\t$.ajax({\r\n\t\turl : \"subforum/del.shtml\",\r\n\t\t// \u6570\u636e\u53d1\u9001\u65b9\u5f0f\r\n\t\ttype : \"post\",\r\n\t\t// \u63a5\u53d7\u6570\u636e\u683c\u5f0f\r\n\t\tdataType : \"json\",\r\n\t\t// \u8981\u4f20\u9012\u7684\u6570\u636e\r\n\t\tdata : {\r\n\t\t\t\"id\" : id\r\n\t\t},\r\n\t\t// \u56de\u8c03\u51fd\u6570\uff0c\u63a5\u53d7\u670d\u52a1\u5668\u7aef\u8fd4\u56de\u7ed9\u5ba2\u6237\u7aef\u7684\u503c\uff0c\u5373result\u503c\r\n\t\tsuccess : function(result) {\r\n\t\t\tfor ( var key in result) {\r\n\t\t\t\t// \u6587\u672c\u6846\u8d4b\u503c\r\n\t\t\t\t$(obj).parents(''tr'').remove();\r\n\t\t\t\t$(''#form'')[0].reset();\r\n\t\t\t}\r\n\t\t}\r\n\t});\r\n}'));             
INSERT INTO PUBLIC.TEMPLATE(TEMPLATE_ID, CONFIG_ID, TEMPLATE_NAME, TEMPLATE_TYPE, GENERATE_BASE_PACKAGE_PATH, GENERATE_PACKAGE_PATH, GENERATE_SOURCE_FOLDER, GENERATE_SUFFIX, TEMPLATE_CONTENT) VALUES
('ab9a4cfb914f4e7a977653e03a19977b', '297e5d005ec7d642015ec7d7676e0000', 'jsp.ftl', 'bySql', 'com/ctosb/test', 'com/ctosb/test/jsp', 'src/main/webapp', 'Jsp.jsp', STRINGDECODE('<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"%>\r\n<%@page import=\"com.alan.system.model.Subforum\"%>\r\n<%@page import=\"java.util.List\"%>\r\n<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\" %>\r\n\r\n<jsp:include page=\"/jsp/common/header.jsp\"></jsp:include>\r\n\r\n<div class=\"container\">\r\n\t<div class=\"row\">\r\n\t\t<div class=\"span12\">\r\n\t\t\t<table class=\"table table-bordered table-hover\">\r\n\t\t\t\t<tr><#list columnInfos as columnInfo>\r\n\t\t\t\t\t<th>${columnInfo.colComment}</th>\r\n\t\t\t\t\t</#list>\r\n\t\t\t\t</tr>\r\n\t\t\t\t\r\n\t\t\t\t<c:forEach items=\"${tableInfo.camelTabName}\"  var=\"var\">\r\n\t\t\t\t\t<tr>\r\n\t\t\t\t\t<#list columnInfos as columnInfo>\r\n\t\t\t\t\t<td>${columnInfo.camelColName}</td>\r\n\t\t\t\t\t</#list>\r\n\t\t\t\t\t<td>\r\n\t\t\t\t\t\t<a onclick=\"edit()\">\u7f16\u8f91</a>|\r\n\t\t\t\t\t\t<a onclick=\"del()\">\u5220\u9664</a>|\r\n\t\t\t\t\t\t<a onclick=\"copy()\">\u590d\u5236</a>\r\n\t\t\t\t\t</td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t</c:forEach>\r\n\t\t\t</table>\r\n\t\t</div>\r\n\t</div>\r\n\t<div class=\"row\">\r\n\t\t<div class=\"span12\" style=\"display:none\">\r\n\t\t\t<form id=\"form\" action=\"${tableInfo.camelTabName}/add.shtml\" class=\"form-horizontal\"\r\n\t\t\t\tmethod=\"post\">\r\n\t\t\t\t<input name=\"id\" type=\"hidden\" id=\"id\">\r\n\t\t\t\t<!-- <div class=\"control-group\">\r\n\t\t\t\t\t<div class=\"controls\">\r\n\t\t\t\t\t\t<label\r\n\t\t\t\t\t\t\tstyle=\"font-size: 28px; font-weight: bolder; margin-top: 10px;\"></label>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div> -->\r\n\t\t\t\t<#list columnInfos as columnInfo>\r\n\t\t\t\t<div class=\"control-group\">\r\n\t\t\t\t\t<label class=\"control-label\">${columnInfo.colComment}</label>\r\n\t\t\t\t\t<div class=\"controls\">\r\n\t\t\t\t\t\t<input name=\"${columnInfo.camelColName}\" type=\"text\" id=\"${columnInfo.camelColName}\" placeholder=\"\">\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t\t</#list>\r\n\t\t\t\t\r\n\t\t\t\t<div class=\"control-group\">\r\n\t\t\t\t\t<div class=\"controls\">\r\n\t\t\t\t\t\t<input type=\"submit\" class=\"btn\" value=\"\u4fdd\u5b58\" /> <input\r\n\t\t\t\t\t\t\ttype=\"reset\" class=\"btn\" value=\"\u91cd\u586b\" />\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\r\n\t\t\t</form>\r\n\t\t</div>\r\n\t</div>\r\n</div>\r\n<jsp:include page=\"/jsp/common/footer.jsp\"></jsp:include>\r\n<script type=\"text/javascript\" src=\"js/${tableInfo.camelTabName}.js\"></script>\r\n<script type=\"text/javascript\" src=\"js/PopUtil.js\"></script>'));         
