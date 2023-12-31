package com.amity.socialcloud.uikit.community.explore.viewmodel

import androidx.paging.PagingData
import com.amity.socialcloud.sdk.api.social.AmitySocialClient
import com.amity.socialcloud.sdk.api.social.community.AmityCommunityRepository
import com.amity.socialcloud.sdk.model.social.community.AmityCommunity
import com.amity.socialcloud.uikit.common.base.AmityBaseViewModel
import com.amity.socialcloud.uikit.community.explore.listener.AmityCommunityItemClickListener
import io.reactivex.rxjava3.core.Flowable

class AmityCategoryCommunityListViewModel : AmityBaseViewModel() {

    var communityItemClickListener: AmityCommunityItemClickListener? = null

    private val communityRepository: AmityCommunityRepository =
        AmitySocialClient.newCommunityRepository()

    fun getCommunityByCategory(parentCategoryId: String): Flowable<PagingData<AmityCommunity>> {
        return communityRepository
            .getCommunities()
            .categoryId(parentCategoryId)
            .includeDeleted(false)
            .build()
            .query()
    }
}