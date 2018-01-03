package mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mvp.annotation.ParasBindPAnnotation;

/**
 *
 * mvpActivity基类，
 * 如果需要添加present，需要使用@BindP注解
 * 会自动绑定present，解绑present
 * 可以同时用@BindP指定多个Present，需要保证实现了了所有Present的listener
 *
 * @param <T>
 */

public abstract class BaseMvpActivity<T extends BasePresent> extends RxAppCompatActivity implements BaseView {

    private List<T> presentList = new CopyOnWriteArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParasBindPAnnotation.parasBindPresent(this);

        for (T present : presentList) {
            if (present != null) {
                present.onCreate(savedInstanceState);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        for (T present : presentList) {
            if (present != null) {
                present.onStart();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        for (T present : presentList) {
            if (present != null) {
                present.onResume();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (T present : presentList) {
            if (present != null) {
                present.onNewIntent(intent);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (T present : presentList) {
            if (present != null) {
                present.onSaveInstance(outState);
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (T present : presentList) {
            if (present != null) {
                present.onRestart();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (T present : presentList) {
            if (present != null) {
                present.onPause();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (T present : presentList) {
            if (present != null) {
                present.onStop();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (T present : presentList) {
            if (present != null) {
                present.onDestroy();
                presentList.remove(present);
            }
        }
    }

    public void addPresent(T t) {
        if (t != null) {
            presentList.add(t);
        }
    }



    @Override
    public Context getCtx() {
        return this;
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEve() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }
}
