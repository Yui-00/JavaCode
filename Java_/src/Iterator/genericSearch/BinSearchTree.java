package Iterator.genericSearch;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//K�̓f�[�^�̃L�[�̌^�CV�̓f�[�^�̒l�̌^
public class BinSearchTree<K,V> {
	private BinSearchTreeNode<K,V> root ;
	private Comparator<K> comparator ;

	//�����Ȃ��̃R���X�g���N�^���֎~�D
	@SuppressWarnings("all")
	private BinSearchTree( ) { }

	//��������̃R���X�g���N�^�D
	public BinSearchTree(Comparator<K> comparator) {
		this.comparator = comparator ;
	}

	//��̖؂��ۂ��𔻒肷�郁�\�b�h
	public boolean isEmpty() {
		return root == null;
	}

	//���̖؂ɂ����Ďw��L�[�����f�[�^���������郁�\�b�h
	public V search(K key) {
		return search(root, key);
	}


	//start�����Ƃ���؂ɂ����Ďw��L�[�����f�[�^���������郁�\�b�h
	private V search(BinSearchTreeNode<K,V> start, K key) {
		//���݂̃m�[�h���L�����锠
		BinSearchTreeNode<K,V> temp = start ;
		
		if (start == null) return null ; //�؂���̏ꍇ
		
		//��������
		while(comparator.compare(temp.getData().getKey(), key) != 0){
			if(comparator.compare(temp.getData().getKey(),key) > 0){
				temp = temp.getLeft() ;
				if(temp == null){
					return null ;
				}
			}else if(comparator.compare(temp.getData().getKey(),key) < 0){
				temp = temp.getRight() ;
				if(temp == null){
					return null ;
				}
			}
		}
		return temp.getData().getValue() ;	
	}


	//���̖؂ɂ����Ďw��L�[�����f�[�^��}�����郁�\�b�h
	public void insert(Map.Entry<K,V> data) {
		root = insert(root, data);
	}


	//start�����Ƃ���؂ɐV�����f�[�^��}�����Ă���C���ʂ̖؂�Ԃ����\�b�h
	private BinSearchTreeNode<K,V> insert(BinSearchTreeNode<K,V> start, Map.Entry<K,V> data) {
		//���݂̃m�[�h���L�����锠
		BinSearchTreeNode<K,V> temp = start ;
		
		//�Ղ����ꍇ�F��̖؂̏ꍇ
		if (start == null) {
			return new BinSearchTreeNode<K,V>(data) ;
		}
		
		while(comparator.compare(temp.getData().getKey(), data.getKey()) != 0){
			if(comparator.compare(temp.getData().getKey(), data.getKey()) > 0){
				if(temp.getLeft() == null){
					temp.setLeft(new BinSearchTreeNode<K,V>(data)) ;
					return start ;
				}
				temp = temp.getLeft() ;
			}else if(comparator.compare(temp.getData().getKey(),data.getKey()) < 0){
				if(temp.getRight() == null){
					temp.setRight(new BinSearchTreeNode<K,V>(data)) ;
					return start ;
				}
				temp = temp.getRight() ;
			}
		}
		return start ;	
	}

	//�؂���w��̃L�[�����f�[�^���폜���Ă���C���ʂ̖؂�Ԃ����\�b�h
	public void delete(K key) {
		root = delete(root, key);
	}


