package mvp;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * BaseView基类
 *
 */

public interface BaseView {

    // 获取context
    Context getCtx();

    // 获取 bindEvent,防止出现内存泄漏
    <T> LifecycleTransformer<T> bindUntilEve();
}
