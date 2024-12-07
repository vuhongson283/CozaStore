/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Model.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SliderDAO {

    Connection c = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Slider> getSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * From slider where status_slider=1 order by slide_id desc ";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Slider(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void editSlider(String slide_id, String slide_title, String slide_image, String backlink, String status_slider) {
        String sql = "UPDATE slider SET "
                + "slide_title = ?, "
                + "slide_image = ?, "
                + "backlink = ?, "
                + "status_slider = ? "
                + "WHERE slide_id = ?";
        try {
            c = new DBContext().getConnection();//mo ket noi voi sql
            ps = c.prepareStatement(sql);
            ps.setString(1, slide_title);
            ps.setString(2, slide_image);
            ps.setString(3, backlink);
            ps.setString(4, status_slider);
            ps.setString(5, slide_id);
            ps.executeUpdate(); // Thực thi câu lệnh SQL cập nhật
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
        } finally {
            // Đóng kết nối và các tài nguyên liên quan ở đây
        }
    }
    public void editSliderWithoutImage(String slide_id, String slide_title, String backlink, String status_slider) {
        String sql = "UPDATE slider SET "
                + "slide_title = ?, "
                
                + "backlink = ?, "
                + "status_slider = ? "
                + "WHERE slide_id = ?";
        try {
            c = new DBContext().getConnection();//mo ket noi voi sql
            ps = c.prepareStatement(sql);
            ps.setString(1, slide_title);
            ps.setString(2, backlink);
            ps.setString(3, status_slider);
            ps.setString(4, slide_id);
            
            ps.executeUpdate(); // Thực thi câu lệnh SQL cập nhật
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
        } finally {
            // Đóng kết nối và các tài nguyên liên quan ở đây
        }
    }

    public Slider getSliderByID(String slide_id) {
        String sql = "SELECT * FROM slider WHERE slide_id = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, slide_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Slider(
                        rs.getString("slide_id"),
                        rs.getString("slide_title"),
                        rs.getString("slide_image"),
                        rs.getString("backlink"),
                        rs.getString("status_slider")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên ở đây
        }
        return null;
    }

    public List getSliderByCon(String status, String title, String backlink) {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * From slider "
                + "WHERE status_slider like ? AND slide_title like ? AND backlink like ?";
        try {
            c = new DBContext().getConnection();//mo ket noi voi sql
            ps = c.prepareStatement(sql);
            ps.setString(1,"%" + status + "%");
            ps.setString(2,"%" + title + "%");
            ps.setString(3,"%" + backlink + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(slider);
            }
        } catch (Exception e) {
        }
        return list;
    }

    

    public void addSlider(String title, String image, String backlink, String status) {
        String sql = "INSERT slider (slide_title, slide_image, backlink, status_slider) VALUES (?, ?, ?, ?)";

        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, image);
            ps.setString(3, backlink);
            ps.setString(4, status);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteSlider(String slide_id) {
        String sql = "DELETE From slider where slide_id = ?";
        try {
            c = new DBContext().getConnection();
            ps = c.prepareStatement(sql);
            ps.setString(1, slide_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
//    public static void main(String[] args) {
//    // Khởi tạo một đối tượng SliderDAO
//    SliderDAO sliderDAO = new SliderDAO();
//
//    // Gọi phương thức getSliderByID với slide_id là "1"
//    String slideIdToCheck = "1";
//    Slider slider = sliderDAO.getSliderByID(slideIdToCheck);
//
//    // Kiểm tra xem slider có null không
//    if (slider != null) {
//        // In ra thông tin của slider
//        System.out.println("Slider found:");
//        System.out.println("slide_id: " + slider.getSlide_id());
//        System.out.println("slide_title: " + slider.getSlide_title());
//        System.out.println("slide_image: " + slider.getSlide_image());
//        System.out.println("backlink: " + slider.getBacklink());
//        System.out.println("status_slider: " + slider.getSlide_status());
//    } else {
//        // Nếu không tìm thấy slider, in ra thông báo tương ứng
//        System.out.println("Slider not found for id: " + slideIdToCheck);
//    }
//}


}
