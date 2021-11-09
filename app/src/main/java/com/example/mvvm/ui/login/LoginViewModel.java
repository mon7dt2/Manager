package com.example.mvvm.ui.login;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.base.BaseViewModel;
import com.example.mvvm.repository.Repository;

import javax.inject.Inject;

/**
 * Created by Mon7 on 9/29/2021.
 */
public class LoginViewModel extends BaseViewModel{

    private final Repository repository;
    private MutableLiveData<String> loginStatus = new MutableLiveData<>();

    public MutableLiveData<String> getLoginStatus() {
        return loginStatus;
    }

    @Inject
    public LoginViewModel(Repository repository) {
        this.repository = repository;
    }

    public void login(String username, String password){
        if (!username.isEmpty()&&!password.isEmpty()){
            byte[] author = (username + ":" + password).getBytes();
            String encodedString = "Basic " + Base64.encodeToString(author,Base64.NO_WRAP);
            compositeDisposable.add(repository.login(encodedString)
                    .doOnSubscribe(d -> {
                        loading.setValue(true);
                    })
                    .doFinally(() -> {
                        loading.setValue(false);
                    })
                    .subscribe(loginResponse -> {
                        Log.d("myLog", loginResponse.toString());
                        loginStatus.setValue("HTTP OK");
                    }, throwable -> {
                        Log.d("myLog", throwable.getMessage());
                        loginStatus.setValue(throwable.getMessage());
                    }));
        }
    }
}
