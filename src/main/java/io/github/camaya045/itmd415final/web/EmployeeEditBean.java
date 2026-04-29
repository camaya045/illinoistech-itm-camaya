package io.github.camaya045.itmd415final.web;

import io.github.camaya045.itmd415final.entity.Department;
import io.github.camaya045.itmd415final.entity.Employee;
import io.github.camaya045.itmd415final.service.DepartmentService;
import io.github.camaya045.itmd415final.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@RolesAllowed("ADMIN")
@Named
@ViewScoped
public class EmployeeEditBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private transient EmployeeService employeeService;

    @Inject
    private transient DepartmentService departmentService;

    private Employee employee;
    private List<Department> departments;

    private int empId;
    private int selectedDeptId;

    @PostConstruct
    public void init() {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("empId");
        if (id != null) {
            empId = Integer.parseInt(id);
            employee = employeeService.getEmployeeById(empId);

            if (employee != null) {
                selectedDeptId = employee.getDepartment().getDeptId();
            } else  {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Employee not found"));
            }
        }
        departments = departmentService.getAllDepartments();
    }
    public String save() {
        try {
            Department dept = departmentService.getDepartmentById(selectedDeptId);
            employee.setDepartment(dept);
            employeeService.update(employee);

            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage(null, new FacesMessage("Employee updated successfully"));

            return "employees.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to update employee"));

            return null;
        }
    }
    // getters/setters
    public Employee getEmployee() { return employee; }
    public List<Department> getDepartments() { return departments; }

    public int getSelectedDeptId() { return selectedDeptId; }
    public void setSelectedDeptId(int selectedDeptId) { this.selectedDeptId = selectedDeptId; }
}