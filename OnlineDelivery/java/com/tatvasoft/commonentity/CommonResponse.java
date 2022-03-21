package com.tatvasoft.commonentity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommonResponse<T> implements Serializable {


    /**
     *kuldipsinh
     */
    private static final long serialVersionUID = 1L;

    protected String message;

    protected List<String> errorMessages;

    protected int status;

    protected T payload;

    /**
     *
     */
    public CommonResponse() {

    }

    /**
     * @param payload payload to return to the client
     */
    private CommonResponse(T payload) {
        this.payload = payload;
    }

    public static <T> CommonResponse<T> of(T payload) {
        return CommonResponse.builder(payload).status(200).build();
    }

    public static <T> CommonResponse<Object> of(String message) {
        return CommonResponse.builder(new Object()).status(200).message(message).build();
    }

    public static <T> CommonResponse<T> of(T payload, String msg) {
        return CommonResponse.builder(payload).message(msg).status(200).build();
    }

    public static <T> CommonResponse<Map<String, T>> of(String key, T payload, String msg) {
        Map<String, T> map = new HashMap<>(1);
        map.put(key, payload);
        return CommonResponse.builder(map).message(msg).status(200).build();
    }
    
   
    public static <T> CommonResponse<Map<String, T>> of(String key, T payload) {
        Map<String, T> map = new HashMap<>(1);
        map.put(key, payload);
        return CommonResponse.builder(map).status(200).build();
    }

    /**
     * @param payload payload to return to the client
     * @return builder
     */
    public static <T> Builder<T> builder(T payload) {
        return new Builder<>(payload);
    }

    /**
     * @return {@link String}
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message will display on ui side
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return T
     */
    public T getPayload() {
        return payload;
    }

    /**
     * @param payload payload to return to the client
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /**
     * @return errors
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @param errorMessages multiple error message
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * @param <T>
     * @author kuldipsinh
     */
    public static class Builder<T> {
        private final CommonResponse<T> response;

        /**
         * @param payload payload to return to the client
         */
        public Builder(T payload) {
            response = new CommonResponse<>(payload);
        }

        /**
         * @param message will display on ui side
         * @return Builder
         */
        public Builder<T> message(String message) {
            response.message = message;
            return this;
        }


        /**
         * @param status API specific status code
         * @return Builder
         */
        public Builder<T> status(int status) {
            response.status = status;
            return this;
        }


        /**
         * @param errorMessages multiple error message
         * @return builder
         */
        public Builder<T> errors(List<String> errorMessages) {
            response.errorMessages = errorMessages;
            return this;
        }

        /**
         * @param payload payload to return to the client
         * @return builder
         */
        public Builder<T> payload(T payload) {
            response.payload = payload;
            return this;
        }

        /**
         * @return {@link CommonResponse}
         */
        public CommonResponse<T> build() {
            return response;
        }
    }
}
