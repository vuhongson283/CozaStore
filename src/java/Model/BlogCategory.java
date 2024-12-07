/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Luu Bach
 */
public class BlogCategory {

    String categoryBlogId, categoryBlogName, categoryBlogImg,status;

    public BlogCategory() {
    }

    public String getCategoryBlogId() {
        return categoryBlogId;
    }

    public void setCategoryBlogId(String categoryBlogId) {
        this.categoryBlogId = categoryBlogId;
    }

    public String getCategoryBlogName() {
        return categoryBlogName;
    }

    public void setCategoryBlogName(String categoryBlogName) {
        this.categoryBlogName = categoryBlogName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryBlogImg() {
        return categoryBlogImg;
    }

    public void setCategoryBlogImg(String categoryBlogImg) {
        this.categoryBlogImg = categoryBlogImg;
    }

}
