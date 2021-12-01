package Visitor.serching2 ;

import java.util.Comparator;

//K�̓f�[�^�̃L�[�̌^�CV�̓f�[�^�̒l�̌^
public class VisitableBinSearchTree2<K,V> extends BinSearchTree<K,V>
		implements BTAcceptor<K,V> {
	private BTVisitor<K,V> visitor ;

	//��������̃R���X�g���N�^
	public VisitableBinSearchTree2(Comparator<K> comparator) {
		super(comparator) ;
	}

	//Visitor�������󂯂郁�\�b�h
	public void accept(BTVisitor<K,V> visitor) {
		this.visitor = visitor ;
	}


	//�ؑS�̂��������郁�\�b�h
	public Object traverse( ) {
		return traverse(getRoot()) ;
	}

	//�؂̎w��m�[�hstart�����Ƃ��镔���؂��������郁�\�b�h
	private Object traverse(BinSearchTreeNode<K,V> start) {
		if (start == null)//��̖؂ł���Ƃ�
			return visitor.visitNull( );
		else {//��ł͂Ȃ��؂̂Ƃ�
			Object left = traverse(start.getLeft()) ;
			Object right = traverse(start.getRight()) ;
			return visitor.visitNode(left, right, start.getData());
		}
	}
}
