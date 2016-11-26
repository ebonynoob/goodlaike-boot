package com.goodlaike.business.core.support;

/**
 * 资源不存在异常
 * 
 * @author Jail Hu
 */
public final class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1308523808279288882L;

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
		super(message);
	}
}
