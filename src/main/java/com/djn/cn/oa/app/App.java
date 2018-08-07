package com.djn.cn.oa.app;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class App {
	public static void main(String[] arg){  
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   
        System.out.println("processEngine:"+processEngine);
    }  
}
