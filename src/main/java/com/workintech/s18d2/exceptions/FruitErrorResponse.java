package com.workintech.s18d2.exceptions;


public record FruitErrorResponse(int status, String message, long timestamp) {
}