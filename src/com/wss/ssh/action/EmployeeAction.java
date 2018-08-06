package com.wss.ssh.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.wss.ssh.entities.Employee;
import com.wss.ssh.service.DepartmentService;
import com.wss.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,
ModelDriven<Employee>,Preparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeService employeeService;
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	private Integer id;
	private String lastName;
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	
	/**
	 * 验证员工是否存在
	 */
	public String validateLastName() {
		if(employeeService.lastNameIsValid(lastName)){
			try {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
		}else{
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
		}
		//Ajax.同delete
		return "delete";
	}
	/**
	 * 添加员工信息
	 */
	public String input() {
		request.put("departments", departmentService.getAll());
		return "input";
	}
	
	public void prepareInput() {
		if (id != null) {
			model = employeeService.get(id);
		}
	}
	
	public String save() {
		System.out.println(model);
		if(id == null) {
			model.setCreatTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return "save";
	}
	
	public void prepareSave() {
		if (id == null) {
			model = new Employee();
		}else {
			model = employeeService.get(id);
		}
	}
	
	
	/**
	 * 删除员工
	 * @return
	 */
	public String delete() {
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "delete";
	}

	/**
	 * 查询所有员工
	 * @return
	 */
	public String list() {
		request.put("employees", employeeService.getAll());
		return "list";
	}

	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	@Override
	public void prepare() throws Exception {
		
	}

	private Employee model;
	
	@Override
	public Employee getModel() {
		return model;
	}

}
