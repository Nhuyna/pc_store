package BUS;

import DAO.EmployeeDAO;
import DTO.Employee;
import java.time.LocalDate;
import java.util.List;

public class EmployeeBUS {
    private EmployeeDAO employeeDAO;

    public EmployeeBUS(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean addEmployee(Employee employee) {
        System.out.println("Thêm nhân viên vào hệ thống");
    
        // Kiểm tra tính hợp lệ của email
        if (!employee.getEmail().contains("@")) {
            return false; // Email không hợp lệ
        }

        LocalDate date = employee.getDateOfJoining();
    
        if (date == null) {
            return false; 
        }
    
        Employee newEmployee = new Employee(
            0, 
            employee.getName(), 
            employee.getPosition(), 
            employee.getPhoneNumber(), 
            employee.getEmail(), 
            date
        );
    
      
        return employeeDAO.addEmployee(newEmployee);
    }
    
    // Phương thức chỉnh sửa thông tin nhân viên (ví dụ)
    public boolean updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    // Phương thức xóa nhân viên
    public boolean deleteEmployee(int employeeId) {
        return employeeDAO.deleteEmployee(employeeId);
    }

    // Phương thức lấy danh sách nhân viên
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
