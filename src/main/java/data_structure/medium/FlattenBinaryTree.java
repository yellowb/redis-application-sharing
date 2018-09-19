package data_structure.medium;


public class FlattenBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 把一颗树扁平化成链表
     */
    public void flatten(TreeNode root) {
        if (root != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 递归, 先把左子树扁平化, 再把右子树扁平化, 最后把当前树扁平化
            flatten(left);
            flatten(right);

            // 扁平化当前树的逻辑(左右子树都已经扁平化完成)
            if (left != null) {
                // 如果左子树不为空, 就把左子树挂到右孩子节点
                root.right = left;
                root.left = null;
                if (right != null) {
                    // 如果右子树不为空, 因为左子树已经在先前递归中扁平化成链表了,
                    // 所以要把右子树挂到这个链表的尾巴
                    TreeNode leftTail = findRightSubTreeTail(left);
                    leftTail.right = right;
                }
            }
        }
    }

    /**
     * 给定一个子树的根节点, 不断沿着它的右孩子向下找,
     * 直到找到最后一个右孩子, 返回之
     */
    public TreeNode findRightSubTreeTail(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public static void printList(TreeNode root) {
        while (root != null) {
            System.out.print(root.val + " , ");
            root = root.right;
        }
    }

    public static void main(String[] args) {
        TreeNode node_1 = new TreeNode(1);
        TreeNode node_2 = new TreeNode(2);
        TreeNode node_3 = new TreeNode(3);
        TreeNode node_4 = new TreeNode(4);
        TreeNode node_5 = new TreeNode(5);
        TreeNode node_6 = new TreeNode(6);

        node_1.left = node_2;
        node_1.right = node_5;
        node_2.left = node_3;
        node_2.right = node_4;
        node_5.right = node_6;

        new FlattenBinaryTree().flatten(node_1);
        printList(node_1);

    }

}
