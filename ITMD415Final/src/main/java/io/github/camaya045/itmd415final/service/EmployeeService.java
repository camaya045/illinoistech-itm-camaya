package io.github.camaya045.itmd415final.service;

import io.github.camaya045.itmd415final.entity.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class EmployeeService {

    @PersistenceContext(unitName = "deptPU")
    private EntityManager em;

    // getting all employees
    public List<Employee> getEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }
    //Filtering by Department
    public List<Employee> getEmployeesByDept(int depId) {
        return em.createQuery("SELECT e FROM Employee e WHERE e.department.deptId = :deptId",
                        Employee.class
                ).setParameter("deptId", depId)
                .getResultList();
    }
    //Search employees by name or title
    public List<Employee> searchEmployees(String keyword) {
        return em.createQuery(
                        "SELECT e FROM Employee e " + "WHERE LOWER(e.fullName) LIKE LOWER(:keyword) " + "OR LOWER(e.title) LIKE LOWER(:keyword)",
                        Employee.class
                ).setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
    //getEmployeebyId method
    public Employee getEmployeeById(int id) {
        return em.find(Employee.class, id);
    }
    // Admin (update employee)
    public void update(Employee employee) {
        Employee managed = em.find(Employee.class, employee.getEmpId());

        if (managed == null) {
            throw new RuntimeException("Employee with id " + employee.getEmpId() + " was not found");
        }
        managed.setTitle(employee.getTitle());
        managed.setSalary(employee.getSalary());
        managed.setDepartment(employee.getDepartment());
    }
}