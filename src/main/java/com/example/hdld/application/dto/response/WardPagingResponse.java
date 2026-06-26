package com.example.hdld.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response DTO for GET /hdld/xa-phuong/paging.
 * Per the official contract this endpoint returns a paging envelope at the root:
 * {@code { "total_count": N, "data": [ ... ] }}.
 */
public class WardPagingResponse {

    @JsonProperty("total_count")
    private long totalCount;

    @JsonProperty("data")
    private List<WardResponse> data;

    public WardPagingResponse() {
    }

    public WardPagingResponse(long totalCount, List<WardResponse> data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public long getTotalCount() { return totalCount; }
    public void setTotalCount(long totalCount) { this.totalCount = totalCount; }

    public List<WardResponse> getData() { return data; }
    public void setData(List<WardResponse> data) { this.data = data; }
}
