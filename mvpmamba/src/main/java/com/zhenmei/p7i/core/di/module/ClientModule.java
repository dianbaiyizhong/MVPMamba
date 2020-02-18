package com.zhenmei.p7i.core.di.module;

import dagger.Module;

/**
 * @author V.Wenju.Tian
 * 使用构建者模式,模块化组件
 */
@Module
public class ClientModule {

    private ClientModule(Buidler buidler) {


    }

    public static Buidler buidler() {
        return new Buidler();
    }


    public static final class Buidler {
        private Buidler() {
        }

        public ClientModule build() {

            return new ClientModule(this);
        }
    }

}
