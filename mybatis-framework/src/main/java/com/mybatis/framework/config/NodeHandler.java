package com.mybatis.framework.config;

import org.dom4j.Element;

import java.util.List;

/**
 *  ��Բ�ͬ�ӱ�ǩ���д�������֮�󣬷�װ����Ӧ��SqlNode������
 *  ����if��ǩ������֮�󣬻��װ��IfSqlNode������
 */
public interface NodeHandler {

    void handleNode(Element nodeToHandle, List<SqlNode> contents);

}
