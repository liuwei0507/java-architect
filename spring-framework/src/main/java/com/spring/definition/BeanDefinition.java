package com.spring.definition;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    private String clazzName;
    private String beanName;

    private String initMethod;
    private String scope;

    /**
     * benn中的属性信息
     */
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public BeanDefinition(String clazzName, String beanName) {
        this.clazzName = clazzName;
        this.beanName = beanName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void addPropertyValue(PropertyValue pv) {
        propertyValues.add(pv);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public boolean isSingleton() {
        return "singleton".equals(scope);
    }

    public boolean isPrototype() {
        return "prototype".equals(scope);
    }
}
