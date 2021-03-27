package ru.german.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import generated.tables.pojos.Redirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.german.exception.RedirectException;
import ru.german.model.*;
import ru.german.repository.UrlRepository;

import java.sql.Timestamp;
import java.util.*;

@Service
public class StatisticService {

    @Autowired
    UrlRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(StatisticService.class);

    public ResponseEntity<UrlPojo> getInfo(String shortUrl) {
        return repository.getUrlByBase62Key(shortUrl);
    }

    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        return repository.getAllExistedShortUrls();
    }

    public ResponseEntity<List<TopUrlResponse>> getTop20FullUrls() {
        return repository.getTopUrls(20);
    }

    public ResponseEntity<String> getGroupedStatisticByDetailedTime(String shortUrl) {
        Long urlId = repository.getIdByShortUrl(shortUrl);
        if (urlId != null) {
            //делаем json в удобно читаемом формате
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            GroupedStatisticPojo groupedStatisticPojo = new GroupedStatisticPojo();
            List<Redirect> redirects = repository.getRedirectListByUrlId(urlId);

            logger.info("Found {} redirects by url", redirects.size());

            Map<Integer, DayPojo> dayPojoMap = new HashMap<>();
            redirects.forEach(redirect -> {
                Timestamp insertedTime = redirect.getCreatedDateTime();

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTimeInMillis(insertedTime.getTime());

                int day = cal.get(Calendar.DAY_OF_MONTH);
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);

                //region day
                DayPojo dayPojo = new DayPojo();
                dayPojo.setDayOfMonth(day);

                //region minute
                MinutePojo minutePojo = new MinutePojo();
                //endregion

                //region hour
                HourPojo hourPojo = new HourPojo();
                //endregion

                dayPojoMap.putIfAbsent(day, dayPojo);
                dayPojoMap.get(day).setRedirectCount(dayPojoMap.get(day).getRedirectCount() + 1);

                dayPojoMap.get(day).getHourGroup().putIfAbsent(hour, hourPojo);
                dayPojoMap.get(day).getHourGroup().get(hour).getMinuteGroup().putIfAbsent(minute, minutePojo);

                MinutePojo minuteInMap = dayPojoMap.get(day).getHourGroup().get(hour).getMinuteGroup().get(minute);
                HourPojo hourInMap = dayPojoMap.get(day).getHourGroup().get(hour);

                minuteInMap.setRedirectCount(minuteInMap.getRedirectCount() + 1);
                hourInMap.setRedirectCount(hourInMap.getRedirectCount() + 1);
                //endregion
            });

            long finalRedirectCount = dayPojoMap.values()
                    .stream()
                    .map(DayPojo::getRedirectCount)
                    .mapToLong(Long::longValue)
                    .sum();

            groupedStatisticPojo.setShortUrl(shortUrl);
            groupedStatisticPojo.getDayGroup().addAll(dayPojoMap.values());
            groupedStatisticPojo.setTotalRedirectCount(finalRedirectCount);

            try {
                String result = objectMapper.writeValueAsString(groupedStatisticPojo);
                return ResponseEntity.ok(result);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            throw new RedirectException(shortUrl);
        }
    }

}
