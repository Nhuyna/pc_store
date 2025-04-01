package DAO;

import config.DatabaseConnection;
import DTO.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() {
        // Sử dụng kết nối từ DatabaseConnection, nếu có lỗi thì sẽ ném ra RuntimeException
        conn = DatabaseConnection.getConnection();
    }

    // Lấy danh sách tất cả nhân viên
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien"; // Tên bảng phải khớp với CSDL của bạn
    
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                int id = rs.getInt("IDNhanVien");
                String name = rs.getString("TenNhanVien");
                String position = rs.getString("ViTri");
                double salary = rs.getDouble("Luong");
                String phoneNumber = rs.getString("SDT");
                String email = rs.getString("Mail");
                // Giả sử trường date_of_joining được lưu dưới dạng DATE
                LocalDate dateOfJoining = rs.getDate("NgayVaoLam").toLocalDate();
                
                // Lấy thông tin địa chỉ từ các cột riêng lẻ và kết hợp lại
                String soNhaDuong = rs.getString("SoNhaDuong");
                String quanHuyen = rs.getString("QuanHuyen");
                String tinhThanhPho = rs.getString("TinhThanhPho");
                String homeAddress = soNhaDuong + ", " + quanHuyen + ", " + tinhThanhPho;
                
                Employee emp = new Employee(id, name, position, salary, phoneNumber, email, dateOfJoining, homeAddress);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    // Thêm nhân viên
    public boolean addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, position, salary, phone_number, email, date_of_joining, home_address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPosition());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getPhoneNumber());
            ps.setString(5, emp.getEmail());
            ps.setDate(6, Date.valueOf(emp.getDateOfJoining()));
            ps.setString(7, emp.getHomeAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Cập nhật thông tin nhân viên
    public boolean updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET name = ?, position = ?, salary = ?, phone_number = ?, email = ?, date_of_joining = ?, home_address = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPosition());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getPhoneNumber());
            ps.setString(5, emp.getEmail());
            ps.setDate(6, Date.valueOf(emp.getDateOfJoining()));
            ps.setString(7, emp.getHomeAddress());
            ps.setInt(8, emp.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Xóa nhân viên theo id
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
