import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var case = 0 // 최종 출력을 위한 변수

    while (true) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        if (n == 0 && m == 0) break // 종료조건
        case++

        val graph = Array(n + 1) { LinkedList<Int>() } // 그래프 선언
        val visited = BooleanArray(n + 1) // 방문여부 확인 배열
        var treeCnt = 0 // 결과물(트리의 개수 카운트)

        repeat(m) { // 입력
            val (node1, node2) = readLine().split(" ").map { it.toInt() }

            graph[node1].add(node2)
            graph[node2].add(node1)
        }

        for (i in 1 .. n){ // 순환이 존재하는지 체크
            if (!visited[i] && isTree(graph, visited, i, 0))
                treeCnt++ // 순환이 없을경우 -> 트리가 맞을 경우
        }

        bw.write("Case $case: ")
        when(treeCnt){
            0 -> bw.write("No trees.")
            1 -> bw.write("There is one tree.")
            else -> bw.write("A forest of ${treeCnt} trees.")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

fun isTree(graph: Array<LinkedList<Int>>, visited: BooleanArray, now: Int, before: Int): Boolean{
    if (visited[now]) // 방문한 곳을 왔다면 순환이 존재하는 것이기때문에 트리가 이님
        return false

    var result = true
    visited[now] = true

    graph[now].forEach{ next-> //
        if (next != before) // 이전값과 다음값이 같은경우 -> 1 - 2 - 1 이동을 막기 위한 장치
            result = (result && isCycle(graph, visited, next, now)) // 트리 내부에 한번이라도 순환이 존재하면 트리가 아니끼때문에 && 연산으로 확인
    }

    return result
}
