import io.reactivex.Observable

fun main() {
    var a = 0
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
}