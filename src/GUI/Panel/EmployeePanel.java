package GUI.Panel;

import DAO.EmployeeDAO;
import DTO.Employee;
import GUI.Components.Button;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

public class EmployeePanel extends JPanel {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    EmployeeActionListener listener;

    public EmployeePanel() {
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.WHITE);

        add(createEmployeeToolbar(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
    
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getAllEmployees();
    
        String[] columnNames = {"ID", "Họ Tên", "Chức Vụ", "Lương", "Số Điện Thoại", "Email", "Ngày Vào Làm", "Địa Chỉ"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
    
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
            Object[] rowData = {
                emp.getId(), emp.getName(), emp.getPosition(), emp.getSalary(),
                emp.getPhoneNumber(), emp.getEmail(), emp.getDateOfJoining(), emp.getHomeAddress()
            };
            tableModel.addRow(rowData);
        }
    
        // Căn giữa chỉ nếu bảng có cột
        if (employeeTable.getColumnCount() > 0) {
            for (int i = 0; i < employeeTable.getColumnCount(); i++) {
                employeeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }


        //     // Không cho phép sửa dữ liệu trong bảng
        // tableModel = new DefaultTableModel(columnNames, 0) {
        //     @Override
        //     public boolean isCellEditable(int row, int column) {
        //         return false; // Mọi ô đều không thể sửa
        //     }
        // };

        // Thêm viền & tùy chỉnh bảng
        employeeTable.setRowHeight(30);
        employeeTable.setShowGrid(true);
        employeeTable.setGridColor(Color.LIGHT_GRAY);
        employeeTable.setRowSelectionAllowed(false); // Không cho chọn dòng
        employeeTable.setCellSelectionEnabled(false); // Không cho chọn ô
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setPreferredSize(new Dimension(950, 500));
        panel.add(scrollPane, BorderLayout.CENTER);
    
        return panel;
    }
    

    public JPanel createEmployeeToolbar() {
        JPanel toolbar = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 hàng, 2 cột, khoảng cách 10px
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        toolbar.setPreferredSize(new Dimension(950, 110));  // Kích thước đồng đều với các panel con
        toolbar.add(createActionPanel());
        toolbar.add(createSearchPanel());
        return toolbar;
    }

    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel(new GridLayout(1, 4, 10, 10)); // 1 hàng, 4 cột
        actionPanel.setPreferredSize(new Dimension(475, 100));  // Kích thước đồng đều
        actionPanel.setBackground(Color.WHITE);  // Đảm bảo nền trắng

        Button buttonFactory = new Button();  // Tạo một object của Button
        JButton btnAdd = buttonFactory.createStyledButton("Thêm", "./resources/icon/add.svg");
        JButton btnEdit = buttonFactory.createStyledButton("Sửa", "./resources/icon/edit.svg");
        JButton btnDelete = buttonFactory.createStyledButton("Xóa", "./resources/icon/delete.svg");
        JButton btnExport = buttonFactory.createStyledButton("Xuất", "./resources/icon/export.svg");

        // Gán sự kiện bấm nút
        btnAdd.addActionListener(e -> openAddEmployeeDialog());
        btnEdit.addActionListener(e -> editEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnExport.addActionListener(e -> ExportExcel());

        // Thêm nút vào panel
        actionPanel.add(btnAdd);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);
        actionPanel.add(btnExport);

        actionPanel.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        return actionPanel;
    }

    private Object editEmployee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editEmployee'");
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); // Khoảng cách giữa các thành phần
        searchPanel.setPreferredSize(new Dimension(475, 100));  // Điều chỉnh lại kích thước sao cho đồng đều
        searchPanel.setBackground(Color.WHITE); // Màu nền trắng

        JTextField searchField = new JTextField(20); // Tăng độ dài của ô nhập liệu
        searchField.setFont(new Font("Arial", Font.PLAIN, 16)); // Font chữ lớn hơn
        searchField.setPreferredSize(new Dimension(300, 40)); // Kích thước lớn hơn cho ô nhập liệu
        searchField.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2)); // Border màu xanh đẹp


        Button buttonFactory = new Button();
        JButton btnSearch= buttonFactory.createStyledButton("Thêm", "./resources/icon/find.svg");

        btnSearch.setHorizontalTextPosition(SwingConstants.RIGHT); 
        btnSearch.setVerticalTextPosition(SwingConstants.CENTER);

        btnSearch.setPreferredSize(new Dimension(120, 40)); // Kích thước lớn hơn cho nút tìm kiếm
        btnSearch.setFont(new Font("Arial", Font.BOLD, 16)); // Font lớn và dễ nhìn

        // Thêm các thành phần vào panel
        searchPanel.add(searchField);
        searchPanel.add(btnSearch);
        searchPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
            "Tìm kiếm", 
            TitledBorder.LEFT, TitledBorder.TOP, 
            new Font("Arial", Font.BOLD, 14), 
            new Color(100, 149, 237) // Màu xanh cho border của tiêu đề
        ));

        return searchPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
        });
        return button;
    }

    public void searchEmployee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchEmployee'");
    }

    public void deleteEmployee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEmployee'");
    }

    public void ExportExcel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ExportExcel'");
    }

    public void openAddEmployeeDialog() {
        // Chỉnh sửa dialog thêm nhân viên
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Nhân Viên", true);
        dialog.setSize(500, 350);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
        // ===== Labels và Input Fields =====
        String[] labels = {"Họ và Tên:", "Email:", "Giới tính:", "Chức vụ:", "Số điện thoại:", "Ngày sinh:", "Địa chỉ:"};
        JTextField txtName = new JTextField(15);
        JTextField txtEmail = new JTextField(15);
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        JTextField txtPosition = new JTextField(15);
        JTextField txtPhone = new JTextField(15);
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        JTextField txtAddress = new JTextField(15);
        
        JComponent[] components = {txtName, txtEmail, cbGender, txtPosition, txtPhone, dateChooser, txtAddress};
        
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            gbc.anchor = GridBagConstraints.LINE_END;
            dialog.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
            dialog.add(components[i], gbc);
        }

        // ===== Nút Lưu =====
        JButton btnSave = new JButton("Lưu");
        gbc.gridx = 0; gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(btnSave, gbc);

        // ===== Xử lý sự kiện Lưu =====
        btnSave.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String gender = (String) cbGender.getSelectedItem();
            String position = txtPosition.getText();
            String phone = txtPhone.getText();
            String address = txtAddress.getText();
            String birthDate = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();

            if (name.isEmpty() || email.isEmpty() || position.isEmpty() || phone.isEmpty() || address.isEmpty() || birthDate.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Thêm nhân viên: " + name + ", " + email + ", " + gender + ", " + position + ", " + phone + ", " + birthDate + ", " + address);
                dialog.dispose(); // Đóng form
            }
        });

        dialog.setVisible(true);
    }
}
