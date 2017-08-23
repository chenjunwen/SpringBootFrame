package com.tuling.common.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseEntity<JSONObject> handleRRException(RRException e){
		ResponseEntity responseEntity = new ResponseEntity(e.getErr(),e.getStatus());
		return responseEntity;
	}

	/**
	 * 请求方式不对
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleRequestMethodException(HttpRequestMethodNotSupportedException e){
		ResponseEntity responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<String> handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>("没有权限，请联系管理员授权", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
