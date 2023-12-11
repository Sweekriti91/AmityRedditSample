package com.example.amitywrap

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.amity.socialcloud.sdk.api.core.AmityCoreClient
import com.amity.socialcloud.sdk.api.core.endpoint.AmityEndpoint
import com.amity.socialcloud.sdk.core.session.AccessTokenRenewal
import com.amity.socialcloud.sdk.model.core.session.SessionHandler
import com.amity.socialcloud.uikit.community.home.fragments.AmityCommunityHomePageFragment
import com.amity.socialcloud.uikit.community.mycommunity.fragment.AmityMyCommunityFragment
import com.amity.socialcloud.uikit.community.profile.fragment.AmityUserProfilePageFragment

public class AmityWrap () {

    public fun setupAmity(apiKey: String)
    {
        AmityCoreClient
            .setup(apiKey, AmityEndpoint.US)
            .subscribe()
    }

    public fun PrintAmityStatus() : String
    {
        return AmityCoreClient.getCurrentSessionState().toString()
    }

    public fun loginAmity(userName: String, displayName : String, authToken: String)
    {
        AmityCoreClient.login(userName, sessionHandler = MySessionHandler())
            .authToken(authToken)
            .displayName(displayName)
            .build()
            .submit()
            .doOnComplete {
                println("Complete!");
            }
            .doOnError {
                println(("Could not register user " + it.message));
            }
            .subscribe()
    }

    public fun showHomeNewsFeed(): Fragment {
        return AmityCommunityHomePageFragment.newInstance()
            .build()
    }

    public fun showProfilePage(activity: AppCompatActivity): Fragment {
        return AmityUserProfilePageFragment.newInstance(AmityCoreClient.getUserId())
            .build(activity)
    }

    public fun showMyCommunityPage(): Fragment {
        return AmityMyCommunityFragment.newInstance()
            .build()
    }

    private class MySessionHandler : SessionHandler {
        override fun sessionWillRenewAccessToken(renewal: AccessTokenRenewal) {
            renewal.renew()
        }
    }
}