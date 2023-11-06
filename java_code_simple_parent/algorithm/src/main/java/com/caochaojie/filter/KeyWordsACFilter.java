package com.caochaojie.filter;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @Author: caochaojie
 * @Date: 2019/4/30
 */
public class KeyWordsACFilter implements IKeyWordsFilter {

    private final DFANode dfaEntrance;
    private final char[] ignoreChars;
    private final char subChar;
    String nonChinese = "[^\\u4e00-\\u9fa5]";
    Pattern NAME_NON_CHINESE;
    String special;
    Pattern NAME_SPECIAL;
    private static final char[] ignowLowerCaseChars = new char[]{'İ', 'K'};

    public KeyWordsACFilter(char[] ignore, char substitute) {
        this.NAME_NON_CHINESE = Pattern.compile(this.nonChinese);
        this.special = "[^a-zA-Z0-9.\\u4e00-\\u9fa5]";
        this.NAME_SPECIAL = Pattern.compile(this.special);
        this.dfaEntrance = new DFANode();
        this.ignoreChars = new char[ignore.length];
        System.arraycopy(ignore, 0, this.ignoreChars, 0, this.ignoreChars.length);
        this.subChar = substitute;
    }

    public boolean initialize(String[] keyWords) {
        this.clear();

        for (int s = 0; s < keyWords.length; ++s) {
            String _keyword = keyWords[s];
            if (_keyword != null && (_keyword = _keyword.trim()).length() != 0) {
                char[] patternTextArray = _keyword.toCharArray();
                DFANode currentDFANode = this.dfaEntrance;

                for (int i = 0; i < patternTextArray.length; ++i) {
                    char _c = patternTextArray[i];
                    Character _lc = this.toLowerCaseWithoutConfict(_c);
                    DFANode _next = (DFANode) currentDFANode.dfaTransition.get(_lc);
                    if (_next == null) {
                        _next = new DFANode();
                        currentDFANode.dfaTransition.put(_lc, _next);
                    }

                    currentDFANode = _next;
                }

                if (currentDFANode != this.dfaEntrance) {
                    currentDFANode.isTerminal = true;
                }
            }
        }

        this.buildFailNode();
        return true;
    }

    private final void buildFailNode() {
        List<DFANode> queues = new ArrayList();
        this.dfaEntrance.failNode = this.dfaEntrance;

        Iterator<DFANode> curNode;
        DFANode failNode;
        for (curNode = this.dfaEntrance.dfaTransition.values().iterator(); curNode.hasNext(); failNode.failNode = this.dfaEntrance) {
            failNode = (DFANode) curNode.next();
            failNode.level = 1;
            queues.add(failNode);
        }

        curNode = null;
        failNode = null;

        while (!queues.isEmpty()) {
            DFANode node = (DFANode) queues.remove(0);
            failNode = node.failNode;
            Iterator it = node.dfaTransition.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<Character, DFANode> nextTrans = (Map.Entry) it.next();
                Character nextKey = nextTrans.getKey();

                DFANode nextNode;
                for (nextNode = nextTrans.getValue(); failNode != this.dfaEntrance && !failNode.dfaTransition.containsKey(nextKey); failNode = failNode.failNode) {
                }

                nextNode.failNode = failNode.dfaTransition.get(nextKey);
                if (nextNode.failNode == null) {
                    nextNode.failNode = this.dfaEntrance;
                }

                nextNode.level = node.level + 1;
                queues.add(nextNode);
            }
        }

    }

    public String filt(String s) {
        char[] input = s.toCharArray();
        char[] result = s.toCharArray();
        boolean _filted = false;
        DFANode currentDFANode = this.dfaEntrance;
        DFANode _next = null;
        int replaceFrom = 0;

        for (int i = 0; i < input.length; ++i) {
            Character _lc = this.toLowerCaseWithoutConfict(input[i]);

            for (_next = (DFANode) currentDFANode.dfaTransition.get(_lc); _next == null && currentDFANode != this.dfaEntrance; _next = (DFANode) currentDFANode.dfaTransition.get(_lc)) {
                currentDFANode = currentDFANode.failNode;
            }

            if (_next != null) {
                currentDFANode = _next;
            }

            if (currentDFANode.isTerminal) {
                int j = i - (currentDFANode.level - 1);
                if (j < replaceFrom) {
                    j = replaceFrom;
                }

                for (replaceFrom = i + 1; j <= i; ++j) {
                    if (!this.isIgnore(input[j])) {
                        result[j] = this.subChar;
                        _filted = true;
                    }
                }
            }
        }

        if (_filted) {
            return String.valueOf(result);
        } else {
            return s;
        }
    }

    public boolean contain(String inputMsg) {
        char[] input = inputMsg.toCharArray();
        DFANode currentDFANode = this.dfaEntrance;
        DFANode _next = null;

        for (int i = 0; i < input.length; ++i) {
            Character _lc = this.toLowerCaseWithoutConfict(input[i]);

            for (_next = (DFANode) currentDFANode.dfaTransition.get(_lc); _next == null && currentDFANode != this.dfaEntrance; _next = (DFANode) currentDFANode.dfaTransition.get(_lc)) {
                currentDFANode = currentDFANode.failNode;
            }

            if (_next != null) {
                currentDFANode = _next;
            }

            if (currentDFANode.isTerminal) {
                return true;
            }
        }

        return false;
    }

    private void clear() {
        this.dfaEntrance.dfaTransition.clear();
    }

    private char toLowerCaseWithoutConfict(char c) {
        return c != ignowLowerCaseChars[0] && c != ignowLowerCaseChars[1] ? Character.toLowerCase(c) : c;
    }

    private boolean isIgnore(char c) {
        for (int i = 0; i < this.ignoreChars.length; ++i) {
            if (c == this.ignoreChars[i]) {
                return true;
            }
        }

        return false;
    }

    private String filterSpecialContent(String content) {
        return this.NAME_SPECIAL.matcher(content).replaceAll("");
    }

    private String filterNonChineseContent(String content) {
        return this.NAME_NON_CHINESE.matcher(content).replaceAll("");
    }

    public boolean isValidName(String name) {
        String filterName = this.filterSpecialContent(name);
        boolean valid = !this.contain(filterName);
        if (valid) {
            filterName = this.filterNonChineseContent(name);
            valid = !this.contain(filterName);
        }

        return valid;
    }


    private static class DFANode {
        public boolean isTerminal = false;
        private final HashMap<Character, DFANode> dfaTransition = new HashMap();
        DFANode failNode = null;
        int level = 0;

        public DFANode() {
        }
    }

    public static void main(String[] args) {

    }
}
