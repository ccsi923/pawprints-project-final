package com.pawprints.edgeservice.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(NotFoundException.class)
    public void handlerProductNotFoundException(NotFoundException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(StockException.class)
    public void handlerMaxStockException(StockException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CantUpdatePurchaseUnit.class)
    public void handlerCantUpdatePurchaseUnit(CantUpdatePurchaseUnit exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NoPossibleCreateAnimal.class)
    public void handlerNoPossibleCreateAnimal(NoPossibleCreateAnimal exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(StatusClosedException.class)
    public void handlerStatusClosedException(StatusClosedException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CartClientNotWorkingException.class)
    public void handleCartClientNotWorkingException(CartClientNotWorkingException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, e.getMessage());
    }
    @ExceptionHandler(UserClientNotWorkingException.class)
    public void handleUserClientNotWorkingException(UserClientNotWorkingException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, e.getMessage());
    }

    @ExceptionHandler(MaterialClientNotWorkingException.class)
    public void handleMaterialClientNotWorkingException(MaterialClientNotWorkingException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, e.getMessage());
    }

    @ExceptionHandler(CommentClientNotWorkingException.class)
    public void handleCommentClientNotWorkingException(CommentClientNotWorkingException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, e.getMessage());
    }


}
