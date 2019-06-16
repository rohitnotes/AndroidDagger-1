package com.tkb.dagger.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {
    private static final String TAG = "ViewModelProviderFactor";

    Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators){

        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        Provider<? extends ViewModel> creator = creators.get(modelClass);

        if (creator == null){
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>entry : creators.entrySet()){
                if (modelClass.isAssignableFrom(entry.getKey())){
                    creator = entry.getValue();
                    break;
                }
            }
        }

        if (creator == null){
            throw new IllegalArgumentException("unknown class model "+modelClass);
        }

        try {
            return (T)creator.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
