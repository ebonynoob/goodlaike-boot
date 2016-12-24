package com.goodlaike.business.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.goodlaike.business.core.support.RestResult;
import com.goodlaike.business.core.support.IllegalLoginException;
import com.goodlaike.business.core.support.NotFoundException;

/**
 * Rest 控制器基类
 * 
 * @author jail
 */
public class BaseRestController {

	/**
	 * 500 异常
	 * 
	 * @param result
	 * @return
	 * @author jail
	 */
	protected ResponseEntity<?> serverError(RestResult result) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}

	/**
	 * 401 登录异常捕捉
	 * 
	 * @param e
	 * @return
	 * @author Jail Hu
	 */
	@ExceptionHandler(IllegalLoginException.class)
	protected ResponseEntity<?> illegalLoginException(IllegalLoginException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	/**
	 * 参数错误异常捕捉 ，针对 IllegalArgumentException.class
	 * 
	 * @param e
	 *            异常类
	 * @return ResponseEntity<?>
	 * @author Jail Hu
	 */
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<?> illegalArgumentExceptionHandler(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestResult.code(400).message(e.getMessage()));
	}

	/**
	 * 无权限业务返回 <br>
	 * {@code 403 Forbidden}
	 * 
	 * @param result
	 *            业务结果
	 * @return ResponseEntity
	 * @author Jail Hu
	 */
	protected ResponseEntity<?> forbidden(RestResult result) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
	}

	/**
	 * 资源不存在业务返回
	 * 
	 * @param result
	 *            业务结果
	 * @return ResponseEntity
	 * @author Jail Hu
	 */
	protected ResponseEntity<?> notFound(RestResult result) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}

	/**
	 * 资源未找到异常
	 * 
	 * @param e
	 *            异常
	 * @return ResponseEntity
	 * @author Jail Hu
	 */
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> notFoundExceptionHandler(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage().equals(RestResult.NOTFOUND.getMessage())
				? RestResult.NOTFOUND : RestResult.code(404).message(e.getMessage()));
	}

	/**
	 * 业务异常返回<br>
	 * {@code 555 ErrorBusiness}
	 * 
	 * @param result
	 *            业务结果
	 * @return ResponseEntity
	 * @author Jail Hu
	 */
	protected ResponseEntity<?> businessError(RestResult result) {
		return ResponseEntity.status(555).body(result);
	}
}
