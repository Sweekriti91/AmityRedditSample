# AmityRedditSample
AmitySDK Tutorial for Reddit-like sample https://www.amity.co/tutorials/how-to-build-your-own-reddit-style-app-on-android

## Setup

- Clone repo
- Open in Android Studio
- Add `<API-KEY>` value in [AmityRedditSampleApp.kt](app/src/main/java/com/example/amityredditsample/AmityRedditSampleApp.kt)
- Build App 

## Versions

Android Studio : 

- Android Studio Giraffe | 2022.3.1
- Build #AI-223.8836.35.2231.10406996, built on June 28, 2023
- Runtime version: 17.0.6+0-17.0.6b829.9-10027231 aarch64
- VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.


Gradle Project Settings : 
- AGP Plugin: 8.1.0
- Gradle Version : 8.0

Operating System : macOS Ventura 

## Steps Followed 

**NOTE** Steps followed from Tutorial sample https://www.amity.co/tutorials/how-to-build-your-own-reddit-style-app-on-android

- Created File -> New App, no activity
- Build to check, blank app builds no errors
- Cloned GitHub Repo : https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource to separate folder
- Imported module (from the above Cloned GitHub Repo), copied folder `buildsystem`
- Add `AppManifest.xml` properties from GitHub sample app (Tutorial does not mention permissions needed, they come as build errors instead)
- Made path adjustments and followed other setup steps from Tutorial 
- Errors hit below when trying to Make App (see below for details)

## Errors 

### Error 1 : Namespace not specified. Specify a namespace in the module's build file. See

Resolved by adding namespace to `build.gradle` of `amity-uikit`, `chat`, `common` and `social` modules

### Error 2 : 'compileDebugJavaWithJavac' task (current target is 1.8) and 'kaptGenerateStubsDebugKotlin' task (current target is 17) jvm target compatibility should be set to the same Java version. 

Resolved by following :https://kotl.in/gradle/jvm/toolchain, added following snippet to `build.gradle` of `amity-uikit`, `chat`, `common` and `social` modules.

``` 
tasks.withType(org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask).configureEach {
    kotlinOptions.jvmTarget = '1.8'
}

```

### Error 3 : Class 'FragmentStoreOwner' is not abstract and does not implement abstract member public abstract val viewModelStore: ViewModelStore defined in androidx.lifecycle.ViewModelStoreOwner

This error is from [Amity Source Code](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource), in the [FragementStoreOwner.kt](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource/blob/production/common/src/main/java/com/amity/socialcloud/uikit/common/base/FragmentStoreOwner.kt) file

Resolved by change code to :

``` 
package com.amity.socialcloud.uikit.common.base

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class FragmentStoreOwner : ViewModelStoreOwner {
    private val store = ViewModelStore()
    override val viewModelStore: ViewModelStore
        get() = store
}

```

### Error 4 : Smart cast to 'AmityCommunity' is impossible, because 'community' is a mutable property that could have been changed by this time

This error is from [Amity Source Code](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource), in the [AmityCommunityMembersViewModel.kt](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource/blob/production/social/src/main/java/com/amity/socialcloud/uikit/community/members/AmityCommunityMembersViewModel.kt) file

**BLOCKED/WORKAROUND/NOT SURE IF CORRECT**

Temporary Workaround :

``` 
 if (community != null) {
            return Flowable.just(this.community!!)
}

```

### Error 5 : Unresolved reference: LiveDataReactiveStreams

This error is from [Amity Source Code](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource), in the [AmityBaseCreatePostFragment.kt](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource/blob/production/social/src/main/java/com/amity/socialcloud/uikit/community/newsfeed/fragment/AmityBaseCreatePostFragment.kt#L19) file. 

Also, related error to changed import, [`Flowable.fromPublisher`](https://github.com/AmityCo/Amity-Social-Cloud-UIKit-Android-OpenSource/blob/production/social/src/main/java/com/amity/socialcloud/uikit/community/newsfeed/fragment/AmityBaseCreatePostFragment.kt#L193) line 193 has error.

**BLOCKED/WORKAROUND/NOT SURE IF CORRECT** 

- Maybe related to this breaking API change : https://developer.android.com/jetpack/androidx/releases/lifecycle#2.6.0 ? 

Possible Workaround : `toPublisher(viewLifecycleOwner, viewModel.getImages())` 