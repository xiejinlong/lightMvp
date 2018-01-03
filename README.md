# lightMvp
a easy mvp，利用注解的形式进行mvp绑定。

## 项目引入
在项目中根build文件添加jitpack的地址
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
然后在app的build文件中添加该工程的依赖
```
dependencies {
	        compile 'com.github.xiejinlong:lightMvp:-SNAPSHOT'
	}
```

# 使用方法
目前只支持fragment和activity与present的绑定。

## present
```
  public class ExamPresent extends BasePresent {
      public interface InterA {
        void change();
      }
      
      @BindV
      InterA interA;
      .....
  }
```

## fragment
fragment需要实现指定的present定义的Interface，
```
  public class ExamFragment extends BaseFragment implement ExamPresent.InterA {
  
  ....
  @BindP
  ExamPresent present
  
  }
```

## activity
activity需要实现指定的present定义的Interface，
```
  public class ExamActivity extends BaseActivity implement ExamPresent.InterA {
  
  ....
  @BindP
  ExamPresent present
  
  }
```


# 生命周期
present生命周期随着fragment的oncreateView方法和activity的onCreate方法创建。
随着fragment的onDestoryView方法和activity的onDestory方法销毁。
一个fragment和acitivty支持绑定多个present
一个present实例只支持绑定一个view
