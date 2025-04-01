package GUI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class MenuLeft extends JPanel {
    private MigLayout layout;

    // Lưu danh sách submenu cho mỗi mục menu
    private Map<Integer, List<JComponent>> menuSubmenus = new HashMap<>();

    String[][] menuItems = {
        {"Trang chủ"},
        {"Sản phẩm", "Laptop", "PC", "Linh kiện máy tính"},
        {"Thuộc tính", "Màu sắc", "Thương hiệu", "Xuất xứ"},
        {"Phiếu nhập"},
        {"Phiếu xuất"},
        {"Khách hàng"},
        {"Nhà cung cấp"},
        {"Nhân viên", "Quản lí", "Bán hàng"},
        {"Tài khoản"},
        {"Thống kê"},
        {"Phân quyền"},
        {"Đăng xuất"},
    };

    public MenuLeft() {
        initComponents();
    }

    private void initComponents() {
        layout = new MigLayout("wrap 1, fillx, gapy 0px, inset 0px");

        setLayout(layout);

        for (int i = 0; i < menuItems.length; i++) {
            addMenu(menuItems[i][0], i);
        }
    }

    private void addMenu(String menuName, int index) {
        int length = menuItems[index].length;
    
        // Toggle action để ẩn/hiện submenu
        Runnable toggleAction = () -> toggleSubMenu(index);
    
        MenuItem item = new MenuItem(menuName, index, length > 1, toggleAction);
        add(item, "growx");
    
        List<JComponent> subMenuList = new ArrayList<>();
    
        if (length > 1) {
            // Duyệt qua các submenu
            for (int j = 1; j < length; j++) {
                MenuItem subItem = new MenuItem("  └ " + menuItems[index][j], index, false, null);
                subItem.setVisible(false); // Ban đầu ẩn đi
                subMenuList.add(subItem);
                add(subItem, "growx, hidemode 3"); // Sử dụng hidemode 3 để ẩn hoàn toàn
            }
            
        } else {
            // Nếu không có submenu, không thêm khoảng cách thụt lề
            add(item, "growx");
        }
    
        // Lưu submenu vào Map theo index
        menuSubmenus.put(index, subMenuList);
    
        revalidate();
        repaint();
    }
    
    private void toggleSubMenu(int index) {
        // Lấy danh sách submenu của mục menu hiện tại
        List<JComponent> subMenuList = menuSubmenus.get(index);

        // Đảo trạng thái của các submenu của mục này
        for (JComponent subMenu : subMenuList) {
            subMenu.setVisible(!subMenu.isVisible()); // Đảo trạng thái submenu
        }

        revalidate();
        repaint();
    }
}  