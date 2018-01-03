package mvp.annotation;

import java.lang.reflect.Field;

import mvp.BaseMvpActivity;
import mvp.BaseMvpFragment;


public class ParasBindVAnnotation {

    public static void parasBindView(Object t, BaseMvpFragment fragment) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(BindV.class)) {
                try {
                    f.setAccessible(true);
                    f.set(t, fragment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void detachView(Object t) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(BindV.class)) {
                try {
                    f.setAccessible(true);
                    f.set(t, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void parasBindView(Object t, BaseMvpActivity activity) {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(BindV.class)) {
                try {
                    f.setAccessible(true);
                    f.set(t, activity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
