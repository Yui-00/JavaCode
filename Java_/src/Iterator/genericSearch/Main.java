package Iterator.genericSearch;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	//�e�X�g�p��main���\�b�h
	public static void main( String [ ] args ) {
		
		//�L�[�̌^��Integer�ŁC�l�̌^��Object�ł�����2���T���؂����D
		BinSearchTree<Integer,Integer> tree = new BinSearchTree<Integer,Integer>(new TreeComparator()) ;
		
		//1,2,..,n�̃����_���ȏ���𐶐�����
    	int n ;
    	Scanner keyboard = new Scanner(System.in) ;
    	System.out.println("#integers:") ;
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
    		Map.Entry<Integer,Integer> data = new AbstractMap.SimpleEntry<Integer,Integer>(array[i],array[i]) ;
    		tree.insert(data) ;
    	}
    	System.out.println("") ;
		
		//�����ƍ폜������e�X�g
		while(true){
			int t, s ;
			Integer value ;
			System.out.println("1.serch or 2.delete or 3,insurt or 4.exit") ;
			s = keyboard.nextInt();
			if(s == 1){
				System.out.println("Please serch number : ");
				t = keyboard.nextInt();
				value = tree.search(t) ;
				if(value == null){
					System.out.println("Not existence") ;	
				} else {
					System.out.println("Value : " + value) ;
				}
			} else if(s == 2){
				System.out.println("Please delete number : ");
				t = keyboard.nextInt();
				tree.delete(t) ;
				System.out.println("Delete") ;
			} else if(s == 3){
				System.out.println("Please insurt number : ");
				t = keyboard.nextInt() ;
				Map.Entry<Integer,Integer> data = new AbstractMap.SimpleEntry<Integer,Integer>(t,t) ;
				tree.insert(data) ;
			} else if(s == 4) {
				break ;
			}
		}
			
    	System.out.println("") ;
    	keyboard.close();
	}
}
		