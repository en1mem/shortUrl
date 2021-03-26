package ru.german.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HourPojo {
    List<MinutePojo> minuteGroup = new ArrayList<MinutePojo>(60);
    Long redirectCount = 0L;
}
