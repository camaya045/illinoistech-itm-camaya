package io.github.camaya045.itmd415final.service;

import io.github.camaya045.itmd415final.entity.Department;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class DepartmentService {

    @PersistenceContext(unitName = "deptPU")
    private EntityManager em;

    //  Get all departments
    public List<Department> getAllDepartments() {
        return em.createQuery(
                "SELECT d FROM Department d ORDER BY d.deptId",
                Department.class
        ).getResultList();
    }

    // Find one department
    public Department getDepartmentById(int deptId) {
        return em.find(Department.class, deptId);
    }
}