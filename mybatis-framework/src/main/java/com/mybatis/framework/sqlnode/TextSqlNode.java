package com.mybatis.framework.sqlnode;

import com.mybatis.framework.sqlsource.DynamicContext;
import com.mybatis.framework.utils.GenericTokenParser;
import com.mybatis.framework.utils.OgnlUtils;
import com.mybatis.framework.utils.SimpleTypeRegistry;
import com.mybatis.framework.utils.TokenHandler;

public class TextSqlNode implements SqlNode {
    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    /**
     * Ҫ����${} ����˵username like '%${username}' ���username=��zhangsan�� ����֮��username
     * like '%zhangsan'
     */
    @Override
    public void apply(DynamicContext context) {
        TokenHandler tokenHandler = new BindingTokenParser(context);
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", tokenHandler);
        // tokenParser.parse(sqlText)������δ����ģ�����ֵ���Ѵ���ģ�û��${}��
        context.appendSql(tokenParser.parse(sqlText));
    }

    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }
        return false;
    }

    private static class BindingTokenParser implements TokenHandler {
        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;
        }

        /**
         * expression������˵${username}����ôexpression����username usernameҲ����Ognl���ʽ
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBindings().get("_parameter");
            if (paramObject == null) {
                // context.getBindings().put("value", null);
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            }

            // ʹ��Ognl apiȥ��ȡ��Ӧ��ֵ
            Object value = OgnlUtils.getValue(expression, context.getBindings());
            String srtValue = value == null ? "" : String.valueOf(value);
            return srtValue;
        }

    }
}
