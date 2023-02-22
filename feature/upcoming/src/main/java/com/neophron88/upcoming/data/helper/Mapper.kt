package com.neophron88.upcoming.data.helper

import com.neophron88.database.upcoming.models.UpcomingEntity
import com.neophron88.network.base.BaseUrl
import com.neophron88.network.upcoming.models.UpcomingResponse
import com.neophron88.upcoming.domain.models.Upcoming

private const val AUTOINCREMENT = 0L

fun List<UpcomingEntity>.mapToUpcomingList() = this.map {
    it.mapToUpcoming()
}

fun UpcomingEntity.mapToUpcoming() = Upcoming(
    id, url, iconUrl, startDate, endDate, name
)


fun List<UpcomingResponse>.mapToUpcomingEntityList(baseUrl: BaseUrl) = this.map {
    it.mapToUpcomingEntity(baseUrl)
}

fun UpcomingResponse.mapToUpcomingEntity(baseUrl: BaseUrl) =
    UpcomingEntity(AUTOINCREMENT, baseUrl.url.plus(url), iconUrl, startDate, endDate, name)