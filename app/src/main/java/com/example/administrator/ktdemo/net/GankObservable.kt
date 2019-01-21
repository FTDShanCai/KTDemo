package com.example.administrator.ktdemo.net

import android.util.Log
import com.example.administrator.ktdemo.entity.GankBaseResult
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * @author ddc
 * 邮箱: 931952032@qq.com
 * <p>description:
 */
abstract class GankObservable<T> : Observer<T> {

    abstract fun OnSuccess(t: T)

    abstract fun OnError(text: String)

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        if (t is GankBaseResult<*>) {
            val result = t as GankBaseResult<*>
            if (result.error!!) {
                OnError("网络错误")
            } else {
                OnSuccess(t)
            }
        } else {
            OnSuccess(t)
        }
    }

    override fun onError(e: Throwable) {
        Log.e("ftd", e.toString())
        OnError("e:" + e.toString())
    }

}