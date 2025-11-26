package data_structure.tree


/**
 * BST
 *
 * 1. 기능 요구 사항
 *      - 추가
 *      - 검색
 *      - 삭제
 *
 *  2. BST 규칙
 *      - 왼쪽 서브트리 < 부모 노드 < 오른쪽 서브트리
 *      - 중복값은 오른쪽에 삽입
 *
 *  3. 구현
 *   추가(재귀)
 *      - case 1 현재 노드가 null -> node 생성 후 반환
 *      - case 2 값 < 현재 노드  -> leftnode로 재귀
 *      - case 3 값 >= 현재 노드 -> right node로 재귀
 *   탐색(재귀)
 *      - case 1 현재 노드가 null -> false 반환
 *      - case 2 값 == 현재 노드 -> true
 *      - case 3 값 < 현재 노드 -> leftnode로 재귀
 *      - case 4 값 >= 현재 노드 -> rightnode로 재귀
 *   삭제(재귀)
 *      - 탐색
 *          - case 1 현재 노드가 null(값이 없을때) -> return null
 *          - case 2 값 = 현재노드 (값이 있을때)
 *              - case 2-1 자식이 없을때 return null
 *              - case 2-2 자식이 한명일때 return leftchild
 *              - case 2-3 자식이 두명일때
 *                  1. 왼쪽 자식중 가장 큰값 찾기
 *                  2. 가장 큰값을 현재노드에 넣기
 *                  3. 왼쪽 자식중 가장 큰값 노드 삭제
 */
class BinarySearchTree {
    var root: Node? = null

    fun insert(value: Int) {
        root = insertProc(root, value)
    }

    fun delete(value: Int) {
        root = deleteProc(root, value)
    }

    fun search(value: Int): Boolean {
        return searchProc(root, value)
    }

    private fun insertProc(node: Node?, value: Int): Node {
        node ?: return Node(value = value)
        when {
            value < node.value -> node.left = insertProc(node.left, value)
            else -> node.right = insertProc(node.right, value)
        }
        return node
    }

    private fun searchProc(node: Node?, value: Int): Boolean {
        node ?: return false
        when {
            value < node.value -> return searchProc(node.left, value)
            value > node.value -> return searchProc(node.right, value)
            else -> return true;
        }
    }

    private fun deleteProc(node: Node?, value: Int): Node? {
        node ?: return null
        when {
            value < node.value -> node.left = deleteProc(node.left, value)
            value > node.value -> node.right = deleteProc(node.right, value)
            else -> {
                if (node.left == null && node.right == null) return null
                else if (node.left != null && node.right == null) return node.left
                else if (node.left == null && node.right != null) return node.right
                else {
                    val leftChildMaxValue = findMax(node.left!!)
                    node.value = leftChildMaxValue
                    node.left = deleteProc(node.left, node.value)
                    return node
                }
            }
        }
        return node
    }

    private fun findMax(node: Node): Int {
        var current = node
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }

    fun inorder(node: Node? = this.root) {
        node ?: return
        inorder(node.left)
        print("${node.value} ")
        inorder(node.right)
    }

    data class Node(
        var left: Node? = null,
        var right: Node? = null,
        var value: Int,
    )
}
