package com.hailin.blog.trie;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;

/**
*   
*  
* author:hailin 
* Date:2018/6/2  
* Time:14:26  
 * @Description
 * @Author 
 * @Date    
 * @Version
 */  

public class WordTree<T> {

    private Node root = new Node();

    private static final WordTree WORD_TREE = new WordTree();

    private WordTree(){}

    public static WordTree getWordTree(){
        return WORD_TREE;
    }

    public T deleteNode(String keywords){
        Node<T> node = findNodeBykeyWords(keywords);
        T result = null ;
        if(node != null && node.isHasPrimaryKey()){
            result = node.getPrimaryKey();
            node.setHasPrimaryKey(false);
        }
        return result;
    }

    public void addNode(String  keywords , T primaryKey){
        Node<T> tmp = root;
        if(!Strings.isNullOrEmpty(keywords)){
            for (Character c : keywords.toCharArray()){
                if(!tmp.getChildNoes().containsKey(c)){
                    tmp.getChildNoes().put(c ,  new Node<T>().setKeyword(c));
                }
                tmp = tmp.getChildNoes().get(c);
            }
            tmp.setHasPrimaryKey(true);
            tmp.setPrimaryKey(primaryKey);
        }

    }

    public Node findNodeBykeyWords(String  keywords){
        Node<T> result = root;
        if(!Strings.isNullOrEmpty(keywords)){
            for (Character c : keywords.toCharArray()){
                result = result.getChildNoes().get(c);
                if(result == null){
                    break;
                }
            }
        }
        return  result;
    }


    public T findPrimaryKeyByKeyWords(String  keywords){
        Node<T> node = findNodeBykeyWords(keywords);
        return node != null && node.isHasPrimaryKey() ?  node.getPrimaryKey() : null;
    }

    public boolean existPrimaryKeyWords(String  keywords){
        Node node = findNodeBykeyWords(keywords);
        return node != null && node.isHasPrimaryKey();
    }


    static class Node<T>{

        private T primaryKey;

        private Character keyword;

        /**
         * 在这个节点上是否有primaryKey
         */
        private boolean hasPrimaryKey;

        private Map<Character , Node<T>> childNoes = Maps.newHashMap();

        public T getPrimaryKey() {
            return primaryKey;
        }

        public void setPrimaryKey(T primaryKey) {
            this.primaryKey = primaryKey;
        }

        public boolean isHasPrimaryKey() {
            return hasPrimaryKey;
        }

        public void setHasPrimaryKey(boolean hasPrimaryKey) {
            this.hasPrimaryKey = hasPrimaryKey;
        }

        public Character getKeyword() {
            return keyword;
        }

        public Node setKeyword(Character keyword) {
            this.keyword = keyword;
            return this;
        }

        public Map<Character, Node<T>> getChildNoes() {
            return childNoes;
        }

        public void setChildNoes(Map<Character, Node<T>> childNoes) {
            this.childNoes = childNoes;
        }
    }

}
