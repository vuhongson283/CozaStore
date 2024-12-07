/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.util.List;

/**
 *
 * @author DELL
 */
public class PaginationHelper<T> {
    private int currentPage;
    private int pageSize;
    private List<T> itemList;

    public PaginationHelper(List<T> itemList, int pageSize) {
        this.itemList = itemList;
        this.pageSize = pageSize;
        this.currentPage = 1;
    }

    public List<T> getCurrentPageItems() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, itemList.size());
        return itemList.subList(startIndex, endIndex);
    }

    public boolean hasNextPage() {
        return currentPage < getTotalPages();
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) itemList.size() / pageSize);
    }

    public void nextPage() {
        if (hasNextPage()) {
            currentPage++;
        }
    }

    public void previousPage() {
        if (hasPreviousPage()) {
            currentPage--;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }
}
