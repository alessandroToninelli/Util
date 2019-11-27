package com.toninelli.fileutil

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject


@Suppress("UNCHECKED_CAST")
object RxBus {

    private val mapComposite = mutableMapOf<String, CompositeDisposable>()

    private val publishMap = mutableMapOf<Class<*>, PublishSubject<Any>?>()
    private val behaviorMap = mutableMapOf<Class<*>, BehaviorSubject<Any>?>()


    fun sendBehavior(event: RxBusEvent) {
        getBehaviorSubject(event::class.java).onNext(event)

    }

    fun sendPublic(event: RxBusEvent) {
        getPublishSubject(event::class.java).onNext(event)
    }

    fun clear(stringId: String) {
        getComposite(stringId).clear()

    }

    fun <T> listenBehavior(
        eventClass: Class<T>,
        stringId: String,
        onSend: (T) -> Unit
    ) {


        getComposite(stringId).add(
            getBehaviorSubject(eventClass)
                .subscribe {
                    val value = it as T
                    onSend(value)
                })


    }

    fun <T> listenPublic(
        eventClass: Class<T>,
        stringId: String,
        onSend: (T) -> Unit
    ) {


        getComposite(stringId).add(
            getPublishSubject(eventClass)
                .subscribe {
                    val value = it as T
                    onSend(value)
                })


    }
    private fun <T> getBehaviorSubject(eventClass: Class<T>): BehaviorSubject<Any>{
        return ifNull(behaviorMap[eventClass]){
            behaviorMap[eventClass] = BehaviorSubject.create()
            behaviorMap[eventClass]!!
        }
    }

    private fun <T> getPublishSubject(eventClass: Class<T>): PublishSubject<Any>{
        return ifNull(publishMap[eventClass]){
            publishMap[eventClass] = PublishSubject.create()
            publishMap[eventClass]!!
        }
    }

    private fun getComposite(stringId: String): CompositeDisposable {
        return mapComposite[stringId]?.let { it } ?: kotlin.run {
            val newComposite = CompositeDisposable()
            mapComposite[stringId] = newComposite
            newComposite
        }
    }


}

interface RxBusEvent









