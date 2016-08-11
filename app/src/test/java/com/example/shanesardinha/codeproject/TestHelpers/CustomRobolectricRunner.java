package com.example.shanesardinha.codeproject.TestHelpers;

import com.example.shanesardinha.codeproject.BuildConfig;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.util.ReflectionHelpers;

/**
 * Created by shanesardinha on 2016/08/11.
 */
public class CustomRobolectricRunner extends RobolectricGradleTestRunner {
    public CustomRobolectricRunner(Class<?> aClass) throws InitializationError {
        super(aClass);
        ReflectionHelpers.setStaticField(BuildConfig.class, "DEBUG", true);
    }
}