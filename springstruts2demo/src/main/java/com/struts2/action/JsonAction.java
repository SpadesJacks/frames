package com.struts2.action;

import com.alibaba.dubbo.common.json.JSON;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/20 16:28
 * classType:
 */
public class JsonAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest request;

    private String result;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 处理ajax请求
     *
     * @return SUCCESS
     */
    public String excuteAjax() {
        try {
            //获取数据
//            String name = "123";
//            Integer age = 12;
//            String position = "浙江省杭州市滨江区";
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String position = request.getParameter("position");
            //将数据存储在map里，再转换成json类型数据，也可以自己手动构造json类型数据
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("age", age);
            map.put("position", position);
            String json = JSON.json(map);//将map对象转换成json类型数据
            result = json;//给result赋值，传递给页面
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    public String getPage(){
        return "SUCCESS";
    }
}
