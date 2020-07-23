package com.zhenmei.p7i.core.di.module;

import dagger.Module;


@Module
public class ClientModule {

    private ClientModule(Builder builder) {


    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private Builder() {
        }

        public ClientModule build() {

            return new ClientModule(this);
        }
    }

}
