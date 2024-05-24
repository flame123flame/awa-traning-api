package awa.training.api.exception;

import awa.training.api.common.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HandleException extends RuntimeException {

    protected final String statusCode;
    protected final String messageTh;
    protected final String messageEn;
    protected final Object detail;

    public HandleException(ErrorCode errorCode) {
        super(errorCode.getMessageEn());
        this.statusCode = errorCode.getStatusCode();
        this.messageTh = errorCode.getMessageTh();
        this.messageEn = errorCode.getMessageEn();
        this.detail = Objects.nonNull(errorCode.getDetail()) ? errorCode.getDetail() : errorCode.getMessageEn();
    }

    public HandleException(ErrorCode errorCode,String message ,List<Long> listId) {
        super(errorCode.getMessageEn());
        this.statusCode = errorCode.getStatusCode();
        this.messageTh = errorCode.getMessageTh();
        this.messageEn = errorCode.getMessageEn();
        HashMap<String,List<Long>> data = new HashMap<>();
        if(!listId.isEmpty()) {
            data.put(message,listId);
        }
        this.detail = !data.isEmpty() ? data : errorCode.getMessageEn();
    }

}
