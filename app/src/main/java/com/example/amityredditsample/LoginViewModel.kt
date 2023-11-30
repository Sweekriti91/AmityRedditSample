package com.example.amityredditsample

import androidx.lifecycle.ViewModel
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.core.session.AccessTokenRenewal
import com.amity.socialcloud.sdk.helper.core.coroutines.await
import com.amity.socialcloud.sdk.model.core.session.SessionHandler

class LoginViewModel : ViewModel() {

    suspend fun login(
        userId: String,
        displayName: String
    ) {
        println("AMITY STATUS :: " + AmityCoreClient.getCurrentSessionState());
        AmityCoreClient.login(userId, sessionHandler = MySessionHandler())
            .authToken("")
            .displayName(displayName)
            .build()
            .submit()
            .doOnComplete {
                println("Complete!");
            }
            .doOnError {
                println(("Could not register user " + it.message));
            }
            .await()
    }

    private class MySessionHandler : SessionHandler {
        override fun sessionWillRenewAccessToken(renewal: AccessTokenRenewal) {
            renewal.renew()
        }
    }
}
