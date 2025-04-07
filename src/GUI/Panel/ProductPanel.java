package GUI.Panel;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BUS.CustomerBUS;
import GUI.Components.MenuChucNang;

public class ProductPanel extends JPanel{
    

    public ProductPanel(){
        initComponent();
    }

    private void initComponent(){
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setBackground(Color.WHITE);
        
        add(createCustomToolbar(), BorderLayout.NORTH);
        // add(createTablePanel(), BorderLayout.CENTER);
    }


         public JPanel createCustomToolbar() {
        JPanel toolbar = new JPanel(new GridLayout(1, 2, 10, 10));
        toolbar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        toolbar.setPreferredSize(new Dimension(950, 110));
        
        MenuChucNang menu = new MenuChucNang();
        toolbar.add(menu.createActionPanel(this));  
        toolbar.add(MenuChucNang.createSearchPanel()); 
        
        return toolbar;
    }


        // private JPanel createTablePanel() {
        // JPanel panel = new JPanel(new BorderLayout());
        // panel.setBackground(Color.WHITE);

        // customerBUS = new CustomerBUS();
        // customers = customerBUS.getAllCustomers();

        // // Tiêu đề cột
        // String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm","Loại sản phẩm", "Thương hiệu", "Xuất xứ"};
        // tableModel = new DefaultTableModel(columnNames, 0);
        // customerTable = new JTable(tableModel) {
        //     @Override
        //     public boolean isCellEditable(int row, int column) {
        //         return false; // Không cho phép chỉnh sửa ô trong bảng
        //     }
        // };

// }
}