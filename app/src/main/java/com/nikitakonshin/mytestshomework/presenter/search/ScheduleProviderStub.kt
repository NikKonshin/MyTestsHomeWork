package com.nikitakonshin.mytestshomework.presenter.search

import com.nikitakonshin.mytestshomework.presenter.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ScheduleProviderStub: SchedulerProvider {
    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()
}