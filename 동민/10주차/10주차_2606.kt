import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))){
    val computerCount = readLine().toInt() // 컴퓨터의 수
    val times = readLine().toInt() // 네트워크 상에서 직접 연결되어있는 컴퓨터 쌍의 수

    // 2차원 배열을 통한 그래프 생성. 기본값을 false로 설정하여 이후 true로 표시
    var graph = Array(computerCount+1, {BooleanArray(computerCount+1,{false})})

    repeat(times){
        val (fir, sec) = readLine().split(" ").map{it.toInt()} // 컴퓨터 값 입력
        graph[fir][sec] = true // 연결되어있다는 표시 남김
        graph[sec][fir] = true // 연결되어있다는 표시 남김. 1-2나 2-1은 똑같기 때문에 둘다 남김
    }

    var starPoint : Queue<Int> = LinkedList() // BFS사용을 위한 Queue 선언
    starPoint.add(1) // 기본적으로 1번 컴퓨터는 감염되어있기 때문에 1 추가

    var visited = BooleanArray(computerCount+1, {false}) // 방문을 확인하기 위한 배열. 중복 방지
    visited[1] = true // 1번 컴퓨터는 감염되어있고, 해당 값부터 시작하기때문에 표시

    var cnt = 0 // 총 감염된 컴퓨터의 숫자
    while (starPoint.isNotEmpty()) { // BFS시작
        var num = starPoint.poll()

        for (i in 1..computerCount) {
            if (graph[num][i] && !visited[i]) { // 방문한 적이 없고, 해당 값이 연결되어있을 경우
                cnt++
                visited[i] = true
                starPoint.add(i)
            }
        }
    }

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    bw.write("${cnt}")
    bw.flush()
    bw.close()
}
