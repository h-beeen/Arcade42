import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val N = readLine().toInt()
    val graph = Array(N + 1) { LinkedList<Int>() } // 메모리를 줄이기 위해서 LinkedList를 통한 배열 선언
    val parent = IntArray(N + 1) // 결과물 리스트
    val visited = BooleanArray(N + 1) { false } // 방문 확인

    repeat(N - 1) { // 값 입력
        val (node1, node2) = readLine().split(" ").map { it.toInt() }
        graph[node1].add(node2)
        graph[node2].add(node1)
    }

    visited[1] = true
    dfs(graph, visited, parent, N,1) // dfs로 부모 확인

    for (i in 2..N) // 1번 노드의 부모는 없기때문에 2번부터 순회
        bw.write("${parent[i]}\n")

    bw.flush()
    bw.close()
}


fun dfs(graph: Array<LinkedList<Int>>, visited: BooleanArray, parent: IntArray,N: Int, now: Int) {
    graph[now].forEach{
        if (!visited[it]) {
            visited[it] = true
            parent[it] = now // it의 부모는 now이므로 기록
            dfs(graph, visited, parent, N, it)
        }
    }
}
