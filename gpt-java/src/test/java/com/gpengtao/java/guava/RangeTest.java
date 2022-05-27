package com.gpengtao.java.guava;


import com.google.common.collect.ContiguousSet;
import com.google.common.collect.Range;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pengtao.geng on 2017/11/25.
 */
public class RangeTest {

    @Test
    public void test_range() throws ParseException {
        Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-07-08 07:00:00");
        Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-07-08 19:00:00");

        Range<Date> range = Range.closed(start, end);

        ContiguousSet<Date> dates = ContiguousSet.create(range, null);

    }
}
