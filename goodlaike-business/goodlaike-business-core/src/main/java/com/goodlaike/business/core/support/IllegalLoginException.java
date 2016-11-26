package com.goodlaike.business.core.support;

/**
 * 不合法的登录异常
 * 
 * @author Jail Hu
 */
public class IllegalLoginException extends IllegalStateException {

	private static final long serialVersionUID = 9199373482683403440L;

	public IllegalLoginException() {
		super();
	}

	public IllegalLoginException(String arg0) {
		super(arg0);
	}
}
