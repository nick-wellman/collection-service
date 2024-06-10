package com.nickwellman.collections.bookbujo.models;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private String kind;
    private List<Item> items;
}
