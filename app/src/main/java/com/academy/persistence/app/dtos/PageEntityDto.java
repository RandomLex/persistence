package com.academy.persistence.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageEntityDto<T> {

    private long totalPages;
    private long totalElements;
    private long pageNumber;
    private long elementsOnPage;
    private List<T> elements;
}