	//�؂���w��̃L�[�����f�[�^���폜���Ă���C���ʂ̖؂�Ԃ����\�b�h
	private BinSearchTreeNode<K,V> delete(BinSearchTreeNode<K,V> start, K key) {
		//���݂̃m�[�h���L�����锠
		BinSearchTreeNode<K,V> temp = start ;
		//1�ߋ��̃m�[�h
		BinSearchTreeNode<K,V> rtemp = null ;
		
		//�폜�������m�[�h�͍��̎q�Ȃ̂��E�̎q�Ȃ̂�
		int flag = 5, swit = 0 ; //����0�A�E��1
		
		//�؂���̂Ƃ��C���̂܂ܕԂ�
		if (start == null) return null ;
		
		while(comparator.compare(temp.getData().getKey(), key) != 0){
			if(comparator.compare(temp.getData().getKey(),key) > 0){
				rtemp = temp ;
				temp = temp.getLeft() ;
				flag = 0 ;
				if(temp == null){
					return start ;
				}
			}else if(comparator.compare(temp.getData().getKey(),key) < 0){
				rtemp = temp ;
				temp = temp.getRight() ;
				flag = 1 ;
				if(temp == null){
					return start ;
				}
			}
		}
		//�m�[�h�����ł��t�ł����鎞
		if(rtemp == null && temp.isLeaf()){
			return null ;
		}
		//���̃m�[�h���t�̂Ƃ�
		if (temp.isLeaf()) {
			if(flag == 0){ 
				rtemp.setLeft(null) ;
				return start ;
			}
			if(flag == 1){
				rtemp.setRight(null) ;
				return start ;
			}
		}
		
		//�q�m�[�h�����Ƃ�
		if (temp.isTwinLeaf()) {
			//�q�m�[�h�����Ƃ�
			
			//�E�̖؂̍ŏ��l�m�[�h�̐e
			BinSearchTreeNode<K,V> rmintemp = temp ;
			//�E�̖؂̍ŏ��l�m�[�h
			BinSearchTreeNode<K,V> mintemp = temp.getRight() ;

			while(mintemp.getLeft() != null) { //�E�̖؂̍ŏ��l�����߂�
				swit = 1 ;
				rmintemp = mintemp ; //1�O��ێ�
				mintemp = mintemp.getLeft() ; 
			}
			if(mintemp.getRight() != null) {
				if(swit == 1){
					rmintemp.setLeft(mintemp.getRight()) ;
					
					mintemp.setLeft(temp.getLeft()) ;
					mintemp.setRight(temp.getRight()) ;
					if(rtemp != null){
						if(flag == 0){ 
							rtemp.setLeft(mintemp) ;
							return start ;
						}
						if(flag == 1){
							rtemp.setRight(mintemp) ;
							return start ;
						}
					} else {
						return mintemp ;
					}
				} else {
					mintemp.setLeft(temp.getLeft()) ;
					if(rtemp != null){
						if(flag == 0){ 
							rtemp.setLeft(mintemp) ;
							return start ;
						}
						if(flag == 1){
							rtemp.setRight(mintemp) ;
							return start ;
						}
						return start ;
					} else {
						return mintemp ;
					}
				}
			} else {
				if(swit == 1){
					mintemp.setLeft(temp.getLeft()) ;
					mintemp.setRight(temp.getRight()) ;
					rmintemp.setLeft(null) ;
					if(rtemp != null){
						if(flag == 0){ 
							rtemp.setLeft(mintemp) ;
							return start ;
						}
						if(flag == 1){
							rtemp.setRight(mintemp) ;
							return start ;
						}
					} else {
						return mintemp ;
					}
				} else {
					mintemp.setLeft(temp.getLeft()) ;
					if(rtemp != null){
						if(flag == 0){ 
							rtemp.setLeft(mintemp) ;
							return start ;
						}
						if(flag == 1){
							rtemp.setRight(mintemp) ;
							return start ;
						}
					} else {
						return mintemp ;
					}
				}
			}
		} else {
			////�q�m�[�h������ꍇ
			if(temp.getRight() == null){
				if(flag == 0){
					rtemp.setLeft(temp.getLeft()) ;
					return start ;
				} else {
					rtemp.setRight(temp.getLeft()) ;
					return start ;
				}
			} else {
				if(flag == 0){
					rtemp.setLeft(temp.getRight()) ;
					return start ;
				} else {
					rtemp.setRight(temp.getRight()) ;
					return start ;
				}
			}
		}
		return start;
	}

	//preorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> preorderIterator() {
		return preorder(root).iterator();
	}

	//preorderIterator���ł����Ăяo����Ȃ��ċA���\�b�h
	private List<Map.Entry<K,V>> preorder(BinSearchTreeNode<K,V> start) {
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>() ;
		if (start == null) return list ;
		else {
			list.add(start.getData()) ;
			list.addAll(preorder(start.getLeft())) ;
			list.addAll(preorder(start.getRight())) ;
			return list ;
		}
	}

	//inorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> inorderIterator() {
		return inorder(root).iterator();
	}

	//inorderIterator���ł����Ăяo����Ȃ��ċA���\�b�h
	private List<Map.Entry<K,V>> inorder(BinSearchTreeNode<K,V> start) {
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>() ;
		if (start == null) return list ;
		else {
			list.addAll(inorder(start.getLeft())) ;
			list.add(start.getData()) ;
			list.addAll(inorder(start.getRight())) ;
			return list ;
		}
	}

	//postorder�̏���2���T���؂ɂ���f�[�^�����ǂ邽�߂̃��\�b�h
	public Iterator<Map.Entry<K,V>> postorderIterator() {
		return postorder(root).iterator();
	}

	//postorderIterator���ł����Ăяo����Ȃ��ċA���\�b�h
	private List<Map.Entry<K,V>> postorder(BinSearchTreeNode<K,V> start) {
		List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>() ;
		if (start == null) return list ;
		else {
			list.addAll(postorder(start.getLeft())) ;
			list.addAll(postorder(start.getRight())) ;
			list.add(start.getData()) ;
			return list ;
		}
	}

	//accessor���\�b�h
	public BinSearchTreeNode<K,V> getRoot() {
		return root ;
	}
	public void setRoot(BinSearchTreeNode<K,V> root) {
		this.root = root ;
	}
}
