package com.mybatis.framework.config;

import com.mybatis.framework.sqlnode.IfSqlNode;
import com.mybatis.framework.sqlnode.MixedSqlNode;
import com.mybatis.framework.sqlnode.NodeHandler;
import com.mybatis.framework.sqlnode.SqlNode;
import com.mybatis.framework.sqlnode.StaticTextSqlNode;
import com.mybatis.framework.sqlnode.TextSqlNode;
import com.mybatis.framework.sqlsource.DynamicSqlSource;
import com.mybatis.framework.sqlsource.RawSqlSource;
import com.mybatis.framework.sqlsource.SqlSource;
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
        // �����Ƚ�sql�ű����ղ�ͬ�����ͣ���װ����ͬ��SqlNode
        MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
        // �ٽ�SqlNode���Ϸ�װ��SqlSource��
        SqlSource sqlSource = null;
        if (isDynamic) {
            sqlSource = new DynamicSqlSource(rootSqlNode);
        } else {
            sqlSource = new RawSqlSource(rootSqlNode);
        }
        // ���ڴ���#{}��${}����̬��ǩ��sql����ʽ��ͬ��������Ҫ��װ����ͬ��SqlSource��
        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> contents = new ArrayList<>();
        int nodeCount = selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            // ��Ҫȥ����select��ǩ���ӽڵ�����
            // ������ı��������װ��TextSqlNode����StaticTextSqlNode
            if (node instanceof Text) {
                String sqlText = node.getText().trim();
                if (sqlText == null || sqlText.equals("")) {
                    continue;
                }
                TextSqlNode sqlNode = new TextSqlNode(sqlText);
                // �ж��ı����Ƿ����${}
                if (sqlNode.isDynamic()) {
                    contents.add(sqlNode);
                    isDynamic = true;
                } else {
                    contents.add(new StaticTextSqlNode(sqlText));
                }
            } else if (node instanceof Element) {
                // ����˵if\where\foreach�ȶ�̬sql�ӱ�ǩ����Ҫ���⴦��
                // ���ݱ�ǩ���ƣ���װ����ͬ�Ľڵ���Ϣ
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
            // ��if��ǩ���н���
            MixedSqlNode rootSqlNode = parseDynamicTags(nodeToHandle);

            String test = nodeToHandle.attributeValue("test");
            IfSqlNode ifSqlNode = new IfSqlNode(test, rootSqlNode);
            targetContents.add(ifSqlNode);
        }
    }
}
