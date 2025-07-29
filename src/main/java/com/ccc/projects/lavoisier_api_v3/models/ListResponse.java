package com.ccc.projects.lavoisier_api_v3.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListResponse<T> {
    private List<T> data;
    private Long totalCount;
}
