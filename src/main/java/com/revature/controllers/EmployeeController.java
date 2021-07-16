package com.revature.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Employees;
import com.revature.services.EmployeeService;

@RestController
@RequestMapping(path="/employees")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:4200","http://revproject2frontend.s3-website-us-west-2.amazonaws.com/"})
public class EmployeeController {

	private EmployeeService es;
	
	@Autowired
	public EmployeeController(EmployeeService es) {
		this.es = es;
	}
	
	@GetMapping
	public List<Employees> getAllEmployees(HttpServletRequest request) {
		System.out.println(request.getSession().getId());
		return es.getAll();
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Employees> getUserById(@PathVariable("id") Integer id) {
		Employees e = es.getById(id);
		return ResponseEntity.ok(e);
	}
	
	@PostMapping
	public ResponseEntity<Employees> addUser(@RequestBody Employees e) {
		Employees emp = es.add(e);
		if (emp != null) {
			return ResponseEntity.created(URI.create("http://localhost:8080/employees" + emp.getId())).build();
		}
		return ResponseEntity.status(409).build();
	}
	
	@PutMapping
	public ResponseEntity<Employees> updateUser(@RequestBody Employees e) {
		Employees emp = es.update(e);
		return ResponseEntity.created(URI.create("http://localhost:8080/employees" + emp.getId())).build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Integer> deleteUserById(@PathVariable("id") Integer id) {
		// Currently we won't have deleting account functionality
		// So this method does not really matter
		// In the future, should add some error handling based on delete
		es.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<Employees> login(@RequestBody Employees e, HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		Employees emp = es.login(e.getUsername(), e.getPassword());
		if (emp != null) {
			request.getSession().setAttribute("employee", emp);
			request.getSession().setAttribute("id", emp.getId());
			return ResponseEntity.ok(emp);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(path="/logout")
	public void logout(HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		request.getSession().invalidate();
	}
	
	@GetMapping(path="/account")
	public ResponseEntity<Employees> getInfo(HttpServletRequest request) {
		//System.out.println(request.getSession().getId());
		Employees e = (Employees)request.getSession().getAttribute("employee");
		Integer id = (Integer)request.getSession().getAttribute("id");
		System.out.println(e);
		System.out.println(id);
		if (e != null) {
			return ResponseEntity.ok(e);
		}
		return ResponseEntity.status(401).build();
	}
}
