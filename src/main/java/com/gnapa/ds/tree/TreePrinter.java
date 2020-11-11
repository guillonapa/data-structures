package com.gnapa.ds.tree;

import java.io.PrintStream;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author Guillermo Narvaez
 */
public class TreePrinter {

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @return
     */
    public static TreePrinter get() {
        return new TreePrinter();
    }

    /**
     * <p>
     * Code taken from {@link https://www.baeldung.com/java-print-binary-tree-diagram}.
     * </p>
     * 
     * @param root
     * @return
     */
    public void print(TreeNode<?, ?> root) {
        System.out.println(toString(root));
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @param stream
     */
    public void print(TreeNode<?, ?> root, PrintStream stream) {
        stream.println(toString(root));
    }

    /**
     * <p>
     * TODO
     * </p>
     * 
     * @param root
     * @return
     */
    public String toString(TreeNode<?, ?> root) {
        return traversePreOrder(root);
    }

    /**
     * <p>
     * Code taken from
     * {@link https://www.baeldung.com/java-print-binary-tree-diagram}.
     * </p>
     * 
     * @param root
     */
    private String traversePreOrder(TreeNode<?, ?> root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.value());

        String pointerRight = "└──";
        String pointerLeft = (root.right() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.left(), root.right() != null);
        traverseNodes(sb, "", pointerRight, root.right(), false);

        return sb.toString();
    }

    /**
     * <p>
     * Code taken from
     * {@link https://www.baeldung.com/java-print-binary-tree-diagram}.
     * </p>
     * 
     * @param sb
     * @param padding
     * @param pointer
     * @param node
     * @param hasRightSibling
     */
    private void traverseNodes(StringBuilder sb, String padding, String pointer, TreeNode<?, ?> node,
            boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.right() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left(), node.right() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right(), false);
        }
    }
}
