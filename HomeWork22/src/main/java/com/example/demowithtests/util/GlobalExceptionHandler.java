package com.example.demowithtests.util;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.service.ServiceBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Repository repository;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        //ErrorDetails errorDetails = new ErrorDetails(new Date(), "Author not found with id =" + request.getParameter("id"), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceWasDeletedException.class)
    protected ResponseEntity<MyGlobalExceptionHandler> handleDeleteException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler("This user was deleted"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> resourceAlreadyExistsException(ResourceAlreadyExistsException ex,WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "User with email = "+ request.getParameter("email")+" already exists", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyUserParameterException.class)
    public ResponseEntity<MyGlobalExceptionHandler> emptyUserParameterException(){
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Some of the fields is empty"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NothingToGetException.class)
    public ResponseEntity<MyGlobalExceptionHandler> nothingToGetException(){
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Data Base is empty"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TooMuchReturnedUsersException.class)
    public ResponseEntity<?> tooMuchReturnedUsersException(TooMuchReturnedUsersException ex,WebRequest request){
        List<Employee> allEmployees=repository.findAll();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "System overload! You got "+ allEmployees.size()+" rows", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(IdNotExistsException.class)
    public ResponseEntity<MyGlobalExceptionHandler> idNotExistsException(){
        return new ResponseEntity<>(new MyGlobalExceptionHandler("There is not user with this id in our Data Base"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SameUpdateException.class)
    public ResponseEntity<?>sameUpdateException(TooMuchReturnedUsersException ex,WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "User with id = "+ request.getParameter("id")+"was already update with the same data", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NothingToRemoveException.class)
    public ResponseEntity<MyGlobalExceptionHandler> nothingToRemoveException(){
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Data Base is empty"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAllWasDeletedException.class)
    public ResponseEntity<MyGlobalExceptionHandler> notAllWasDeletedException(){
        return new ResponseEntity<>(new MyGlobalExceptionHandler("Not all data was deleted from Data Base"), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    private static class MyGlobalExceptionHandler {
        private String message;
    }
}
