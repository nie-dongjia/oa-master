package com.djn.cn.oa.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @ClassName ActivitiSpringTest
 * @Description spring 整合测试
 * @author 聂冬佳-服务
 * @date 2018年8月9日 下午10:12:46
 *
 */
public class ActivitiSpringTest extends AbstractTestCase{
	@Autowired
	private ProcessEngine processEngine;
	 /** 
     * 查看任务 
     */  
    @Test  
    public void queryTask(){  
        //获取任务服务对象  
        TaskService taskService = processEngine.getTaskService();  
        //根据接受人获取该用户的任务  
        List<Task> tasks = taskService.createTaskQuery()  
                                    .taskAssignee("张三")  
                                    .list();  
        for (Task task : tasks) {  
            System.out.println("ID:"+task.getId()+",姓名:"+task.getName()+",接收人:"+task.getAssignee()+",开始时间:"+task.getCreateTime());  
        }  
    }  
    
	
}
