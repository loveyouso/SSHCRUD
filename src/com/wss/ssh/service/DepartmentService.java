package com.wss.ssh.service;

import java.util.List;

import com.wss.ssh.dao.DepartmentDao;
import com.wss.ssh.entities.Department;	

public class DepartmentService {
	
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
}
