package ru.german.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HourPojo {
    Map<Integer, MinutePojo> minuteGroup = new HashMap<>();
    Long redirectCount = 0L;
}
