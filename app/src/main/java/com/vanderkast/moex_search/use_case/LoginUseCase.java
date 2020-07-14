package com.vanderkast.moex_search.use_case;

import com.vanderkast.moex_search.model.LoginCredentials;
import com.vanderkast.moex_search.model.Result;

public abstract class LoginUseCase {
    public Result login(LoginCredentials credentials) {
        try {
            String token = authorize(credentials);
            saveToken(token);
            saveCredentials(credentials);
        } catch (AuthException e) {
            return new Result.Failure(e);
        }
        return new Result.Success();
    }

    protected abstract String authorize(LoginCredentials credentials) throws AuthException;

    protected abstract void saveCredentials(LoginCredentials credentials);

    protected abstract void saveToken(String token);

    public static class AuthException extends Exception {

    }

    public static class WrongCredentials extends AuthException {

    }

    public static class NetworkError extends  AuthException {

    }
}
