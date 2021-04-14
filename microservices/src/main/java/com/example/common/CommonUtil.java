
package com.example.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.example.user.entity.UserBean;
import com.example.user.service.JwtService;
import com.example.user.service.UserService;
import com.google.gson.Gson;

import jxl.Workbook;
import jxl.WorkbookSettings;



public class CommonUtil {


    @Autowired
    private static JwtService jwtService;
    
    @Autowired
    private static UserService userService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
    private static final String[] SIGNED_ARR = new String[] {
            "à", "á", "ạ", "ả", "ã",
            "â", "ầ", "ấ", "ậ", "ẩ", "ẫ",
            "ă", "ằ", "ắ", "ặ", "ẳ", "ẵ",
            "è", "é", "ẹ", "ẻ", "ẽ",
            "ê", "ề", "ế", "ệ", "ể", "ễ",
            "ì", "í", "ị", "ỉ", "ĩ",
            "ò", "ó", "ọ", "ỏ", "õ",
            "ô", "ồ", "ố", "ộ", "ổ", "ỗ",
            "ơ", "ờ", "ớ", "ợ", "ở", "ỡ",
            "ù", "ú", "ụ", "ủ", "ũ",
            "ư", "ừ", "ứ", "ự", "ử", "ữ",
            "ỳ", "ý", "ỵ", "ỷ", "ỹ",
            "đ",
            "À", "Á", "Ạ", "Ả", "Ã",
            "Â", "Ầ", "Ấ", "Ậ", "Ẩ", "Ẫ",
            "Ă", "Ằ", "Ắ", "Ặ", "Ẳ", "Ẵ",
            "È", "É", "Ẹ", "Ẻ", "Ẽ",
            "Ê", "Ề", "Ế", "Ệ", "Ể", "Ễ",
            "Ì", "Í", "Ị", "Ỉ", "Ĩ",
            "Ò", "Ó", "Ọ", "Ỏ", "Õ",
            "Ô", "Ồ", "Ố", "Ộ", "Ổ", "Ỗ",
            "Ơ", "Ờ", "Ớ", "Ợ", "Ở", "Ỡ",
            "Ù", "Ú", "Ụ", "Ủ", "Ũ",
            "Ư", "Ừ", "Ứ", "Ự", "Ử", "Ữ",
            "Ỳ", "Ý", "Ỵ", "Ỷ", "Ỹ",
            "Đ"
    };
    private static final String[] UNSIGNED_ARR = new String[] {
            "a", "a", "a", "a", "a",
            "a", "a", "a", "a", "a", "a",
            "a", "a", "a", "a", "a", "a",
            "e", "e", "e", "e", "e",
            "e", "e", "e", "e", "e", "e",
            "i", "i", "i", "i", "i",
            "o", "o", "o", "o", "o",
            "o", "o", "o", "o", "o", "o",
            "o", "o", "o", "o", "o", "o",
            "u", "u", "u", "u", "u",
            "u", "u", "u", "u", "u", "u",
            "y", "y", "y", "y", "y",
            "d",
            "A", "A", "A", "A", "A",
            "A", "A", "A", "A", "A", "A",
            "A", "A", "A", "A", "A", "A",
            "E", "E", "E", "E", "E",
            "E", "E", "E", "E", "E", "E",
            "I", "I", "I", "I", "I",
            "O", "O", "O", "O", "O",
            "O", "O", "O", "O", "O", "O",
            "O", "O", "O", "O", "O", "O",
            "U", "U", "U", "U", "U",
            "U", "U", "U", "U", "U", "U",
            "Y", "Y", "Y", "Y", "Y",
            "D"
    };

    /**
     * toList
     * 
     * @param <T>
     * @param json
     * @param typeClass
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> typeClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, new ListOfJson<T>(typeClass));
    }
	/**
	 * Cast BigDemical to Long
	 *
	 * @param value
	 * @return
	 */
	public static Long demicalToLong(BigDecimal value) {
		if (value == null) {
			return 0L;
		} else {
			return value.longValue();
		}
	}

