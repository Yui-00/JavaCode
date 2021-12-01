package Visitor.serching2 ;

import java.util.Map;

//K�̓f�[�^�̃L�[�̌^�CV�̓f�[�^�̒l�̌^
public class BinSearchTreeNode<K,V> {
	private Map.Entry<K,V> data ;
	private BinSearchTreeNode<K,V> left, right ;

	//�R���X�g���N�^
	public BinSearchTreeNode(Map.Entry<K,V> data, BinSearchTreeNode<K,V> left,
			BinSearchTreeNode<K,V> right) {
		this.data = data ;
		this.left = left ;
		this.right = right ;
	}

	//�t����邽�߂̃R���X�g���N�^
	public BinSearchTreeNode(Map.Entry<K,V> data) {
		this(data, null, null) ;
	}

	//�t���ۂ��𔻒肷�郁�\�b�h
	public boolean isLeaf() {
		return left == null && right == null ;
	}


	//accessor���\�b�h
	public Map.Entry<K,V> getData() {
		return data ;
	}
	public BinSearchTreeNode<K,V> getLeft() {
		return left ;
	}
	public BinSearchTreeNode<K,V> getRight() {
		return right ;
	}
	public void setData(Map.Entry<K,V> data) {
		this.data = data ;
	}
	public void setLeft(BinSearchTreeNode<K,V> left) {
		this.left = left ;
	}
	public void setRight(BinSearchTreeNode<K,V> right) {
		this.right = right ;
	}
}
