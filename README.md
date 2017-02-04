[TOC]

`just in develop!`

# Android permission-check

Android ALog provides :
- ~~Full method count 00~~

Less Runtime :
- minSdkVersion 15
- gradle or maven
- jar [You can Download just like this Path]((https://github.com/MDL-Sinlov/MDL-Android-Repo/raw/master/mvn-repo/mdl/sinlov/android/))

> eclipse just use every repo at version `permission-check-x.x.x-jarLib.jar`

Project Runtime:
- Android Studio 2.2.3
- appcompat-v7:23.4.0
- Gradle 2.14.1
- com.android.tools.build:gradle:2.2.3
- minSdkVersion 15

# Last Version Info

- version 0.0.1
- repo at https://github.com/MDL-Sinlov/MDL-Android-Repo

# Dependency

at root project `build.gradle`

```gradle
repositories {
    maven {
        url 'https://raw.githubusercontent.com/MDL-Sinlov/MDL-Android-Repo/master/mvn-repo/'
    }
    jcenter()
    ...
}
```

in module `build.gradle`

```gradle
dependencies {
    compile 'mdl.sinlov.android:permission-check:0.0.1'
}
```

# Build APK for test

- Do not annotation // OutBuildApk_2.0 in `app/build.gradle` then run

```
./gradlew :app:outTAD
```

out `*.apk` at `app/build/outApk`

# Usage

- CheckPermission

```java
mdlPermissionCheck = MDLPermissionUtils.getInstance();
boolean has_permission = mdlPermissionCheck.checkFromManifest(getApplication(), PermissionGroup.OrdinaryGroup.ACCESS_WIFI_STATE);
```

- requestMultiResult

```java
mdlPermissionCheck = MDLPermissionUtils.getInstance();
HashMap<String, Integer> tryPermission = new HashMap<String, Integer>();
for (int i = 0; i < permissionSet.size(); i++) {
    tryPermission.put(permissionArray[i], i + 1);
}
mdlPermissionCheck.requestMultiResult(this, tryPermission, new PermissionGrant() {
    @Override
    public void onPermissionGranted(int requestCode, String permission) {

  }
});
```

- PermissionFloatWindowUtils

```java
permissionFloatWindowUtils = PermissionFloatWindowUtils.getInstance();
boolean checkResult = permissionFloatWindowUtils.checkCanShowFloatWindow(this);
permissionFloatWindowUtils.grantShowFloatWindowPermission(this);
```

###License

---

Copyright 2017 sinlovgm@gmail.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
