import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

fun main()  = with(BufferedReader(InputStreamReader(System.`in`))){
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = readLine().split(" ").map { it.toInt() }
    val graph = Array(N + 1) { mutableListOf<Int>() }
    val inDegree = IntArray(N + 1)
    val queue: Queue<Int> = LinkedList()
    val result = mutableListOf<Int>()

    repeat(M){
        val (A, B) = readLine().split(" ").map { it.toInt() }

        graph[A].add(B) // 자기 뒤에 있는 숫자 기록
        inDegree[B]++ // 자신 앞에 있는 숫자의 갯수 카운팅
    }

    for (i in 1 .. N){
        if (inDegree[i] == 0) queue.add(i) // 맨 앞에 올 수 있는 값
    }

    while (queue.isNotEmpty()){
        val now = queue.poll()
        result.add(now)

        for (i in graph[now]){ // 특정 숫자 뒤에 오는 값들의 카운팅 내리기
            inDegree[i]--

            if (inDegree[i] == 0) queue.add(i) // 자기 앞에 올 애가 없다면 큐에 넣기
        }
    }

    result.forEach{
        bw.write("$it ")
    }
    bw.flush()
    bw.close()
}
