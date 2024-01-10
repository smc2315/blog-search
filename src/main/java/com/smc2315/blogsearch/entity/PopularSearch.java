package com.smc2315.blogsearch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PopularSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private int count;

    public static PopularSearch create(String keyword) {
        PopularSearch popularSearch = new PopularSearch();
        popularSearch.keyword = keyword;
        popularSearch.count = 1;
        return popularSearch;
    }

    public void increaseCount() {
        count++;
    }
}
