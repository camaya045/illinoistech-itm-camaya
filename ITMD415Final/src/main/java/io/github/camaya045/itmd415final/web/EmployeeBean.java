package io.github.camaya045.itmd415final.web;

import io.github.camaya045.itmd415final.entity.Department;
import io.github.camaya045.itmd415final.entity.Employee;
import io.github.camaya045.itmd415final.service.DepartmentService;
import io.github.camaya045.itmd415final.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(EmployeeBean.class.getName());

    @Inject
    private transient EmployeeService employeeService;

    @Inject
    private transient DepartmentService departmentService;

    private List<Employee> employee;
    private List<Department> departments;

    private int selectedDeptId;
    private String searchKeyword;

    @PostConstruct
    public void init(){
        employee = employeeService.getEmployees();
        departments = departmentService.getAllDepartments();

        String deptParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("deptId");

        if(deptParam != null){
            try {
                selectedDeptId = Integer.parseInt(deptParam);
                employee = employeeService.getEmployeesByDept(selectedDeptId);
            } catch (NumberFormatException e) {
                logger.warning("Invalid deptId parameter: " + deptParam);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid department ID"));
            }
        }
    }
    //search for employees
    public void search(){
        if(searchKeyword == null || searchKeyword.isBlank()){
            employee = employeeService.getEmployees();
        }else {
            employee = employeeService.searchEmployees(searchKeyword);
        }
    }
    //filter by department
    public void filter () {
        if (selectedDeptId == 0){
            employee = employeeService.getEmployees();
        }else {
            employee = employeeService.getEmployeesByDept(selectedDeptId);
        }
    }
    //getters and setters
    public List<Employee> getEmployees() {
        return employee;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    public int getSelectedDeptId() {
        return selectedDeptId;
    }

    public void setSelectedDeptId(int selectedDeptId) {
        this.selectedDeptId = selectedDeptId;
    }
    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}