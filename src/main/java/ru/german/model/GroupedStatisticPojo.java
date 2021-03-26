package ru.german.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupedStatisticPojo {
    String shortUrl;
    Long totalRedirectCount = 0L;
    List<DayPojo> dayGroup = new ArrayList<DayPojo>();
}
