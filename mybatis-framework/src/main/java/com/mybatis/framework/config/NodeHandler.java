package com.mybatis.framework.config;

import org.dom4j.Element;

import java.util.List;

/**
 *  针对不同子标签进行处理，处理之后，封装到对应的SqlNode对象中
 *  比如if标签被处理之后，会封装到IfSqlNode对象中
 */
public interface NodeHandler {

    void handleNode(Element nodeToHandle, List<SqlNode> contents);

}
