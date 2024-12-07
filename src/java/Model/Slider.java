/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Slider {
    String slide_id, slide_title, slide_image, backlink, slide_status;

    public Slider() {
    }

    public Slider(String slide_id, String slide_title, String slide_image, String backlink, String slide_status) {
        this.slide_id = slide_id;
        this.slide_title = slide_title;
        this.slide_image = slide_image;
        this.backlink = backlink;
        this.slide_status = slide_status;
    }

    public String getSlide_id() {
        return slide_id;
    }

    public void setSlide_id(String slide_id) {
        this.slide_id = slide_id;
    }

    public String getSlide_title() {
        return slide_title;
    }

    public void setSlide_title(String slide_title) {
        this.slide_title = slide_title;
    }

    public String getSlide_image() {
        return slide_image;
    }

    public void setSlide_image(String slide_image) {
        this.slide_image = slide_image;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public String getSlide_status() {
        return slide_status;
    }

    public void setSlide_status(String slide_status) {
        this.slide_status = slide_status;
    }
    
}
