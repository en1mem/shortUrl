package ru.german.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupedStatisticPojo {
    String shortUrl;
    int minutes;
    int hours;
    int day;
    Timestamp fullDate;
}
