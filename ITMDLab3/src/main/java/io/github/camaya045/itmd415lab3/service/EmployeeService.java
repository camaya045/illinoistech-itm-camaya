package io.github.camaya045.itmd415lab3.service;

import io.github.camaya045.itmd415lab3.entity.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RequestScoped
public class EmployeeService {
    @PersistenceContext(unitName = "deptPU")
    private EntityManager em;

    @Transactional
    public void createEmployee(int empId, String fullName, String title, int deptId, java.math.BigDecimal salary, java.time.LocalDate hireDate) {
        Department dept = em.find(Department.class, deptId);
        if (dept == null) throw new IllegalArgumentException("No such dept_id=" + deptId);
        Employee e = new Employee();
        e.setEmpId(empId);
        e.setFullName(fullName);
        e.setTitle(title);
        e.setDepartment(dept);
        e.setSalary(salary);
        e.setHireDate(hireDate);
        em.persist(e);
    }

    public Employee findEmployee(int empId) {
        return em.find(Employee.class, empId);
    }

    @Transactional
    public void updateTitle(int empId, String newTitle) {
        Employee e = em.find(Employee.class, empId);
        if (e == null)
            throw new IllegalArgumentException("No such emp_id=" + empId);  // Dirty checking: no explicit "update" call is needed.
        e.setTitle(newTitle);
    }
}
