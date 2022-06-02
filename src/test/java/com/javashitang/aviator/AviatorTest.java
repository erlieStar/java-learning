package com.javashitang.aviator;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lilimin
 * @Date 2022/5/30
 */
@Slf4j
public class AviatorTest {

    @Test
    public void test1() {
        Long result = (Long) AviatorEvaluator.execute("1 + 2 + 3");
        System.out.println(result);
    }

    @Test
    public void test2() {
        Map<String, String> demoParams = Maps.newHashMap();
        demoParams.put("P1", "10");
        String variable = "[{\"name\":\"P1\",\"from\":\"BOM-CSBL\",\"para\":\"srCWMJ\"}]";
        this.check("P1>=10", variable, demoParams);
    }

    public Number check(String rule, String variable, Map<String, String> demoParams) {
        if (rule == null || variable == null) {
            throw new RuntimeException("公式校验参数为空");
        }
        List<String> variableNames = null;
        try {
            Expression expression = AviatorEvaluator.compile(rule);
            variableNames = expression.getVariableNames();
        } catch (Exception e) {
            throw new RuntimeException("解析公式格式错误");
        }
        variableNames.remove("true");
        variableNames.remove("false");
        List<HashMap> paramList = JSON.parseArray(variable, HashMap.class);
        List<String> params = Lists.newArrayListWithCapacity(paramList.size());
        if (variableNames.size() == 0 || paramList.size() == 0) {
            throw new RuntimeException("公式校验参数为空");
        }
        paramList.stream().forEach(p -> {
            String name = (String) p.get("name");
            String from = (String) p.get("from");
            String para = (String) p.get("para");
            if (StringUtils.isEmpty(name)) {
                throw new RuntimeException("公式:" + rule + "变量解释名称为空");
            }
            if (StringUtils.isEmpty(from)) {
                throw new RuntimeException("公式:" + rule + "变量解释来源为空");
            }
            if (StringUtils.isEmpty(para)) {
                throw new RuntimeException("公式:" + rule + "变量解释参数为空");
            }
//            SimpleResponse<Parameter> response = parameterRpcService.findParameter(para);
//            if (response.hasSuccess()) {
//                if (response.getData() == null) {
//                    ErrorCode.PARAM_ERROR.throwExeption("公式:" + rule + "变量解释参数无效");
//                }
//            }
            params.add(name);
        });
        if (variableNames.size() != paramList.size()) {
            throw new RuntimeException("公式:" + rule + "变量解释错误，无法匹配解释如下：" + variable);
        }
        for (String variableName : variableNames) {
            if (!params.contains(variableName)) {
                throw new RuntimeException("公式:" + rule + "变量解释错误，非法解释如下：" + variable);
            }
        }

        if (demoParams != null) {
            try {
                String checkRuleStr = rule;
                for (String key : demoParams.keySet()) {
                    if (checkRuleStr.contains(key)) {
                        checkRuleStr = checkRuleStr.replace(key, demoParams.get(key));
                    }
                }
                Object result = AviatorEvaluator.execute(checkRuleStr); // 模拟输入
                log.info("BOM试算校验公式：{}，结果：{}", checkRuleStr, String.valueOf(result));
                if (!(result instanceof Number)) {
                    throw new RuntimeException("试算结果类型错误");
                }
                return (Number) result;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("模拟公式计算错误");
            }
        }
        return null;
    }
}
