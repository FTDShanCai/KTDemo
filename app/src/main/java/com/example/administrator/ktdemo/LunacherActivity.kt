package com.example.administrator.ktdemo

import android.content.Intent
import android.os.Bundle
import com.example.administrator.ktdemo.base.RxSchedulers
import com.example.administrator.ktdemo.base.BaseActivity
import com.example.administrator.ktdemo.ui.activity.MainActivity
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

class LunacherActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_lunacher
    }

    override fun initViews(savedInstanceState: Bundle?) {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.compose())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(Consumer {
                    goMain()
                })
    }

    private fun goMain() {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
