package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define a field for entityManager
	private EntityManager entityManager;
	
	// Set up a constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	//@Transactional ... Moved to service class
	public List<Employee> findAll() {
		
		// get current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute a query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results 
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// get the current Hibernate session
		 Session currentSession = entityManager.unwrap(Session.class);
		 
		 //get the employee
		 Employee theEmployee = currentSession.get(Employee.class, theId);
		 
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// get current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save or update the employee ... POST and PUT
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		// get current Hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with the primary key
		Query theQuery = currentSession.createQuery(
				"delete from Employee where id=: employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
