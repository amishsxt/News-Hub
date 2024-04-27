package com.example.newshub.utils;

public interface DataSourceCallback<T> {
    void onSuccess(T data);
    void onError(String errorMessage);
}

