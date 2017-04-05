
multipermissions
==
multipermissions is a wrapper library to simplify basic system permissions logic.

multipermissions主要是为处理android 6.0的动态权限，其主要是对easypermissions（https://github.com/googlesamples/easypermissions ）做了一层封装，按照实际的使用简化使用的复杂度，同时保留兼容easypermissions的接口。

引入
--
project's build.gradle

    allprojects {
      repositories {
          jcenter()
          maven { url 'https://jitpack.io' }
      }
    }

module's buid.gradle

    dependencies {
        compile 'com.github.porco-zheng:multipermissions:v1.0'
    }    

使用
---
* 申请权限

```java
MultiPermissions.get().configDefault(true).needPermissions(MainActivity.this,RC_CAMERA_PERM,PERMISSION_NOTE,Manifest.permission.CAMERA);
```

* 权限申请回调

```java
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    // EasyPermissions handles the request result.
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
}
```

* 授权成功

```java
@AfterPermissionGranted(RC_CAMERA_PERM)
public void cameraTask() {}
```
注意，当调用了configDefault(true)后，第一次请求权限拒绝会弹出跳转设置的窗口，如果进行跳转回来的处理，可以在onActivityResult(int requestCode, int resultCode, Intent data)中进行判断，requestCode的默认值为**0311**.如果M以下的系统中，当设置中手动关闭权限后，执行权限出错后会弹出报警框，点击后会关闭当前activity，如果想自己处理异常，可以在cameraTask处进行异常捕获处理。
其他的一些使用可参看easypermissions的api说明。

混淆
---
```
-dontwarn pub.porco.multipermissions.**
-keep class pub.porco.multipermissions.** {*;}
-keep interface pub.porco.multipermissions.** { *; }
```
