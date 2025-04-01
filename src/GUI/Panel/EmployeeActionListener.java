package GUI.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeActionListener implements ActionListener {
    private EmployeePanel panel;

    public EmployeeActionListener(EmployeePanel panel) {
        this.panel = panel;
    }

 

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Thêm":
                panel.openAddEmployeeDialog();
                break;
            case "Sửa":
                panel.deleteEmployee();
                break;
            case "Xóa":
                panel.deleteEmployee();
                break;
            case "Tìm":
                panel.searchEmployee();
                break;
            case "Xuất Excel":
                panel.ExportExcel();
                break;
        }
    }
}
