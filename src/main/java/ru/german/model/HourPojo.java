package ru.german.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HourPojo {
    List<MinutePojo> minuteGroup = new ArrayList<MinutePojo>(60);
    Long redirectCount = 0L;
}
