package com.djn.cn.oa.test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @ClassName ActivitiBaseTest
 * @Description  TODO(这里用一句话描述这个类的作用)
 * @author 聂冬佳-服务
 * @date 2018年8月7日 下午9:50:57
 *
 */
public class ActivitiBaseTest {
	
	@Before 
	public void startTest(){
        

	}
	
	@After 
	public void afterTest(){
		// 测试后执行条件
	}
	/**使用代码创建工作流需要的25张表*/
    @Test
    public void createTable(){
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //连接数据库的配置
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/DB_Act?useUnicode=true&characterEncoding=utf8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");
        /**
            public static final String DB_SCHEMA_UPDATE_FALSE = "false";不能自动创建表，需要表存在
            public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";先删除表再创建表
            public static final String DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，自动创建表
         */
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEnginee对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }
    /**使用配置文件创建工作流需要的25张表*/
    @Test
    public void createTableByConfigTest(){
//      ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//      //工作流的核心对象，ProcessEnginee对象
//      ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
 
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")   //
                                    .buildProcessEngine();
        System.out.println("processEngine:"+processEngine);
    }
    
    /** 
     * 发布流程 
     * 发布流程后，流程文件会保存到数据库中 
     */  
    @Test  
    public void deployFlow(){  
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   

        RepositoryService repositoryService = processEngine.getRepositoryService();  
          
        //获取在classpath下的流程文件  
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("myleave.zip");  
        ZipInputStream zipInputStream = new ZipInputStream(in);  
        //使用deploy方法发布流程  
        repositoryService.createDeployment()  
                         .addZipInputStream(zipInputStream)  
                         .name("Myleave")  
                         .deploy();  
    }  
    @Test  
    public void queryProcdef(){  
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   

        RepositoryService repositoryService = processEngine.getRepositoryService();  
        //创建查询对象  
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();  
        //添加查询条件  
        query.processDefinitionKey("myProcess");//通过key获取  
            // .processDefinitionName("My process")//通过name获取  
            // .orderByProcessDefinitionId()//根据ID排序  
        //执行查询获取流程定义明细  
        List<ProcessDefinition> pds = query.list();  
        for (ProcessDefinition pd : pds) {  
            System.out.println("ID:"+pd.getId()+",NAME:"+pd.getName()+",KEY:"+pd.getKey()+",VERSION:"+pd.getVersion()+",RESOURCE_NAME:"+pd.getResourceName()+",DGRM_RESOURCE_NAME:"+pd.getDiagramResourceName());  
        }  
    }  
    /** 
     * 发布流程 
     */  
    @Test  
    public void startFlow(){  
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   
        RuntimeService runtimeService = processEngine.getRuntimeService();  
        /** 
         * 启动请假单流程  并获取流程实例 
         * 因为该请假单流程可以会启动多个所以每启动一个请假单流程都会在数据库中插入一条新版本的流程数据 
         * 通过key启动的流程就是当前key下最新版本的流程 
         *  
         */  
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");  
        System.out.println("id:"+processInstance.getId()+",activitiId:"+processInstance.getActivityId());  
    }  

    /** 
     * 查看任务 
     */  
    @Test  
    public void queryTask(){  
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   
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
    
    @Test  
    public void startTask(){  
    	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   

        TaskService taskService = processEngine.getTaskService();  
        //taskId 就是查询任务中的 ID  
        String taskId = "2504";  
        //完成请假申请任务  
        taskService.complete(taskId );  
    } 
    /** 
    * 查看任务 
    */  
   @Test  
   public void queryTask2(){  
   	   ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   
       //获取任务服务对象  
       TaskService taskService = processEngine.getTaskService();  
       //根据接受人获取该用户的任务  
       List<Task> tasks = taskService.createTaskQuery()  
                                   .taskAssignee("老板")  
                                   .list();  
       for (Task task : tasks) {  
           System.out.println("ID:"+task.getId()+",姓名:"+task.getName()+",接收人:"+task.getAssignee()+",开始时间:"+task.getCreateTime());  
       }  
   }  
   @Test  
   public void startTask2(){  
   	   ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();   
       TaskService taskService = processEngine.getTaskService();  
       //taskId 就是查询任务中的 ID  
       String taskId = "5002";  
       //完成请假申请任务  
       taskService.complete(taskId );  
   }  
}
