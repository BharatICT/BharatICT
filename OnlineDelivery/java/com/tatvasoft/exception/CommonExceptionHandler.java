package com.tatvasoft.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import com.tatvasoft.commonentity.CommonResponse;
import com.tatvasoft.commonentity.ErrorDetails;
import com.tatvasoft.commonentity.ErrorResponse;
import com.tatvasoft.commonentity.WebResponseJsonBo;
import com.tatvasoft.constants.Constants;

@ControllerAdvice
public class CommonExceptionHandler {

	public static final String HANDLE_ENTITY_EXCEPTION_ERROR = "handleEntityException: Error";
	private static final String INTERNAL_SERVER_ERROR = "internal.server.error";
	private final MessageSource messageSource;
	private final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@Autowired
	public CommonExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @param exception
	 * @return ResponseEntity with error details
	 */
	public ResponseEntity<String> handleExceptionAny(Exception ex) {
		log.error("Internal server error", ex);
		JSONArray ar = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONArray mainAr = new JSONArray();
		JSONObject mainObj = new JSONObject();
		String msg = "";
		int i = 0;
		Error error = null;
		if (ex instanceof BadRequestException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.BAD_REQUEST);
//					mainObj.put("status", HttpStatus.OK);
				mainObj.put("Message_Description", "Malformed message");
				mainAr.put(mainObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(mainAr.toString());
		} else if (ex instanceof NotAuthorizedException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.UNAUTHORIZED);
				mainObj.put("Message_Description", "Authentication failure");
				mainAr.put(mainObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(mainAr.toString());
		} else if (ex instanceof ForbiddenException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.FORBIDDEN);
				mainObj.put("Message_Description", "Not permitted to access");
				mainAr.put(mainObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(mainAr.toString());
		} else if (ex instanceof NotAllowedException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.METHOD_NOT_ALLOWED);
				mainObj.put("Message_Description", "HTTP method not supported");
				mainAr.put(mainObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(mainAr.toString());
		} else if (ex instanceof NotFoundException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.NOT_FOUND);
				mainObj.put("Message_Description", "Couldn�t find resource");
				mainAr.put(mainObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(mainAr.toString());
		} else if (ex instanceof NotAcceptableException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.NOT_ACCEPTABLE);
				mainObj.put("Message_Description", "Client media type requested not supported");
				mainAr.put(mainObj);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.value()).body(mainAr.toString());
		} else if (ex instanceof NotSupportedException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
				mainObj.put("Message_Description", "Client posted media type not supported");
				mainAr.put(mainObj);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).body(mainAr.toString());
		} else if (ex instanceof InternalServerErrorException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
				mainObj.put("Message_Description", "General server error");
				mainAr.put(mainObj);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(mainAr.toString());
		} else if (ex instanceof InternalServerErrorException) {
			try {
				mainObj.put("error_message", ar.put(ex.getMessage()));
				mainObj.put("status", HttpStatus.SERVICE_UNAVAILABLE);
				mainObj.put("Message_Description", "Server is temporarily unavailable or busy");
				mainAr.put(mainObj);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE.value()).body(mainAr.toString());
		} else {
			try {
				if (ex.getCause() != null && !"".equals(ex.getCause())) {
					mainObj.put("error_message", ar.put(ex.getCause().toString()));
				} else {
					mainObj.put("error_message", ar.put(ex.toString()));
				}
				mainObj.put("status", 404);
				mainObj.put("Message_Description", "Not Found !!!");
				mainAr.put(mainObj);
				return ResponseEntity.status(404).body(mainAr.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception
	 * @return ResponseEntity with error details
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ SecurityException.class })
	public ResponseEntity<CommonResponse<Map<String, Object>>> securityException(SecurityException exception) {
		Map<String, Object> map = new HashMap<>();
		String msg = "";
		try {
			msg = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
		} catch (NoSuchMessageException e) {
			msg = exception.getMessage();
		}

		map.put(Constants.MSG_ERROR, new ErrorResponse(msg, HttpStatus.UNAUTHORIZED.value()));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(CommonResponse.builder(map).message(msg).status(HttpStatus.UNAUTHORIZED.value()).build());
	}

	@ExceptionHandler({ UnsatisfiedDependencyException.class })
	public ResponseEntity<WebResponseJsonBo> unsatisfiedDependencyException(UnsatisfiedDependencyException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	/**
	 * @param exception
	 * @return ResponseEntity with error details
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<WebResponseJsonBo> methodNotAllowedException(
			HttpRequestMethodNotSupportedException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	@ExceptionHandler({ MultipartException.class })
	public ResponseEntity<WebResponseJsonBo> multipartException(MultipartException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler({ MethodNotAllowed.class })
	public ResponseEntity<WebResponseJsonBo> methodNotAllowed(MethodNotAllowed exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(200);
		aj.setError_message(errors);
		aj.setMessage_description(HttpStatus.METHOD_NOT_ALLOWED.name());
		return ResponseEntity.ok(aj);
	}

	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<WebResponseJsonBo> unsupportedMediaType(HttpMediaTypeNotSupportedException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	@ExceptionHandler({ MissingPathVariableException.class })
	public ResponseEntity<WebResponseJsonBo> missingPathVariable(MissingPathVariableException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	public String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	@ExceptionHandler({ MaxUploadSizeExceededException.class })
	public ResponseEntity<WebResponseJsonBo> maxUploadSizeExceededException(MaxUploadSizeExceededException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		WebResponseJsonBo aj = new WebResponseJsonBo();
		if (exception.contains(FileUploadException.class)) {
			SizeLimitExceededException fx = (SizeLimitExceededException) exception.getRootCause();
			System.out.println(fx.getActualSize());
			errors.put("error_message", "the request was rejected because its size "
					+ humanReadableByteCount(new Long(fx.getActualSize()), true) + " exceeds the configured maximum ("
					+ humanReadableByteCount(new Long(exception.getMaxUploadSize()), true) + ")");
			aj.setValidated(false);

			aj.setStatus(Constants.ERROR_CODE);
			aj.setError_message(errors);
			aj.setMessage_description(errors.toString());
			Map<String, Object> map = new HashMap<>();
			map.put("error", aj);
		} else {
			String msg = exception.getCause().getMessage();
			String s2 = msg.substring(msg.indexOf("(") + 1, msg.indexOf(")"));
			errors.put("error_message",
					"the request was rejected because its size " + humanReadableByteCount(new Long(s2), true)
							+ " exceeds the configured maximum ("
							+ humanReadableByteCount(new Long(exception.getMaxUploadSize()), true) + ")");
			aj.setValidated(false);
			aj.setStatus(Constants.ERROR_CODE);
			aj.setError_message(errors);
			aj.setMessage_description(errors.toString());
			Map<String, Object> map = new HashMap<>();
			map.put("error", aj);
		}
		return ResponseEntity.ok(aj);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class, DuplicateEntityException.class,
			EntityCreationException.class, BadDataException.class, EntityModificationException.class })
	public ResponseEntity<CommonResponse<Map<String, Object>>> handleEntityException(Exception exception) {
		String msg = "";
		try {
			msg = messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale());
		} catch (NoSuchMessageException e) {
			if (exception instanceof HttpMessageNotReadableException) {
				msg = "Invalid data";
			} else {
				msg = exception.getMessage();
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put(Constants.MSG_ERROR, new ErrorResponse(msg, HttpStatus.BAD_REQUEST.value()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(CommonResponse.builder(map).message(msg).status(HttpStatus.BAD_REQUEST.value()).build());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<CommonResponse<Map<String, Object>>> handleNotFound(Exception exception) {
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.MSG_ERROR, new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CommonResponse.builder(map)
				.message(exception.getMessage()).status(HttpStatus.NOT_FOUND.value()).build());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<WebResponseJsonBo> handleValidation(BindException exception, HttpServletRequest re) {
		BindingResult result = exception.getBindingResult();
//	        List<FieldError> fieldErrors = result.getFieldErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getAllErrors().stream()
				.map(error -> (FieldError) error).collect(Collectors.toList());
		return handleValidationError(fieldErrors, re, exception);

	}

	private ResponseEntity<WebResponseJsonBo> handleValidationError(List<FieldError> fieldErrors, HttpServletRequest re,
			BindException exception) {
		Map<String, String> messages = new HashMap<>();
		Map<String, Object> errors = new HashMap<String, Object>();
		for (FieldError fieldError : fieldErrors) {
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(200);
		aj.setError_message(errors);
		aj.setReturn_message("Fill all the mandatory fields..");
		return ResponseEntity.ok(aj);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<WebResponseJsonBo> constraintViolationException(RuntimeException exception) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<WebResponseJsonBo> dataIntegrityViolationException(DataIntegrityViolationException e) {
		Map<String, Object> errors = new HashMap<String, Object>();
		String message = e.getMostSpecificCause().getMessage();
		String column = message.substring(message.indexOf("(") + 1, message.indexOf(")")).replace("_", " "); //
		WebResponseJsonBo aj = new WebResponseJsonBo();
		errors.put("error_message", column + " is already exist");
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<WebResponseJsonBo> allException(Exception exception) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.toString());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		if (exception.getCause() != null && !"".equals(exception.getCause())) {
			if (exception.getCause().getCause() != null) {
				aj.setMessage_description(exception.getCause().getCause().toString());
			} else {
				aj.setMessage_description(exception.getCause().toString());
			}
		} else {
			aj.setMessage_description(errors.toString());
		}
		exception.printStackTrace();
		return ResponseEntity.ok(aj);
	}
	@ExceptionHandler({ DataAccessPrivilegesException.class })
	public ResponseEntity<WebResponseJsonBo> dataAccessPrivilegesException(DataAccessPrivilegesException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}
	@ExceptionHandler({ UserSessionExpariedException.class })
	public ResponseEntity<WebResponseJsonBo> userSessionExpariedException(UserSessionExpariedException exception) {

		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		aj.setMessage_description(errors.toString());
		return ResponseEntity.ok(aj);
	}
	@ExceptionHandler({ UniqueConstrintException.class })
	public ResponseEntity<WebResponseJsonBo> uniqueConstrintException(UniqueConstrintException exception) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("error_message", exception.getMessage());
		WebResponseJsonBo aj = new WebResponseJsonBo();
		aj.setValidated(false);
		aj.setStatus(Constants.ERROR_CODE);
		aj.setError_message(errors);
		if (exception.getCause() != null && !"".equals(exception.getCause())) {
			if (exception.getCause().getCause() != null) {
				aj.setMessage_description(exception.getCause().getCause().toString());
			} else {
				aj.setMessage_description(exception.getCause().toString());
			}
		} else {
			aj.setMessage_description(errors.toString());
		}
		return ResponseEntity.ok(aj);
	}

}
