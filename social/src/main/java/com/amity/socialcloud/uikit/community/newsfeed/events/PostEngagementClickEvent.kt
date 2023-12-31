package com.amity.socialcloud.uikit.community.newsfeed.events

import com.amity.socialcloud.sdk.model.social.post.AmityPost

sealed class PostEngagementClickEvent {

    class Reaction(val post: AmityPost, val isAdding: Boolean) : PostEngagementClickEvent()

    class Comment(val post: AmityPost) : PostEngagementClickEvent()

    class Sharing(val post: AmityPost) : PostEngagementClickEvent()

}