package mvp.annotation;

import java.lang.reflect.Field;

import mvp.BaseMvpActivity;
import mvp.BaseMvpFragment;
import mvp.BasePresent;

public class ParasBindPAnnotation {

    @SuppressWarnings("unchecked")
    public static void parasBindPresent(BaseMvpFragment fragment) {
        Class clazz = fragment.getClass();
        while (!(clazz.equals(Object.class) )) {
            parasBindPresent(fragment, clazz);
            clazz = clazz.getSuperclass();
        }
    }

    private static void parasBindPresent(BaseMvpFragment<BasePresent> fragment, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(BindP.class)) {
                try {
                    Class<?> c = f.getType();
                    Object t = c.newInstance();
                    f.setAccessible(true);
                    f.set(fragment, t);

                    ((BasePresent) t).attachView(fragment);
                    fragment.addPresent((BasePresent) t);
                    ParasBindVAnnotation.parasBindView(t, fragment);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void parasBindPresent(BaseMvpActivity activity) {
        Class clazz = activity.getClass();
        while (!(clazz.equals(Object.class) )) {
            parasBindPresent(activity, clazz);
            clazz = clazz.getSuperclass();
        }
    }

    private static void parasBindPresent(BaseMvpActivity<BasePresent> activity, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(BindP.class)) {
                try {
                    Class<?> c = f.getType();
                    Object t = c.newInstance();
                    f.setAccessible(true);
                    f.set(activity, t);

                    ((BasePresent) t).attachView(activity);
                    activity.addPresent((BasePresent) t);
                    ParasBindVAnnotation.parasBindView(t, activity);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}