package next.exception;

public class DataAccessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataAccessException(){
    	super();
    }
    
    public DataAccessException(String message){
    	super(message);
    }
    
    public DataAccessException(Throwable throwable){
    	super(throwable);
    }
    
    public DataAccessException(String message,Throwable throwable){
    	super(message,throwable);
    }
    
    public DataAccessException(String message,Throwable throwable, boolean enableSuppression, boolean writeableStackTrace){
    	super(message,throwable,enableSuppression,writeableStackTrace);
    }
    
}
