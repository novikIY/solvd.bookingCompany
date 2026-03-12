package com.solvd.bookingcompany.interfaces;

import com.solvd.bookingcompany.search.SearchCriteria;

public interface Searchable {
    boolean matches(SearchCriteria criteria);
}
