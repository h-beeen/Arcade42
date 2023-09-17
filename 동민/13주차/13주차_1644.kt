import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.sqrt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val br = BufferedWriter(OutputStreamWriter(System.out))
    val N = readLine().toInt()

    var decimals =  mutableListOf<Int>()

    for (i in 2 .. N){ // N 까지의 소수 리스트 생성
        if (isPrimeNumber(i)) decimals.add(i)
    }

    br.write(getCnt(N, decimals).toString())
    br.flush()
    br.close()
}

// 소수인지 여부 리턴 함수
fun isPrimeNumber(targetNumber: Int): Boolean {
    for (i in 2 .. sqrt(targetNumber.toDouble()).toInt()) {
        if (targetNumber % i == 0) {
            return false
        }
    }
    return true
}

// 투포인터 사용 갯수 카운트 함수
fun getCnt(N: Int, decimals: MutableList<Int>): Int{
    var end = 0
    var sum = 0
    var cnt = 0
    val last = decimals.size

    for (start in 0 until last){
        while (sum < N && end < last){
            sum += decimals[end]
            end++
        }

        if (sum == N) cnt++

        sum -= decimals[start]
    }
    return cnt
}
