package nvd.spring.structure.basic.business.validator;

import nvd.spring.structure.basic.exception.ExpectException;
import nvd.spring.structure.basic.exception.ExpectExceptionCode;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class BaseInputValidator {

    protected final Pattern phonePattern, emailPattern, idPattern;
    protected final SimpleDateFormat SDF;
    protected final Integer ID_LENGTH = 32;

    protected final String DATE_FORMAT = "yyMMdd";

    protected final String NOT_EMPTY = "Input null or empty";
    protected final String SPACE_INVALID = "Space not allowed";
    protected final String LENGTH_INVALID = "Length invalid";
    protected final String INPUT_INVALID = "Input invalid";
    protected final String INPUT_DUPLICATED = "Input duplicated";

    public BaseInputValidator() {
        phonePattern = Pattern.compile("(09|01[2|6|8|9])+([0-9]{8})");
        emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        idPattern = Pattern.compile("[0-9a-f]{8}[0-9a-f]{4}[0-9a-f]{4}[0-9a-f]{4}[0-9a-f]{12}");
        SDF = new SimpleDateFormat(DATE_FORMAT);
    }

    public <I> void skip(I i) {}

    /**
     * Validate UUID for mysql, fixed length 32
     * @param id: String fix length 32
     * @throws ExpectException
     */
    public void validateId(String id) throws ExpectException {
        notEmpty(id, "Id");
        fixedLength(id, ID_LENGTH, "Id length must be 32");
        rejectSpace(id, INPUT_INVALID + "No space allowed in id");
        if (!idPattern.matcher(id).matches()) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID,
                    ExpectExceptionCode.INPUT_INVALID_MESSAGE,
                    "Wrong pattern of ID");
        }
    }

    /**
     * Validate UUID for mysql, fixed length 32
     * @param ids: List String, fix length 32
     * @throws ExpectException
     */
    public void validateIds(List<String> ids) throws ExpectException {
        notEmpty(ids, ExpectExceptionCode.INPUT_INVALID, "List Id can not be null or empty");
        for (String id: ids) {

            notEmpty(id, "Id must not be empty");
            fixedLength(id, ID_LENGTH, "Id length must be 32");
            rejectSpace(id, INPUT_INVALID + "No space allowed in list id");

            if (!idPattern.matcher(id).matches()) {
                throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, INPUT_INVALID, "");
            }
        }

        rejectDuplicated(ids, "Id");
    }

    /**
     * Validate duplicated value in Values
     * @param list: List of data need to validate 
     * @param detail: message detail when throws
     * @throws ExpectException with detail message
     */
    public <T> void rejectDuplicated(List<T> list, String detail) throws ExpectException {
        List<T> tmp = new LinkedList<>(list);
        for(T obj: list) {
            tmp.remove(obj);
            if (tmp.contains(obj)) {
                throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, INPUT_DUPLICATED, detail);
            }
        }
    }

    /**
     * Validate String id for custom id validators
     * @param id: id value need to validate
     * @param length: length of id input
     * @throws ExpectException
     */
    public void validateId(String id, Integer length) throws ExpectException {
        notEmpty(id, "Id must not be empty");
        fixedLength(id, length, "Id length must be " + length);
    }

    /**
     * Validate a String, rejected space
     * @param str: value need to validate
     * @param detail: detail message when throws 
     * @throws ExpectException
     */
    protected void rejectSpace(String str, String detail) throws ExpectException {
        if (str.trim().contains(" ")) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, "", detail);
        }
    }

    /**
     * Validate a String must not be empty
     * @param str: value need to validate
     * @param detail: detail message when throws 
     * @throws ExpectException
     */
    protected void notEmpty(String str, String detail) throws ExpectException {
        if (StringUtils.isEmpty(str)) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, NOT_EMPTY, detail);
        }
    }

    /**
     * Validate a Collection must not be empty
     * @param collection: value need to validate
     * @param code: value of code when throws
     * @param detail: detail message when throws 
     * @throws ExpectException
     */
    protected void notEmpty(@SuppressWarnings("rawtypes") Collection collection, Integer code, String detail) throws ExpectException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ExpectException(code, NOT_EMPTY, detail);
        }
    }

    /**
     * Validate a String, minimum length
     * @param str: value need to validate
     * @param min: minimum length of input string
     * @param detail: detail message when throws 
     * @throws ExpectException
     */
    protected void minLength(String str, Integer min, String detail) throws ExpectException {
        if (str.length() < min) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, LENGTH_INVALID, detail);
        }
    }

    /**
     * Validate a String, maximum length
     * @param str: value need to validate
     * @param max: maximum length of input string
     * @param detail: detail message when throws 
     * @throws ExpectException
     */
    protected void maxLength(String str, Integer max, String detail) throws ExpectException {
        if (str.length() > max) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, LENGTH_INVALID, detail);
        }
    }

    /**
     * Validate a String, fixed length
     * @param str: value need to validate
     * @param fixed: fixed length of input string
     * @param detail: detail message when throws 
     * @throws ExpectException
     */
    protected void fixedLength(String str, Integer fixed, String detail) throws ExpectException {
        if (str.length() != fixed) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, LENGTH_INVALID, detail);
        }
    }

    /**
     * Validate a Date
     * @param date: value need to validate
     * @param mess: fixed length of input string
     * @throws ExpectException
     */
    protected Date validateDate(String date, String mess) throws ExpectException {
        try {
            return SDF.parse(date);
        } catch (Exception e) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, mess, "");
        }
    }

    /**
     * Validate two dates, from date must be less than to date
     * @param from: java util date input
     * @param to: java util date input
     * @throws ExpectException
     */
    protected void compareDates(Date from, Date to) throws ExpectException {
        if (from == null || to == null) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, "From date and to date must not be empty", "");
        }

        if (from.after(to)) {
            throw new ExpectException(ExpectExceptionCode.INPUT_INVALID, "From date must less than  to date", "");
        }
    }
    
}
