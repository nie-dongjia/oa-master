package com.djn.cn.oa.base.controller;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index/")
public class IndexController {
	@Autowired
	private ProcessEngine processEngine;

	@RequestMapping("index")
	public String toIndex() {
		return "index";
	}

	@RequestMapping("queryTask")
	@ResponseBody
	public String queryTask() {
		// 获取任务服务对象
		TaskService taskService = processEngine.getTaskService();
		// 根据接受人获取该用户的任务
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("张三").list();
		for (Task task : tasks) {
			System.out.println("ID:" + task.getId() + ",姓名:" + task.getName() + ",接收人:" + task.getAssignee() + ",开始时间:"
					+ task.getCreateTime());
		}
		return "success";
	}
}
