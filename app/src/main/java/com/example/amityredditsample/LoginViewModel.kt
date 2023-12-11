package com.example.amityredditsample

import androidx.lifecycle.ViewModel
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.core.session.AccessTokenRenewal
import com.amity.socialcloud.sdk.model.core.session.SessionHandler
import com.example.amitywrap.AmityWrap

class LoginViewModel : ViewModel() {

    suspend fun login(
        userId: String,
        displayName: String
    ) {
        println("AMITY STATUS :: " + AmityCoreClient.getCurrentSessionState());


        val test = AmityWrap()
        test.loginAmity(userId,displayName, "AUTH_TOKEN")

//        AmityCoreClient.login(userId, sessionHandler = MySessionHandler())
//            .authToken("9dec888c75eacb8e0562e334f4674ae327324003")
//            .displayName(displayName)
//            .build()
//            .submit()
//            .doOnComplete {
//                println("Complete!");
//            }
//            .doOnError {
//                println(("Could not register user " + it.message));
//            }
//            .await()
    }

    private class MySessionHandler : SessionHandler {
        override fun sessionWillRenewAccessToken(renewal: AccessTokenRenewal) {
            renewal.renew()
        }
    }
}
