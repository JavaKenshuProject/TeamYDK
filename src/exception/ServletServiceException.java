package exception;

import javax.servlet.ServletException;

public class ServletServiceException extends ServletException{
	//オリジナルの例外を定義する際はExceptionではなくServletExceptionを継承しないとエラーページ遷移されない

	public ServletServiceException(String e){
		super(e);
	}

}
