package com.wss.ssh.service;

import java.util.List;

import com.wss.ssh.dao.EmployeeDao;
import com.wss.ssh.entities.Employee;
public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public void delete(Integer id) {
		employeeDao.delete(id);
	}
	
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	public void saveOrUpdate(Employee employee) {
		employeeDao.saveOruUdate(employee);
	}
	
	public boolean lastNameIsValid(String lastName) {
		return employeeDao.getEmployeeFromLastName(lastName) == null;
	}

	public Employee get(Integer id) {
		return employeeDao.get(id);
	}
}
