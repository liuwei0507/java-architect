package com.mybatis.framework.sqlnode;

import org.dom4j.Element;

import java.util.List;

/**
 * ��Բ�ͬ�ӱ�ǩ���д�������֮�󣬷�װ����Ӧ��SqlNode������
 * ����if��ǩ������֮�󣬻��װ��IfSqlNode������
 */
public interface NodeHandler {

    /**
     * ������ı��ڵ�
     *
     * @param nodeToHandle �������ӽڵ�
     * @param contents     ����֮��Ľڵ㼯��
     */
    void handleNode(Element nodeToHandle, List<SqlNode> contents);

}
