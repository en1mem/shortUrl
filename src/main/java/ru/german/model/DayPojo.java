package ru.german.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DayPojo {
    List<HourPojo> hourGroup = new ArrayList<HourPojo>(24);
    Long redirectCount = 0L;
}
