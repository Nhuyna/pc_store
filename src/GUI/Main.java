package GUI;

import java.awt.*;
import javax.swing.*;

import GUI.Components.MenuLeft;
import GUI.Panel.EmployeePanel;

import GUI.Panel.Trangchu;

public class Main extends JFrame {

    public JPanel MainContent;
    Color MainColor = new Color(250, 250, 250);

    private MenuLeft menuTaskbar;
    private Trangchu trangChu;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        this.setSize(new Dimension(1400, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Hệ thống quản lý cửa hàng máy tính ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        menuTaskbar = new MenuLeft();
    
        // Thêm thanh cuộn cho Menu
        JScrollPane scrollPane = new JScrollPane(menuTaskbar);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(250, 800));
    
        // Thêm Menu vào JFrame
        this.add(scrollPane, BorderLayout.WEST);
    
        // Khởi tạo MainContent để chứa nội dung chính
        MainContent = new JPanel(new BorderLayout());
        MainContent.setBackground(MainColor);
        this.add(MainContent, BorderLayout.CENTER);
    
        setMainPanel(new EmployeePanel());
    
        this.setVisible(true);
    }

    public void setMainPanel(JPanel panel) {
        MainContent.removeAll(); // Xóa nội dung cũ
        MainContent.add(panel, BorderLayout.CENTER); // Thêm nội dung mới
        MainContent.revalidate();
        MainContent.repaint();
    }
    

    public static void main(String[] args) {
        new Main();
    }
}
