package com.example.common;

import org.apache.commons.beanutils.Converter;

public class StringToDateConverter implements Converter {

    @Override
    public Object convert(Class type, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            try {
                return CommonUtil.convertStringToDate(value.toString());
            } catch (Exception ex) {
                return null;
            }
        } else {
            return value;
        }
    }
}
