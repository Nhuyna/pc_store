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
                Employee emp = new Employee(id, name, position, salary, phoneNumber, email, dateOfJoining);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    
    // Thêm nhân viên
    public boolean addEmployee(Employee emp) {
        // Kiểm tra xem homeAddress có tồn tại trong Employee hay không
        String sql = "INSERT INTO nhanvien (TenNhanVien, ViTri, Luong, SDT, Mail, NgayVaoLam) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());               // TenNhanVien
            ps.setString(2, emp.getPosition());           // ViTri
            ps.setDouble(3, emp.getSalary());             // Luong
            ps.setString(4, emp.getPhoneNumber());        // SDT
            ps.setString(5, emp.getEmail());              // Mail
            ps.setDate(6, Date.valueOf(emp.getDateOfJoining())); // NgayVaoLam

    
            // Thực thi câu lệnh và kiểm tra kết quả
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // In chi tiết lỗi để debug dễ hơn
            System.err.println("Lỗi khi thêm nhân viên: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateEmployee(Employee emp) {
        // Câu lệnh SQL để cập nhật thông tin nhân viên trong bảng nhanvien
        String sql = "UPDATE nhanvien SET TenNhanVien = ?, ViTri = ?, Luong = ?, SDT = ?, Mail = ?, NgayVaoLam = ? WHERE id = ?";
    
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Thiết lập các tham số của câu lệnh SQL từ đối tượng Employee
            ps.setString(1, emp.getName());               // TenNhanVien
            ps.setString(2, emp.getPosition());           // ViTri
            ps.setDouble(3, emp.getSalary());             // Luong
            ps.setString(4, emp.getPhoneNumber());        // SDT
            ps.setString(5, emp.getEmail());              // Mail
            ps.setDate(6, Date.valueOf(emp.getDateOfJoining())); // NgayVaoLam
            ps.setInt(7, emp.getId());                    // id của nhân viên cần sửa
    
            // Thực thi câu lệnh và kiểm tra kết quả
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // In chi tiết lỗi để debug dễ hơn
            System.err.println("Lỗi khi cập nhật thông tin nhân viên: " + e.getMessage());
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
