package com.mybatis.framework.config;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLScriptParser {

    private Configuration configuration;

    private Map<String, NodeHandler> nodeHandlerMap = new HashMap();

    private boolean isDynamic = false;

    public XMLScriptParser(Configuration configuration) {
        this.configuration = configuration;

        initNodeHanderMap();
    }

    private void initNodeHanderMap() {
        nodeHandlerMap.put("if", new IfHandler());
//        nodeHandlerMap.put("where", new WhereHandler());
//        nodeHandlerMap.put("foreach", new ForeachHandler());
    }

    public SqlSource parseScriptNode(Element selectElement) {
        // 首先先将sql脚本按照不同的类型，封装到不同的SqlNode
        MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
        // 再将SqlNode集合封装到SqlSource中
        SqlSource sqlSource = null;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(rootSqlNode);
        } else {
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        // 由于带有#{}和${}、动态标签的sql处理方式不同，所以需要封装到不同的SqlSource中
        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> contents = new ArrayList<>();
        int nodeCount = selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            // 需要去区分select标签的子节点类型
            // 如果是文本类型则封装到TextSqlNode或者StaticTextSqlNode
            if (node instanceof Text) {
                String sqlText = node.getText().trim();
                if (sqlText == null || sqlText.equals("")) {
                    continue;
                }
                TextSqlNode sqlNode = new TextSqlNode(sqlText);
                // 判断文本中是否带有${}
                if (sqlNode.isDynamic()) {
                    contents.add(sqlNode);
                    isDynamic = true;
                } else {
                    contents.add(new StaticTextSqlNode(sqlText));
                }
            } else if (node instanceof Element) {
                // 比如说if\where\foreach等动态sql子标签就需要在这处理
                // 根据标签名称，封装到不同的节点信息
                Element nodeToHandle = (Element) node;
                String nodeName = nodeToHandle.getName().toLowerCase();
                NodeHandler nodeHandler = nodeHandlerMap.get(nodeName);
                nodeHandler.handleNode(nodeToHandle, contents);

                isDynamic = true;
            }
        }
        return new MixedSqlNode(contents);
    }

    private class IfHandler implements NodeHandler {

        @Override
        public void handleNode(Element nodeToHandle, List<SqlNode> targetContents) {
            // 对if标签进行解析
            MixedSqlNode rootSqlNode = parseDynamicTags(nodeToHandle);

            String test = nodeToHandle.attributeValue("test");
            IfSqlNode ifSqlNode = new IfSqlNode(test, rootSqlNode);
            targetContents.add(ifSqlNode);
        }
    }
}
