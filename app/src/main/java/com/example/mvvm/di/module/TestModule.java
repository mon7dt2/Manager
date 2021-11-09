package com.example.mvvm.di.module;

import com.example.mvvm.model.Post;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

    @Provides
    String provideTestString(){
        return "This is provide String";
    }


    @Provides
    Post providePost(){
        return new Post("1","1","thuis is title","this is body");
    }
}
