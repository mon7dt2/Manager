package com.example.mvvm.ui.login;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseActivity;
import com.example.mvvm.databinding.ActivityLoginBinding;
import com.example.mvvm.ui.dashboard.DashboardActivity;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Mon7 on 9/29/2021.
 */
public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding>{
    @NonNull
    @NotNull
    @Override
    protected LoginViewModel createViewModel() {
        return new ViewModelProvider(this,viewModelFactory).get(LoginViewModel.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        viewModel.getLoading().observe(this,isLoading->{
            if(isLoading != null){
                if(isLoading){
                    loadingDialog.show();
                }else {
                    loadingDialog.hide();
                }
            }
        });
        viewModel.getLoginStatus().observe(this,loginStatus ->{
            if (loginStatus.equals("HTTP OK")){
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
            } else if(loginStatus.equals("HTTP 500")||loginStatus.equals("HTTP 503 Service Unavailable")){
                binding.txtError.setVisibility(View.VISIBLE);
                binding.txtError.setText("Server không phản hồi");
            } else {
                binding.txtError.setVisibility(View.VISIBLE);
                binding.txtError.setText("invalid username or password");
            }
        });
    }

    @Override
    protected void initData() {
        binding.loginEdtName.setText("zunezx");
        binding.loginEdtPass.setText("zunezx");
    }

    @Override
    protected void initListener() {
        binding.btnLogin.setOnClickListener(v -> {
            if (!binding.loginEdtName.getText().toString().trim().equals("") &&
                    !binding.loginEdtPass.getText().toString().trim().equals(""))
            {
                binding.txtError.setVisibility(View.GONE);
                viewModel.login(binding.loginEdtName.getText().toString().trim(), binding.loginEdtPass.getText().toString().trim());
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
