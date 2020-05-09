package com.bjut.JSONTest;

import com.alibaba.fastjson.JSON;

public class JsonTest {
    public static void main(String[] args) {
        String json = "{\"V_ZGBX_SBGRJBXX\":[{\"BAE039\":\"123\",\"AAC999\":\"3506599473\"}]}";
        System.out.println(json);

        /* 找出指定的2个字符在 该字符串里面的位置 */
        int strStartIndex = json.indexOf("[");
        int strEndIndex = json.indexOf("]");

        /* 截取字符串 */
        String result = json.substring(strStartIndex + 1, strEndIndex);
        System.out.println(result);

        V_ZGBX_SBGRJBXXa v_zgbx_sbgrjbxx = JSON.parseObject(result, V_ZGBX_SBGRJBXXa.class);

        System.out.println("BAE039:" + v_zgbx_sbgrjbxx.getBAE039());
        System.out.println("AAC999:" + v_zgbx_sbgrjbxx.getAAC999());
    }
}
