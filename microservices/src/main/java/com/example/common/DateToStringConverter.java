package com.example.common;

import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateToStringConverter implements Converter {

    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Date) {
            return CommonUtil.convertDateToString((Date) value);
        } else if (value instanceof Double) {
            return CommonUtil.formatNumber((Double) value);
        } else if (value instanceof Long) {
            return CommonUtil.formatNumber((Long) value);
        } else {
            return value.toString();
        }
    }
}
