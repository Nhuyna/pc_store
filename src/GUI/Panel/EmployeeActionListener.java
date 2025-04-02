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
            // Kiem tra xem ng dung co dang click thog tin nao kh
            
                panel.openEditEmployeeDialog();
                break;
            case "Xóa":
                System.out.println("XOá");
                break;
            case "Tìm":
                System.out.println("Tìm");
                break;
            case "Xuất Excel":
                System.out.println("Excel");
                break;
        }
    }
}
