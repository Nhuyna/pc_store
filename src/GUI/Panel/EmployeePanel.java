package GUI.Panel;

import DAO.EmployeeDAO;
import DTO.Employee;
import GUI.Components.MenuChucNang;
import GUI.Dialog.ThemNhanVien;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.EmployeeBUS;

public class EmployeePanel extends JPanel {
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeePanel() {
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.WHITE);

        add(createEmployeeToolbar(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);

        // 🔹 Gọi hàm để lắng nghe sự kiện chọn dòng
        addTableSelectionListener();
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getAllEmployees();


        // Tiêu đề cột
        String[] columnNames = {"ID", "Họ Tên", "Chức Vụ", "Lương", "Số Điện Thoại", "Email", "Ngày Vào Làm", "Địa Chỉ"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Thiết lập header bảng
        JTableHeader header = employeeTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);

        // Căn giữa nội dung bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Đổ dữ liệu vào bảng
        for (Employee emp : employees) {
            tableModel.addRow(new Object[]{
                emp.getId(), emp.getName(), emp.getPosition(), emp.getSalary(),
                emp.getPhoneNumber(), emp.getEmail(), emp.getDateOfJoining(), emp.getHomeAddress()
            });
        }

        // Áp dụng căn giữa cho tất cả các cột
        for (int i = 0; i < employeeTable.getColumnCount(); i++) {
            employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Thiết lập bảng
        employeeTable.setShowGrid(false);
        employeeTable.setRowHeight(30);
        employeeTable.setSelectionBackground(new Color(173, 216, 230));
        employeeTable.setSelectionForeground(Color.BLACK);

        // Đưa bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createEmployeeToolbar() {
        JPanel toolbar = new JPanel(new GridLayout(1, 2, 10, 10));
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        toolbar.setPreferredSize(new Dimension(950, 110));
        
        MenuChucNang menu = new MenuChucNang();
        toolbar.add(menu.createActionPanel(this));  
        toolbar.add(MenuChucNang.createSearchPanel()); 
        
        return toolbar;
    }

    public void editEmployee() {
        System.out.println("Hihihihihi");
    }

    public void searchEmployee() {
        throw new UnsupportedOperationException("Unimplemented method 'searchEmployee'");
    }

    public void deleteEmployee() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }

    public void ExportExcel() {
        throw new UnsupportedOperationException("Unimplemented method 'ExportExcel'");
    }

    public void openAddEmployeeDialog() {
        EmployeeBUS employeeBUS = new EmployeeBUS(new EmployeeDAO());
        ThemNhanVien nvmoi = new ThemNhanVien(employeeBUS);
        nvmoi.FormThemNv("Thêm nhân viên","Thêm");
    }

    public void openEditEmployeeDialog() {
        EmployeeBUS employeeBUS = new EmployeeBUS(new EmployeeDAO());
        ThemNhanVien nvmoi = new ThemNhanVien(employeeBUS);
        nvmoi.FormThemNv("Chỉnh sửa thông tin nhân viên","Cập nhật");
    }

    private void printSelectedEmployee(int selectedRow) {
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String position = (String) tableModel.getValueAt(selectedRow, 2);
        
        // 🔹 Sửa lỗi ép kiểu salary
        Object salaryObj = tableModel.getValueAt(selectedRow, 3);
        double salary = salaryObj instanceof Number ? ((Number) salaryObj).doubleValue() : 0;

        String phone = (String) tableModel.getValueAt(selectedRow, 4);
        String email = (String) tableModel.getValueAt(selectedRow, 5);

        // 🔹 Sửa lỗi lấy ngày
        Object dateObj = tableModel.getValueAt(selectedRow, 6);
        String dateOfJoining = dateObj != null ? dateObj.toString() : "N/A";

        // 🔹 Thêm lại địa chỉ
        String address = (String) tableModel.getValueAt(selectedRow, 7);

        // In thông tin nhân viên được chọn
        System.out.println("ID: " + id);
        System.out.println("Họ tên: " + name);
        System.out.println("Chức vụ: " + position);
        System.out.println("Lương: " + salary);
        System.out.println("Số điện thoại: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Ngày vào làm: " + dateOfJoining);
        System.out.println("Địa chỉ: " + address);
    }

    private int addTableSelectionListener() {
        final int[] selectedId = {-1}; // Dùng mảng để lưu giá trị có thể thay đổi được
    
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    selectedId[0] = (int) tableModel.getValueAt(selectedRow, 0);
                    System.out.println(selectedId[0]);
                }
            }
        });
    
        return selectedId[0]; // Lưu ý: giá trị này sẽ luôn là -1 ban đầu
    }
    


}