	/**
	 * Check string is null.
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}

	/**
	 * Check list object is null.
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(List data) {
		return (data == null || data.isEmpty());
	}

	/**
	 * Chuyen doi tuong String thanh doi tuong Date.
	 *
	 * @param date
	 *            Xau ngay, co dinh dang duoc quy trinh trong file Constants
	 * @return Doi tuong Date
	 * @throws Exception
	 *             Exception
	 */
    public static Date convertStringToDate(String date) throws Exception {
        if (date == null || date.trim().isEmpty()) {
        	return null;
        } else {
        	String pattern = "dd/MM/yyyy";
        	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        	dateFormat.setLenient(false);
        	return dateFormat.parse(date);
        }
    }

	/**
	 * Chuyen doi tuong Date thanh doi tuong String.
	 *
	 * @param date
	 *            Doi tuong Date
	 * @return Xau ngay, co dang dd/MM/yyyy
	 */
	public static String convertDateToString(Date date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(date);
		}
	}

	/**
	 * Chuyen doi tuong Date thanh doi tuong String.
	 *
	 * @param date
	 *            Doi tuong Date
	 * @return Xau ngay, co dang dd/MM/yyyy
	 */
	public static String convertDateToString(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		}
	}

	/**
	 * Conver tu string sang date theo dinh dang mong muon
	 *
	 * @param date
	 * @param pattern
	 *            : kieu dinh dang vd: "dd/MM/yyyy hh:MM"
	 * @return
	 * @throws Exception
	 */
	public static Date convertStringToDateTime(String date, String pattern) throws Exception {
		if (date == null || date.trim().isEmpty()) {
			return null;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormat.setLenient(false);
			try {
				return dateFormat.parse(date);
			} catch (Exception ex) {
				LOGGER.error("convertStringToDateTime", ex);
				return null;
			}
		}
	}

    /**
     * Lay gia tri tu file config.properties.
     *
     * @param key
     *            Khoa
     * @return Gia tri
     */
    public static String getConfig(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("config");
        return rb.getString(key);
    }
    /**
     * Lay xau gia tri tu file ApplicationResources.properties.
     *
     * @param key Khoa
     * @return Gia tri
     */
    public static String getApplicationResource(String key) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("ApplicationResources", Locale.getDefault());
            return rb.getString(key);
        } catch (Exception e) {
            // TODO: handle exception
            return "";
        }
        
    }

	public static Object NVL(Object value, Object defaultValue) {
		return value == null ? defaultValue : value;
	}

	public static Double NVL(Double value) {

		return NVL(value, new Double(0));
	}

	public static Integer NVL(Integer value) {
		return value == null ? new Integer(0) : value;
	}
	public static Integer NVL(Integer value, Integer defaultValue) {
	    return value == null ? defaultValue : value;
	}

	public static BigDecimal NVL(BigDecimal value) {
		return value == null ? new BigDecimal(0) : value;
	}

	public static Double NVL(Double value, Double defaultValue) {
		return value == null ? defaultValue : value;
	}

	public static Long NVL(Long value, Long defaultValue) {
		return value == null ? defaultValue : value;
	}

	public static String NVL(String value, String nullValue, String notNullValue) {

		return value == null ? nullValue : notNullValue;
	}

	public static String NVL(String value, String defaultValue) {

		return NVL(value, defaultValue, value);
	}

	public static String NVL(String value) {

		return NVL(value, "");
	}

	public static Long NVL(Long value) {

		return NVL(value, 0L);
	}

    public static Long checkBoxValue(Long value) {
        if (value != null && value.equals(1L)) {
            return 1L;
        } else {
            return 0L;
        }
    }

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-FORWARDED-FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * Convert String to list
	 *
	 * @param sourceString
	 * @param pattern
	 * @return
	 */
	public static List<String> toList(String sourceString, String pattern) {
		List<String> results = new LinkedList<String>();
		String[] sources = NVL(sourceString).split(pattern);
		for (String source : sources) {
			results.add(source);
		}
		return results;
	}

	/**
	 * Convert String to list
	 *
	 * @param sourceString
	 * @param pattern
	 * @return
	 */
	public static List<Long> toLongList(String sourceString, String pattern) {
		List<Long> results = new LinkedList<Long>();
		String[] sources = NVL(sourceString).split(pattern);
		for (String source : sources) {
		    if (!CommonUtil.isNullOrEmpty(source)) {
		        results.add(Long.valueOf(source));
		    }
		}
		return results;
	}

	/**
	 *
	 * @param endDate
	 * @param startDate
	 * @return
	 */
	public static double monthsBetween(Date endDate, Date startDate) {
		Calendar endCalendar = Calendar.getInstance();
		Calendar startCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		startCalendar.setTime(startDate);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		return diffMonth;
	}

	/**
	 *
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 *
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * Format Double theo he thong kieu Phap
	 *
	 * @param itemValue
	 * @return
	 */
	public static String formatFrenchNumber(Double itemValue) {
		if (itemValue != null) {
			try {
				DecimalFormat df = new DecimalFormat();
				DecimalFormatSymbols symbols = new DecimalFormatSymbols();
				symbols.setDecimalSeparator(',');
				symbols.setGroupingSeparator('.');
				df.setDecimalFormatSymbols(symbols);

				return df.format(itemValue);
			} catch (Exception ex) {
				LOGGER.error("formatFrenchNumber", ex);
				return String.valueOf(itemValue);
			}
		} else {
			return "";
		}
	}

	/**
	 * Trunc date.
	 *
	 * @param inputDate
	 * @return
	 */
	public static Date TRUNC(Date inputDate) {
		if (inputDate == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.HOUR, 0);
			return cal.getTime();
		}
	}

	/**
     * kiem tra 1 xau rong hay null khong
     *
     * @param str : xau
     * @param queryString
     * @param paramList
     * @param field
     */
    public static void filter(String str, StringBuilder queryString, List<Object> paramList, String field) {
        if ((str != null) && !"".equals(str.trim())) {
            queryString.append(" AND LOWER(").append(field).append(") LIKE ? ESCAPE '/'");
            str = str.replace("  ", " ");
            str = "%" + str.trim().toLowerCase().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%";
            paramList.add(str);
        }
    }

    /**
     * kiem tra 1 so rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param paramList
     * @param field
     */
    public static void filter(Long n, StringBuilder queryString, List<Object> paramList, String field) {
        if ((n != null) && (n > 0L)) {
            queryString.append(" AND ").append(field).append(" = ? ");
            paramList.add(n);
        }
    }
    
    /**
     * kiem tra 1 so rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param paramList
     * @param field
     */
    public static void filter(Double n, StringBuilder queryString, List<Object> paramList, String field) {
        if ((n != null) && (n > 0L)) {
            queryString.append(" AND ").append(field).append(" = ? ");
            paramList.add(n);
        }
    }

    /**
     * kiem tra 1 so rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param paramList
     * @param field
     */
    public static void filter(Boolean n, StringBuilder queryString, List<Object> paramList, String field) {
        if (n != null) {
            queryString.append(" AND ").append(field).append(" = ? ");
            paramList.add(n);
        }
    }

    /**
     * kiem tra 1 Integer rong hay null khong
     *
     * @param n So
     * @param queryString
     * @param paramList
     * @param field
     */
    public static void filter(Integer n, StringBuilder queryString, List<Object> paramList, String field) {
        if ((n != null) && (n > 0)) {
            queryString.append(" AND ").append(field).append(" = ? ");
            paramList.add(n);
        }
    }
    
    /**
     * kiem tra 1 xau rong hay null khong
     *
     * @param date So
     * @param queryString
     * @param field
     * @param paramList
     */
    public static void filter(Date date, StringBuilder queryString, List<Object> paramList, String field) {
        if ((date != null)) {
            queryString.append(" AND ").append(field).append(" = ? ");
            paramList.add(date);
        }
    }

	/**
	 * filter for inserting preparedStatement
	 *
	 * @param value
	 * @param index
	 * @param preparedStatement
	 * @throws Exception
	 */
	public static void filter(String value, PreparedStatement preparedStatement, int index) throws Exception {

		if (value != null) {
			preparedStatement.setString(index, value.trim());
		} else {
			preparedStatement.setNull(index, java.sql.Types.NULL);
		}
	}

	/**
	 *
	 * @param value
	 * @param preparedStatement
	 * @param index
	 * @throws Exception
	 */
	public static void filter(Double value, PreparedStatement preparedStatement, int index) throws Exception {

		if (value != null) {
			preparedStatement.setDouble(index, value);
		} else {
			preparedStatement.setNull(index, java.sql.Types.NULL);
		}
	}

	/**
	 *
	 * @param value
	 * @param preparedStatement
	 * @param index
	 * @throws Exception
	 */
	public static void filter(Long value, PreparedStatement preparedStatement, int index) throws Exception {

		if (value != null) {
			preparedStatement.setLong(index, value);
		} else {
			preparedStatement.setNull(index, java.sql.Types.NULL);
		}
	}

	/**
	 *
	 * @param value
	 * @param preparedStatement
	 * @param index
	 * @throws Exception
	 */
	public static void filter(Object value, PreparedStatement preparedStatement, int index) throws Exception {
		if (value != null) {
			if (value instanceof Date) {
				Date temp = (Date) value;
				preparedStatement.setObject(index, new java.sql.Timestamp(temp.getTime()));
			} else {
				preparedStatement.setObject(index, value);
			}

		} else {
			preparedStatement.setNull(index, java.sql.Types.NULL);
		}
	}

	/**
	 *
	 * @param value
	 * @param preparedStatement
	 * @param index
	 * @throws Exception
	 */
	public static void filter(java.sql.Date value, PreparedStatement preparedStatement, int index) throws Exception {

		if (value != null) {
			preparedStatement.setDate(index, value);
		} else {
			preparedStatement.setNull(index, java.sql.Types.NULL);
		}
	}

	/**
	 * kiem tra mot chuoi co chua ky tu Unicode khong
	 *
	 * @param str
	 * @return
	 */
	public static boolean containUnicode(String str) {
		String signChars = "ăâđêôơưàảãạáằẳẵặắầẩẫậấèẻẽẹéềểễệếìỉĩịíòỏõọóồổỗộốờởỡợớùủũụúừửữựứỳỷỹỵý";
		if ("".equals(str)) {
			return false;
		} else {
			int count = 0;
			String subStr;
			while (count < str.length()) {
				subStr = str.substring(count, count + 1);
				if (signChars.contains(subStr)) {
					return true;
				}
				count++;
			}
		}
		return false;
	}

	/**
	 * kiem tra mot chuoi co chua ky tu Unicode khong
	 *
	 * @param str
	 * @return
	 */
	public static boolean containPhoneNumber(String str) {
		String signChars = "0123456789";
		if ("".equals(str)) {
			return false;
		} else {
			int count = 0;
			String subStr;
			while (count < str.length()) {
				subStr = str.substring(count, count + 1);
				if (!signChars.contains(subStr)) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * replaceSpecialKeys
	 *
	 * @param str
	 *            String
	 * @return String
	 */
	public static String replaceSpecialKeys(String str) {
		str = str.replace("  ", " ");
		str = "%" + str.trim().toLowerCase().replace("/", "//").replace("_", "/_").replace("%", "/%") + "%";
		return str;
	}

	/**
	 * copy properties tu form sang BO va nguoc lai
	 *
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void copyProperties(Object dest, Object orig)
			throws Exception {
		BeanUtilsBean beanUtilsBean = getUtilsBean();
		beanUtilsBean.copyProperties(dest, orig);
	}

	/**
	 *
	 * @return
	 */
	public static BeanUtilsBean getUtilsBean() {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		convertUtilsBean.register(new LongConverter(), Long.class);
		convertUtilsBean.register(new DoubleConverter(), Double.class);
		convertUtilsBean.register(new StringToDateConverter(), Date.class);
		convertUtilsBean.register(new DateToStringConverter(), String.class);
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
		return beanUtilsBean;
	}

	/**
	 * Format so.
	 *
	 * @param d
	 *            So
	 * @return Xau
	 */
	public static String formatNumber(Double d) {
		if (d == null) {
			return "";
		} else {
			DecimalFormat format = new DecimalFormat("######.#####");
			return format.format(d);
		}
	}

	/**
	 * Format so.
	 *
	 * @param d
	 *            So
	 * @return Xau
	 */
	public static String formatNumber(Long d) {
		if (d == null) {
			return "";
		} else {
			DecimalFormat format = new DecimalFormat("######");
			return format.format(d);
		}
	}

	/**
	 *
	 * @param <T>
	 * @param list
	 * @param size
	 * @return
	 */
	public static <T> List<List<T>> partition(List<T> list, int size) {

		if (list == null) {
			throw new NullPointerException("'list' must not be null");
		}
		if (!(size > 0)) {
			throw new IllegalArgumentException("'size' must be greater than 0");
		}

		return new Partition<T>(list, size);
	}

	/**
	 * Chuyen string -> List Long
	 *
	 * @param inpuString
	 * @param separator
	 * @return
	 */
	public static List<Long> string2ListLong(String inpuString, String separator) {
		List<Long> outPutList = new ArrayList<Long>();

		if (inpuString != null && !"".equals(inpuString.trim()) && separator != null && !"".equals(separator.trim())) {
			String[] idArr = inpuString.split(separator);
			for (String idArr1 : idArr) {
				if (idArr1 != null && !"".equals(idArr1.trim())) {
					outPutList.add(Long.parseLong(idArr1.trim()));
				}
			}
		}

		return outPutList;
	}

	/**
	 * chuyen list string ve chuỗi phuc vu tim kiem
	 *
	 * @param lstObject
	 *            lst
	 * @param separator
	 * @return ket qua
	 * @throws Exception
	 *             ex
	 */
	public static String convertListToString(List lstObject, String separator) throws Exception {
		try {
			if (lstObject != null && !lstObject.isEmpty()) {
				StringBuilder result = new StringBuilder("");
				int size = lstObject.size();
				result.append("'").append(lstObject.get(0)).append("'");
				for (int i = 1; i < size; i++) {
					result.append(separator);
					result.append("'");
					result.append(lstObject.get(i));
					result.append("'");
				}
				return result.toString();
			} else {
				return "";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static Date subDayFromDate(Date date, int day) {
		if (date != null) {
			Calendar cld = Calendar.getInstance();
			cld.setTime(date);
			cld.add(Calendar.DATE, -day);
			return cld.getTime();
		}
		return null;
	}

	/**
	 * Convert Object To String Json
	 *
	 * @param object
	 * @return
	 */
	public static String convertObjectToStringJson(Object object) {
		String strMess = "";
		try {
			Gson gson = new Gson();
			strMess = gson.toJson(object);
		} catch (Exception e) {
			LOGGER.error("Error! Convert object to json", e);
		}
		return strMess;
	}

	/**
	 * Get long parameter.
	 *
	 * @param req
	 * @param name
	 * @return
	 */
	public static Long getParameterLong(HttpServletRequest req, String name) {
		try {
			return Long.parseLong(req.getParameter(name));
		} catch (Exception ex) {
			LOGGER.error("Get parameter long from: " + name + " ERROR: ", ex);
			return null;
		}
	}

	/**
	 * Get long parameter.
	 *
	 * @param req
	 * @param name
	 * @return
	 */
	public static Integer getParameterInt(HttpServletRequest req, String name) {
		try {
			return Integer.parseInt(req.getParameter(name));
		} catch (Exception ex) {
			LOGGER.error("Get parameter long from: " + name + " ERROR: ", ex);
			return null;
		}
	}

	/**
	 * Checks if is collection empty.
	 *
	 * @param collection
	 *            the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is object empty.
	 *
	 * @param object
	 *            the object
	 * @return true, if is object empty
	 */
	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		} else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}
		} else if (object instanceof Collection) {
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}

    /**
     * Builds the paginated query.
     *
     * @param baseQuery
     *            the base query
     * @param paginationCriteria
     *            the pagination criteria
     * @return the string
     */
    public static String buildPaginatedQuery(String baseQuery, String orderBy, SearchParams searchParams) {
        if (!isEmpty(searchParams)) {
            if (!"".equals(CommonUtil.NVL(searchParams.getOrderByClause()))) {
                orderBy = searchParams.getOrderByClause();
            }
        }
        StringBuilder sb = new StringBuilder("#BASE_QUERY# #ORDER_CLASUE# ");
        String finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery)
                .replaceAll("#ORDER_CLASUE#", CommonUtil.NVL(orderBy));
        return finalQuery;
    }

    /**
     * Builds the paginated query.
     *
     * @param baseQuery
     *            the base query
     * @param paginationCriteria
     *            the pagination criteria
     * @return the string
     */
    public static String buildCountQuery(String baseQuery) {
        StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM (#BASE_QUERY#) FILTERED_ORDERD_RESULTS ");
        String finalQuery = null;
        finalQuery = sb.toString().replaceAll("#BASE_QUERY#", baseQuery);
        return (null == finalQuery) ? baseQuery : finalQuery;
    }
    /**
     * <p>Escapes the characters in a <code>String</code> to be suitable to pass to
     * an SQL query.</p>
     *
     * <p>For example,
     * <pre>statement.executeQuery("SELECT * FROM MOVIES WHERE TITLE='" + 
     *   StringEscapeUtils.escapeSql("McHale's Navy") + 
     *   "'");</pre>
     * </p>
     *
     * <p>At present, this method only turns single-quotes into doubled single-quotes
     * (<code>"McHale's Navy"</code> => <code>"McHale''s Navy"</code>). It does not
     * handle the cases of percent (%) or underscore (_) for use in LIKE clauses.</p>
     *
     * see http://www.jguru.com/faq/view.jsp?EID=8881
     * @param str  the string to escape, may be null
     * @return a new String, escaped for SQL, <code>null</code> if null string input
     */
    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("'", "''");
    }
    /**
     * Loai bo cac dau, ten file chi chua cac ky tu ASCII.
     *
     * @param originalName
     * @return String : xau sau khi bo dau
     */
    public static String removeSign(String originalName) {
        if (originalName == null) {
            return "";
        }
        String result = originalName;
        for (int i = 0; i < SIGNED_ARR.length; i++) {
            result = result.replaceAll(SIGNED_ARR[i], UNSIGNED_ARR[i]);
        }
        return result;
    }
    /**
     * lay user id login 
     * @return
     */
    public static Long getUserId(HttpServletRequest req) {
        // TODO Auto-generated method stub
        Object object = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if(object instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)object;
            UserBean user = userService.getUserByUsername(userDetails.getUsername());
            if(user != null) {
                return user.getUserId();
           } else {
               return 0L;
           }
        } 
        return 0L;
    }
    /**
     * lay user id login 
     * @return
     */
    public static String getUserLoginName(HttpServletRequest request) {
        // TODO Auto-generated method stub
        Object object = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if(object instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)object;
            if( userDetails != null) {
                return userDetails.getUsername();
            } 
            
            /*
             * UserBean user = userService.loadUserByUsername(userName);
             * if(user != null) {
             * return user.getUserName();
             * } else {
             * return "";
             * }
             */
        } 
        return "";
        
        
    }
    public static String getCurrentLanguage(HttpServletRequest req) {
        return "en";
	}
	
	/**
	 * Loai cac ki tu /, \, null trong ten file Fix loi path traversal
	 *
	 * @param input : string
	 * @return String
	 */
    public static String getSafeFileName(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != '/' && c != '\\' && c != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
	}

	/**
     * @param fileName
     * @return
     */
    public static String extractFileExt(String fileName) {
        int dotPos = fileName.lastIndexOf(".");
        if (dotPos != -1) {
            return fileName.substring(dotPos);
        }
        return null;
    }
	
	/**
    *
    * @param fileName
    * @return
    */
	public static boolean isAllowedType(String fileName) {
		if (fileName != null && !"".equals(fileName.trim())) {
			String[] allowedType = {".jpg", ".jpeg", ".png", ".doc", ".docx", ".xls", ".xlsx", ".pdf", ".rar", ".zip", ".gif", ".txt", ".log", ".xml", ".7zip"};
			String ext = extractFileExt(fileName);
			if (ext == null) {
				return true;
			}
			ext = ext.toLowerCase();
			for (String extendFile : allowedType) {
				if (extendFile.equals(ext)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
     * Luu file import Excel.
     *
     * @param uploadFile Doi tuong MultipartFile
     * @param fileName Ten file
     * @param uploadPath Duong dan thu muc
     * @return Doi tuong Workbook
     * @throws Exception Exception
     */
    public static Workbook saveImportExcelFile(MultipartFile uploadFile, String fileName, String uploadPath) throws Exception {
        fileName = CommonUtil.getSafeFileName(CommonUtil.removeSign(fileName));
        if (isAllowedType(fileName)) {
            saveFile(uploadFile, fileName, uploadPath);
            File desDir = new File(uploadPath);
            File serverFile = new File(desDir.getAbsolutePath() + File.separator + fileName);
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("Cp1252"); // UTF-8
            ws.setCellValidationDisabled(true);
            Workbook workbook = Workbook.getWorkbook(serverFile, ws);
            return workbook;
        } else {
            throw new Exception("FILE TYPE NOT ALLOW");
        }
	}
	
	    /**
     * Luu file len server.
     *
     * @param uploadFile
     *            Doi tuong FormFile
     * @param fileName
     *            Ten file
     * @param uploadPath
     *            Duong dan thu muc
     * @throws Exception
     *             Exception
     */
    public static void saveFile(MultipartFile uploadFile, String fileName, String uploadPath) throws Exception {
        if (isAllowedType(uploadFile.getName())) {
            File desDir = new File(uploadPath);
            if (!desDir.exists()) {
                desDir.mkdir();
            }
            String url = desDir.getAbsolutePath() + File.separator + getSafeFileName(fileName);
            OutputStream outStream = new FileOutputStream(url);
            InputStream inStream = uploadFile.getInputStream();
            int bytesRead;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = inStream.read(buffer, 0, 1024 * 8)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();
        } else {
            throw new Exception("FILE TYPE NOT ALLOW");
        }
    }
    
    /**
	 * getAvatarPath
	 * 
	 * @param fileId
	 * @return
	 */
	public static String getAvatarPath(String fileId) {
		if (!CommonUtil.isNullOrEmpty(fileId)) {
			return String.format("%s/avatar/image/%s", CommonUtil.getConfig("fileStorage.serverUrl"), fileId);
		} else {
			return null;
		}
	}
 
}
