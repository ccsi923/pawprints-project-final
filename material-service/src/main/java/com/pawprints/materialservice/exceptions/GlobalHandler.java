package com.pawprints.materialservice.exceptions;

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

    @ExceptionHandler(NoPossibleUpdateOrder.class)
    public void handlerNoPossibleUpdateOrder(NoPossibleUpdateOrder exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

}
