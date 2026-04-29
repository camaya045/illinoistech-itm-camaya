package io.github.camaya045.itmd415final.web;

import io.github.camaya045.itmd415final.entity.Department;
import io.github.camaya045.itmd415final.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class DepartmentBean {

    @Inject
    private DepartmentService departmentService;

    private List<Department> departments;

    @PostConstruct
    public void init() {
        departments = departmentService.getAllDepartments();
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
