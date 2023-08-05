import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var cntLTH = 0 // 본인보다 무거운 구슬을 세는 카운트
var cntHTL = 0 // 본인보다 가벼운 구슬을 세는 카운트

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))){
    val (N, M) = readLine().split(" ").map { it.toInt() }

    var graph = Array(N+1, { IntArray(N+1,{0}) }) // 그래프 생성
    repeat(M){
        val (fir, sec) = readLine().split(" ")
        graph[fir.toInt()][sec.toInt()] = 1 // 본인보다 무거운 구슬을 1로 표기
        graph[sec.toInt()][fir.toInt()] = 2 // 본인보다 가벼운 구슬을 2로 표기
    }

    var half = (N+1)/2 // 중간 이상 벗어났는지 확인하기 위한 변수
    var result = 0 // 결과값
    for (i in 1 .. N.toInt()){
        cntLTH = 0 // 초기화
        cntHTL = 0 // 초기화
        var visitedLowToHigh = BooleanArray(N+1, {false}) // 방문기록 -> 본인보다 무거운것 확인
        var visitedHighToLow = BooleanArray(N+1, {false}) // 방문기록 -> 본인보다 가벼운것 확인

        dfsLowToHigh(graph, visitedLowToHigh, i) // DFS 실행 -> 본인보다 무거운 것
        dfsHighToLow(graph, visitedHighToLow, i) // DFS 실행 -> 본인보다 가벼운 것

        if (cntHTL >= half || cntLTH >= half) // 본인보다 가볍거나 무거운게 절반 이상 있을경우 중간이 절대 될 수 없음
            result++
    }

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    bw.write("${result}")
    bw.flush()
    bw.close()
}

// DFS -> 방문하지 않았고, 본인보다 무거운 경우(1) 카운트를 늘리고 DFS 재귀 호출
fun dfsLowToHigh(graph: Array<IntArray>, visited: BooleanArray, positon: Int){
    visited[positon] = true

    for (i in 0 until graph[positon].size){
        if (!visited[i] && graph[positon][i] == 1){
            cntLTH++
            dfsLowToHigh(graph, visited, i)
        }
    }

}

// DFS -> 방문하지 않았고, 본인보다 가벼운 경우(2) 카운트를 늘리고 DFS 재귀 호출
fun dfsHighToLow(graph: Array<IntArray>, visited: BooleanArray, positon: Int){
    visited[positon] = true
    for (i in 0 until graph[positon].size){
        if (!visited[i] && graph[positon][i] == 2){
            cntHTL++
            dfsHighToLow(graph, visited, i)
        }
    }
}
