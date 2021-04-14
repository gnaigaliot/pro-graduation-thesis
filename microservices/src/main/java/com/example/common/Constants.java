package com.example.common;

public class Constants {

    public static class SCHEMA {

        public static final String SYSTEM = "vhcm_system";
    }

    public static class RESPONSE_TYPE {

        public static final String SUCCESS = "SUCCESS";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String invalidPermission = "invalidPermission";
    }

    public static class RESPONSE_CODE {
        public static final String SUCCESS = "Thành công";
        public static final String DELETE_SUCCESS = "deleteSuccess";
        public static final String ERROR = "error";
        public static final String WARNING = "warning";
        public static final String RECORD_DELETED = "record.deleted";
        public static final String EMAIL_ADDRESS_DELETED = "emailAddress.deleted";
        public static final String RECORD_INUSED = "record.inUsed";
        public static final String RECORD_NOT_EXISTED = "recordNotExits";
        public static final String SYS_CAT_TYPE_USED = "sysCatTypeUsed";
        public static final String CODE_USED = "Mã đã tồn tại";
        public static final String APART_HAVE_PERSON = "Căn hộ đã có người ở!";
        public static final String PERSON_HAVE_APART = "Người này đã có căn hộ!";
    }

    public static class WARNING_TYPE {

        public static final Long EMAIL = 0l;
        public static final Long SMS = 1l;
        public static final Long EMAIL_SMS = 2l;
    }

    public interface API_METHOD {

        public static final String POST = "POST";
        public static final String GET = "GET";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
    }

    public interface CORS_FILTER {

        public static final String ALLOW_METHODS = "POST, PUT, GET, OPTIONS, DELETE";
        public static final String ALLOW_HEADERS = "X-CACHEABLE, Authorization, Content-Type";
    }
    
    public interface SYS_CAT_TYPE {

        String WORK_PLACE = "WORK_PLACE";
        String MANAGE_ORG = "DSCQT";
        String CALCULATE_TYPE = "CTT";
    }
    
    public interface SYS_CAT {
        String SYS_CAT = "SYS_CAT";
        String SYS_CAT_CHILD = "SYS_CAT_CHILD";
    }
}
