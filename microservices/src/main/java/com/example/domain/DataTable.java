package com.example.domain;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class DataTable<T> {

    @JsonView(View.class)
    private int draw;
    @JsonView(View.class)
    private int first;
    @JsonView(View.class)
    private long recordsTotal = 0L;
    @JsonView(View.class)
    private long recordsFiltered = 0L;
    @JsonView(View.class)
    private List<T> data = Collections.emptyList();
    @JsonView(View.class)
    private String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public interface View {

    }

    /**
     * @return the first
     */
    public int getFirst() {
        return first;
    }

    /**
     * @param first
     *            the first to set
     */
    public void setFirst(int first) {
        this.first = first;
    }
}