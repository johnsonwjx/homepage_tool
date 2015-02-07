package com.youngfriend.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import youngfriend.common.util.net.ServiceInvokerUtil;
import youngfriend.common.util.net.exception.ServiceInvokerException;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by pandongyu on 15/1/7.
 */
public enum V6LoginUtil {
    INSTANCE;
    public static final String V6LOGIN_USER = "user";
    public static final String V6LOGIN_PWD = "pwd";
    public static final String V6LOGIN_WEB_ADD = "web-add";
    public static final String V6LOGIN_SERVER_ADD = "server-add";
    public static final String V6LOGIN_SERVER_PROXY = "web-proxy";
    private static final Logger logger= LogManager.getLogger(V6LoginUtil.class) ;
    public static V6LoginUtil getInstance() {
        return INSTANCE;
    }

    public static boolean stringNull(String val) {
        return val == null || "".equals(val) ? true : false;
    }

    public static void loginSystem(String registerName, String password, String ip, String computerName) throws Exception {
        Hashtable sendTab = new Hashtable();
        sendTab.put("service", "useraccess.login");
        sendTab.put("registerName", registerName);
        sendTab.put("password", password);
        sendTab.put("ip", ip);
        sendTab.put("computerName", computerName);
        sendTab.put("keyNum", "");
        sendTab.put("ptype", "client");
        // UserAccess服务地址为空的时候报错
        if (stringNull(System.getProperties().getProperty("useraccess"))) {
            throw new ServiceInvokerException(V6LoginUtil.class, "验证用户失败！", "身份认证服务没有启用！");
        }
        Hashtable reTab = ServiceInvokerUtil.invoker(sendTab);
        String assID = (String) reTab.get("sysAccessID");
        System.getProperties().put("sysAccessID", assID);
    }

    public static String iniRuntimeEnv(boolean useInvokermodel, String url) {
        boolean proxy = true;// 始终通过web代理连接
        return setAllServiceInfo(url, proxy, useInvokermodel);
    }

    public static String sendData(String msgVar, String serviceUrl) throws Exception {
        return youngfriend.common.util.net.ServiceInvokerUtil.httpGet(serviceUrl, msgVar);
    }

    public static String setAllServiceInfo(String serviceUrl, boolean useWebProxy, boolean useInvokermodel) {
        try {

            if (serviceUrl == null) {
                serviceUrl = "http://127.0.0.1:8080/yfengine";
            }
            // 检验地址的合法性
            if (!serviceUrl.toLowerCase().startsWith("http://")) {
                serviceUrl = "http://" + serviceUrl;
            }
            if (useWebProxy) {
                serviceUrl = serviceUrl + "/webproxy";
            }
            if (useInvokermodel) {
                ServiceInvokerUtil.invokermodel = "2";
            }

            String msgVar = "service := system.poperties.serviceslocation\nxml := ";
            String reMsg = sendData(msgVar, serviceUrl);// 调用服务并返回值

            if (stringNull(reMsg)) {
                throw new Exception("取所有服务的地址映射失败!");
            } else {
                reMsg = reMsg.substring(reMsg.indexOf(":=") + 2).trim();
                if (stringNull(reMsg)) {
                    throw new Exception("取所有服务的地址映射失败!");
                }
            }
            Document doc = DocumentHelper.parseText(reMsg);
            String serverUrl = null;
            // 循环所有服务,并把服务名和地址写入内存
            for (Iterator iter = doc.getRootElement().elements("service").iterator(); iter.hasNext();) {
                Element element = (Element) iter.next();
                String key = element.attributeValue("name");
                // String value = serviceUrl;//用输入的地址作为服务地址
                // 因为有可能有服务器集群，所以不能用输入的地址作为服务地址
                String value = "http://" + element.attributeValue("addr");
                if (serverUrl == null) {
                    serverUrl = value;
                }
                if (useWebProxy)// 使用集群，那样所有服务都用web地址
                {
                    value = serviceUrl;
                }
                System.getProperties().put(key, value);
            }
            return "ok";
        } catch (Exception e) {
             logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
