package Visitor.serching2 ;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StringSearch2 {
	//�e�X�g�p��main���\�b�h
	@SuppressWarnings("resource")
    public static void main( String [ ] args ) {
    	//�f�[�^�^��String�^�ł�����2���T���؂����D
    	VisitableBinSearchTree2<Integer,String> tree =
    		new VisitableBinSearchTree2<Integer,String>(
        		new Comparator<Integer>() {
    				public int compare(Integer a, Integer b) {
    					return a - b ;
    				}
    			}
    		) ;

    	//1,2,..,n�̃����_���ȏ���𐶐�����
    	int n ;
    	Scanner keyboard = new Scanner(System.in) ;
    	System.out.println("#students:") ;
    	n = keyboard.nextInt();
    	int[] array = new int[n] ;
    	for (int i = 0 ; i < n ; i++)
    		array[i] = i + 1;
    	Random rand = new Random() ;
    	for (int i = 0 ; i < n ; i++) {
    		int j = rand.nextInt(n - i) ;
    		int temp = array[n - 1 - i] ;
    		array[n - 1 - i] = array[j] ;
    		array[j] = temp ;
    	}

    	//��L�̖؂�n�̃f�[�^���C�������������_�����ɑ}��
    	for (int i = 0 ; i < n ; i++) {
    		//System.out.print(array[i] + " ");
    		tree.insert(new AbstractMap.SimpleEntry<Integer,String>(array[i], i + "-th string"));
    	}
    	System.out.println("") ;

    	//�؂̍��������߂�
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Integer visitNull( ) {
    			return 0 ;
    		}
    		public Integer visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			return Math.max((Integer)left, (Integer)right) + 1 ;
    		}
    	});
    	System.out.println("height: " + (Integer)tree.traverse());
    	System.out.println("") ;

    	//�؂�unbalance�x�����߂�
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Integer[] visitNull( ) {
    			Integer ans[] = new Integer[2] ;
    			ans[0] = 0 ; //�m�[�h�̑���
    			ans[1] = 0 ; //unbalance�l
    			return ans ;
    		}
    		public Integer[] visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			Integer ans[] = new Integer[2] ;
    			Integer a[] = new Integer[2] ;
    			Integer b[] = new Integer[2] ;
    			a = (Integer[])left ;
    			b = (Integer[])right ;
    			ans[0] = a[0] + b[0] + 1 ; //�������܂߂��m�[�h�̑���
    			ans[1] = Math.abs(a[0] - b[0]) ;
    			//3��unbalance�l�̍ő�l�����߂�
    			ans[1] = Math.max(ans[1], a[1]) ;
    			ans[1] = Math.max(ans[1], b[1]) ;
    			return ans ;
    		}
    	});
    	
    	Integer ans[] = new Integer[2] ;
    	ans = (Integer[])tree.traverse() ;
    	System.out.println("unbalance value: " + ans[1]);
    	System.out.println("") ;

    	//postorder���ɖ؂����ǂ�D
    	System.out.println("visiting the tree in post order");
    	tree.accept(new BTVisitor<Integer,String>() {
    		public Void visitNull( ) {
    			return null ;
    		}
    		public Void visitNode(Object left, Object right, Map.Entry<Integer,String> data) {
    			System.out.println(data.getKey() + ":" + data.getValue());
    			return null ;
    		}
    	});
    	tree.traverse();
    	System.out.println("") ;

    	//�����ƍ폜������e�X�g
    	int m = rand.nextInt(n) ;
    	System.out.println("trying to find " + m + " ...");
    	String result = tree.search(m) ;
    	if (result != null) {
    		System.out.println("found " + m + ":" + result) ; //���ʕ\��
        	System.out.println("") ;
    		System.out.println("trying to delete " + m + " ...");
    		tree.delete(m); //�폜
        	result = tree.search(m) ;
        	if (result != null)
        		System.out.println("Error: found the deleted data.") ;
        }
    	System.out.println("") ;

    	//�؂ɂ���f�[�^���L�[�̏��������ɕ\������ʂ̕��@�D
    	Iterator<Map.Entry<Integer,String>> iter = tree.inorderIterator() ;
    	System.out.println("data in sorted order:");
    	while (iter.hasNext()) {
    		Map.Entry<Integer,String> d = iter.next();
    		System.out.println(d.getKey() + "�F" + d.getValue());
    	}
    }
}