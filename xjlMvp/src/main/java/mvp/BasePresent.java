package mvp;

import android.content.Intent;
import android.os.Bundle;

import mvp.annotation.ParasBindVAnnotation;

/**
 *
 * BaseMvp基类, 在定义present时必须继承这个类，包含了常用的生命周期方法
 * 在onDestroy时也会自动解绑
 * 通过@BindV注解可以直接将mvpView转化为指定类型
 *
 */

public class BasePresent {

    protected BaseView mvpView;

    public BasePresent() {
    }

    public void attachView(BaseView mvpView) {
        this.mvpView = mvpView;
    }

    public void detach() {
        this.mvpView = null;
    }

    public void onCreate(Bundle saveInstanceState) {
    }

    public void onResume() {
    }

    public void onNewIntent(Intent intent) {
    }

    public void onPause() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        detach();
        ParasBindVAnnotation.detachView(this);
    }


    public void onRestart() {
    }

    public void onSaveInstance(Bundle state) {
    }

    public void onRestoreSavedInstance(Bundle saveInstance) {
    }
}
