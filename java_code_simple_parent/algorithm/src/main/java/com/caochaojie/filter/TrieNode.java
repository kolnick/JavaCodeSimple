package com.caochaojie.filter;

import java.util.HashMap;

public class TrieNode {
    public boolean m_end;
    public HashMap<Character, TrieNode> m_values;

    public TrieNode() {
        m_values = new HashMap<>();
    }
}
