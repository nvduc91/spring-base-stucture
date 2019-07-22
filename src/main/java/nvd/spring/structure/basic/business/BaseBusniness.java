package nvd.spring.structure.basic.business;

import org.springframework.http.HttpStatus;
import nvd.spring.structure.basic.response.Payload;

public interface BaseBusniness {
    default <T> Payload<T> responseModel() {
        return new Payload<T>(null, null, HttpStatus.OK.value());
    }

    default <T> Payload<T> responseModel(T data) {
        return new Payload<T>(data, null, HttpStatus.OK.value());
    }

    default <T, M> Payload<T> responseModel(T data, M meta) {
        return new Payload<T>(data, meta, HttpStatus.OK.value());
    }
}
