package br.com.eduardofbom.ScreenMatch.infrastructure.model;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
