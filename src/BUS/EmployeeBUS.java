package BUS;

import DAO.EmployeeDAO;
import DTO.Employee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
        // Kiểm tra nếu ngày sinh tồn tại trong Employee và chuyển đổi
        LocalDate birthDate = employee.getDateOfJoining(); // Giả sử ngày sinh được lưu trong dateOfJoining
    
        if (birthDate == null) {
            return false; // Nếu không có ngày sinh hợp lệ
        }
    
        // Tạo đối tượng Employee mới từ dữ liệu đầu vào
        Employee newEmployee = new Employee(
            0, 
            employee.getName(), 
            employee.getPosition(), 
            employee.getSalary(), 
            employee.getPhoneNumber(), 
            employee.getEmail(), 
            birthDate
        );
    
        // Lưu vào cơ sở dữ liệu
        return employeeDAO.addEmployee(newEmployee);
    }
    
    // Phương thức chỉnh sửa thông tin nhân viên (ví dụ)
    public boolean updateEmployee(Employee employee) {
        // Logic cập nhật nhân viên
        return employeeDAO.updateEmployee(employee);
    }

    // Phương thức xóa nhân viên
    public boolean deleteEmployee(int employeeId) {
        return employeeDAO.deleteEmployee(employeeId);
    }
}
