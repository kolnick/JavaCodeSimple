package com.caochaojie.filter;

public class TrieFilter extends TrieNode {

    public void addKey(String key) {
        if (key.isEmpty()) {
            return;
        }
        TrieNode node = this;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            TrieNode subnode = node.m_values.get(c);
            if (subnode == null) {
                subnode = new TrieNode();
                node.m_values.put(c, subnode);
            }
            node = subnode;
        }
        node.m_end = true;
    }

    /**
     * 检查是否包含非法字符
     *
     * @param text 输入文本
     * @return 找到的第1个非法字符.没有则返回false
     */
    public boolean hasBadWord(String text) {
        int len = text.length();
        for (int i = 0; i < len; i++) {
            TrieNode node = m_values.get(text.charAt(i));
            if (node == null) {
                continue;
            }
            // 单独字的敏感词
            if (node.m_end) {
                return true;
            }
            for (int j = i + 1; j < len; j++) {
                node = node.m_values.get(text.charAt(j));
                if (node == null) {
                    break;
                }
                // 第一个非法字符为:text.Substring(i, j + 1 - i);
                if (node.m_end) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换非法字符
     *
     * @param text
     * @param c    用于代替非法字符
     * @return 替换后的字符串
     */
    public String replace(String text, char c) {
        char[] chars = null;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            int start = -1;
            int end = -1;
            TrieNode subNode = m_values.get(text.charAt(i));
            if (subNode == null) {
                continue;
            }
            start = i;
            if (subNode.m_end) {
                end = i;
            }
            for (int j = i + 1; j < length; j++) {
                subNode = subNode.m_values.get(text.charAt(j));
                if (subNode == null) {
                    break;
                }
                if (subNode.m_end) {
                    end = Math.max(end, j);
                }
            }
            if (end == -1) {
                continue;
            }
            if (chars == null) {
                chars = text.toCharArray();
            }
            for (int t = start; t <= end; t++) {
                chars[t] = c;
            }
            i = end + 1;
        }
        return chars == null ? text : new String(chars);
    }
}
