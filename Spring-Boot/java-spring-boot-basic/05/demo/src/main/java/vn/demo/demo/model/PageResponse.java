package vn.demo.demo.model;

import java.util.List;

public class PageResponse<T> {
    private List<T> resources;
    private int pageSize;
    private int currentPage;

    public PageResponse() {
    }

    public PageResponse(List<T> resources, int pageSize, int currentPage) {
        this.resources = resources;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) resources.size() / pageSize);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getData() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, resources.size());
        return resources.subList(startIndex, endIndex);
    }
}
