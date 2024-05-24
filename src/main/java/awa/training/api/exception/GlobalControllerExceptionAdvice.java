package awa.training.api.exception;

import awa.training.api.common.ErrorCode;
import awa.training.api.common.model.BaseErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@Log4j2
@RestControllerAdvice
public class GlobalControllerExceptionAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class})
    public BaseErrorResponse handleArgumentNotValid(Exception ex) {
        log.error("{} : {}", ex.getClass().getSimpleName(), ex.getMessage());
        if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            return getBaseErrorResponse((methodArgumentNotValidException).getBindingResult());
        } else if (ex instanceof BindException bindException) {
            return getBaseErrorResponse((bindException).getBindingResult());
        } else if (ex instanceof MissingServletRequestParameterException missingServletRequestParameterException) {
            return getBaseErrorResponse(missingServletRequestParameterException);
        }else if(ex instanceof HttpMessageNotReadableException httpMessageNotReadableException){
            return getBaseErrorResponse(httpMessageNotReadableException);
        } else {
            return getBaseErrorResponse((MethodArgumentTypeMismatchException) ex);
        }
    }

    private BaseErrorResponse getBaseErrorResponse(BindingResult bindingResult) {
        Map<String, List<String>> errors = new HashMap<>();
        bindingResult.getFieldErrors()
                .forEach(fieldError -> {
                    List<String> errorMsg;
                    String field = fieldError.getField();
                    if (errors.get(field) == null) {
                        errorMsg = new ArrayList<>();
                    } else {
                        errorMsg = errors.get(field);
                    }
                    errorMsg.add(fieldError.getDefaultMessage());
                    errors.put(field, errorMsg);
                });
        BaseErrorResponse response = getResponse(ErrorCode.VALIDATION_ERROR);
        response.setDetail(errors);
        return response;
    }

    private BaseErrorResponse getBaseErrorResponse(MissingServletRequestParameterException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put(ex.getParameterName(), Collections.singletonList(ex.getMessage()));
        BaseErrorResponse response = getResponse(ErrorCode.VALIDATION_ERROR);
        response.setDetail(errors);
        return response;
    }

    private BaseErrorResponse getBaseErrorResponse(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return this.getResponse(ErrorCode.UNEXPECTED);
    }

    private BaseErrorResponse getBaseErrorResponse(MethodArgumentTypeMismatchException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        errors.put(ex.getName(), Collections.singletonList(ex.getMessage()));
        BaseErrorResponse response = getResponse(ErrorCode.VALIDATION_ERROR);
        response.setDetail(errors);
        return response;
    }

    @ExceptionHandler(HandleException.class)
    public ResponseEntity<BaseErrorResponse> exciseApiException(HandleException ex) {
        log.error("{} : {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseErrorResponse.builder()
                        .statusCode(ex.getStatusCode())
                        .messageTh(ex.getMessageTh())
                        .messageEn(ex.getMessageEn())
                        .detail(ex.getDetail())
                        .build());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseErrorResponse handleUnexpected(Exception ex) {
        log.error("Exception : {}", ex.getMessage(), ex);
        BaseErrorResponse response = this.getResponse(ErrorCode.UNEXPECTED);
        response.setMessageEn(ex.getMessage());
        return response;
    }

    private BaseErrorResponse getResponse(ErrorCode errorCode) {
        return BaseErrorResponse.builder()
                .statusCode(errorCode.getStatusCode())
                .messageEn(errorCode.getMessageEn())
                .messageTh(errorCode.getMessageTh())
                .detail(errorCode.getDetail())
                .build();
    }

}
