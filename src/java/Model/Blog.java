/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luu Bach
 */
public class Blog {

    private String blogId;          // Represents a unique identifier for a blog
    private String title;           // Stores the title of the blog
    private String authorName;        // Indicates the author's unique identifier
    private String updatedDate;     // Stores the date when the blog was last updated
    private String content;         // Contains the actual content of the blog
    private String thumbnail;       // Represents a thumbnail image associated with the blog
    private String briefInfo;       // Provides a brief summary or description of the blog
    private String categoryBlogId;  // Indicates the category or topic of the blog
    private String status;

    
    
    public String getShortenedUpdatedDate() {
        if (updatedDate == null || updatedDate.length() <= 8) {
            return "";
        }
        return updatedDate.substring(8);
    }

    public String getMonthYear() {
        if (updatedDate == null || updatedDate.length() < 7) {
            return "";
        }
        return updatedDate.substring(0, 7);
    }

    public List<String> splitContentAroundMiddleDot() {
        List<String> halves = new ArrayList<>();

        if (content == null || content.isEmpty()) {
            halves.add("");
            halves.add("");
            return halves;
        }

        int length = content.length();
        int middle = length / 2;

        int leftDot = content.lastIndexOf('.', middle);
        int rightDot = content.indexOf('.', middle);

        int splitIndex;
        if (leftDot != -1 && (rightDot == -1 || middle - leftDot < rightDot - middle)) {
            splitIndex = leftDot;
        } else if (rightDot != -1 && (leftDot == -1 || rightDot - middle < middle - leftDot)) {
            splitIndex = rightDot;
        } else {
            splitIndex = middle;
        }

        halves.add(content.substring(0, splitIndex));
        halves.add(content.substring(splitIndex));

        return halves;
    }

    public List<String> splitContentAroundNearestDotAfterAdventures() {
        List<String> halves = new ArrayList<>();

        if (content == null || content.isEmpty()) {
            halves.add("");
            halves.add("");
            return halves;
        }

        int adventuresIndex = content.indexOf("adventures");
        if (adventuresIndex == -1) {
            // If "adventures" not found, split content around the middle dot
            return splitContentAroundMiddleDot();
        }

        // Find the nearest dot after "adventures"
        int dotIndex = content.indexOf('.', adventuresIndex);
        if (dotIndex == -1) {
            // If dot not found after "adventures", split content at the end of "adventures"
            dotIndex = adventuresIndex + "adventures".length();
        }

        // Split content around the nearest dot after "adventures"
        halves.add(content.substring(0, dotIndex + 1));
        halves.add(content.substring(dotIndex + 1));

        return halves;
    }

    public Blog(String blogId, String title, String authorName, String updatedDate, String content, String thumbnail, String briefInfo, String categoryBlogId, String status) {
        this.blogId = blogId;
        this.title = title;
        this.authorName = authorName;
        this.updatedDate = updatedDate;
        this.content = content;
        this.thumbnail = thumbnail;
        this.briefInfo = briefInfo;
        this.categoryBlogId = categoryBlogId;
        this.status = status;
    }

    public Blog() {
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getCategoryBlogId() {
        return categoryBlogId;
    }

    public void setCategoryBlogId(String categoryBlogId) {
        this.categoryBlogId = categoryBlogId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
