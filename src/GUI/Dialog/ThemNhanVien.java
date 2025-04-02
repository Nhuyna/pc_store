package GUI.Dialog;

import BUS.EmployeeBUS;
import DTO.Employee;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class ThemNhanVien {
    private JTextField txtName, txtEmail, txtPosition, txtPhone, txtSalary;
    private JComboBox<String> cbGender;
    private JDateChooser dateChooser;
    private EmployeeBUS employeeBUS;

    public ThemNhanVien(EmployeeBUS bus) {
        this.employeeBUS = bus;
    }

    public void FormThemNv(String formname, String textButton) {
        JDialog dialog = new JDialog((Frame) null, formname, true);
        dialog.setSize(520, 550);  // Tăng kích thước dialog để chứa thêm trường Lương
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        String[] labels = {"Họ và Tên:", "Email:", "Giới tính:", "Chức vụ:", "Số điện thoại:", "Ngày sinh:", "Lương:"};
        
        // Các trường nhập
        txtName = new JTextField(18);
        txtEmail = new JTextField(18);
        cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        txtPosition = new JTextField(18);
        txtPhone = new JTextField(18);
        txtSalary = new JTextField(18);  // Thêm trường Lương
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy/MM/dd");  // Định dạng ngày

        JComponent[] components = {txtName, txtEmail, cbGender, txtPosition, txtPhone, dateChooser, txtSalary};
    
        // Thêm các trường vào dialog
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.LINE_END;
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.BOLD, 13));
            dialog.add(label, gbc);
    
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            if (components[i] instanceof JTextField) {
                ((JTextField) components[i]).setPreferredSize(new Dimension(200, 30));
                ((JTextField) components[i]).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            } else if (components[i] instanceof JComboBox) {
                components[i].setPreferredSize(new Dimension(210, 30));
            } else if (components[i] instanceof JDateChooser) {
                components[i].setPreferredSize(new Dimension(210, 30));
            }
            dialog.add(components[i], gbc);
        }
    
        JButton btnSave = new JButton("✔ Lưu");
        btnSave.setFont(new Font("Arial", Font.BOLD, 14));
        btnSave.setBackground(new Color(50, 150, 250));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setPreferredSize(new Dimension(120, 35));

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(btnSave, gbc);

        btnSave.addActionListener(e -> saveEmployee(dialog));
        dialog.setVisible(true);
    }

    private void saveEmployee(JDialog dialog) {
        System.err.println("Hi vào saveEmpoyee rồi nè");
        String name = txtName.getText();
        String email = txtEmail.getText();
        String gender = (String) cbGender.getSelectedItem();
        String position = txtPosition.getText();
        String phone = txtPhone.getText();
        String salaryStr = txtSalary.getText(); // Lấy lương từ trường nhập
    
        // Kiểm tra ngày
        Date date = dateChooser.getDate();
        System.err.println(date);
        LocalDate dateOfJoining = null;
        if (date != null) {
            // Đảm bảo chuyển đổi đúng định dạng từ Date sang LocalDate
            dateOfJoining = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }
    
        // Kiểm tra các trường nhập
        if (name.isEmpty() || email.isEmpty() || position.isEmpty() || phone.isEmpty() || salaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            double salary = 0;
            try {
                // Chuyển đổi lương từ String sang double
                salary = Double.parseDouble(salaryStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(dialog, "Lương không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;  // Dừng lại nếu lương không hợp lệ
            }
    
            // Thêm nhân viên
            boolean success = employeeBUS.addEmployee(new Employee(name, position, salary, phone, email, dateOfJoining));
    
            if (success) {
                JOptionPane.showMessageDialog(dialog, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Lỗi khi thêm nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    public void openEditEmployeeDialog(){

    }
}
