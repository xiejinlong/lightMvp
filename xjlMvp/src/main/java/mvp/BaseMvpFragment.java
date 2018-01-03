package mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mvp.annotation.ParasBindPAnnotation;

/**
 *
 * mvpFragment基类，
 * 如果需要添加present，需要使用@BindP注解
 * 会自动绑定present，解绑present
 * 可以同时用@BindP指定多个Present，需要保证实现了了所有Present的listener
 *
 * @param <T>
 */
public abstract class BaseMvpFragment<T extends BasePresent> extends RxFragment implements BaseView {

    private List<T> presentList = new CopyOnWriteArrayList<>();

    public BaseMvpFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        ParasBindPAnnotation.parasBindPresent(this);
        return view;
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (T present : presentList) {
            if (present != null) {
                present.onSaveInstance(outState);
            }
        }
    }



    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        for (T present : presentList) {
            if (present != null) {
                present.onRestoreSavedInstance(savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
        for (T present : presentList) {
            if (present != null) {
                present.onDestroy();
                presentList.remove(present);
            }
        }
    }

    public void addPresent(T basePresent) {
        if (basePresent != null) {
            presentList.add(basePresent);
        }

    }

    @Override
    public Context getCtx() {
        return getContext();
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEve() {
        return bindUntilEvent(FragmentEvent.DESTROY_VIEW);
    }
}
