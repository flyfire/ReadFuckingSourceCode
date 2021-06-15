import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers

fun main() {
    var a: Int
    Observable.empty<Unit>()
        .doOnComplete {
            a = 1
            println(a)
            println("onComplete")
        }
        .doFinally {
            a = 2
            println(a)
            println("finally")
        }.subscribe()
    a = 3
    println(a)

    Observable.create(object : ObservableOnSubscribe<String> {
        override fun subscribe(emitter: ObservableEmitter<String>) {
            emitter.onNext("hello")
        }
    })
        .subscribeOn(Schedulers.io())
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.newThread())
        .observeOn(Schedulers.trampoline())
        .subscribe(
        {
            println(it)
        },
        { throwable ->
            throwable.printStackTrace()
        }
    )

}