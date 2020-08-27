package com.zhenmei.mvpmamba.mvp;


public abstract class BasePagingPresenter {
    public Long getId() {
        return id;
    }

    protected Long id = 0l;
    protected int page = 1;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getSortType() {
        return sortType;
    }

    protected int rows = 10;


    public int getTotal() {
        return total;
    }

    protected int total = 0;

    public boolean isNoMoreData() {
        return isNoMoreData;
    }

    public void setNoMoreData(boolean noMoreData) {
        isNoMoreData = noMoreData;
    }

    protected boolean isNoMoreData = false;


    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public void nextPage() {
        this.page = this.page + 1;
    }

    public void firstPage() {
        this.isNoMoreData = false;
        this.page = 1;
    }


    protected Integer sortType = 1;


    public boolean isNextESPage() {
        return isNextESPage;
    }

    public void setNextESPage(boolean nextESPage) {
        isNextESPage = nextESPage;
    }

    private boolean isNextESPage = false;

    public void nextESPage() {
        isNextESPage = true;
    }


    protected String nextPageValue;


    public void setId(Long id) {
        this.id = id;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
