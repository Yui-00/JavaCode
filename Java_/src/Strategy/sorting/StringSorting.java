package Strategy.sorting;

import java.util.Comparator;
import java.util.Scanner;

@SuppressWarnings("resource")
public class StringSorting {
	public static void main(String[] args) {
		//�L�[�{�[�h����̓��͂̂��߂ɃX�L���i�[�𐶐�
		Scanner scan = new Scanner(System.in) ;

		//�L�[�{�[�h���琮�񂵂���������̌������
		System.out.printf("#strings: ") ;
		int size = scan.nextInt();

		//size�̕�������i�[���邽�߂̃��������m��
		String[] array = new String[size] ;

		//size�̕�������L�[�{�[�h�������
		for (int i = 0 ; i < size ; i++) {
			System.out.printf("array[%d] = ", i) ;
			array[i] = scan.next();
		}

		//********* ���L�̇@�ƇA�̂ǂ��炩��I�� ********
		//�@���쐮��֐����Ăяo���ĕ�����z��array�𐮗�
		mySort(array, 0, size - 1,  new ReverseStringComparator()) ;

		//�AJava��sort���Ăяo���ĕ�����z��array�𐮗�
		//Arrays.sort(array, 0, size - 1, new ReverseStringComparator()) ;
        //***********************************************

		//���񌋉ʂ�\��
		System.out.println("Sorted array:") ;
		for (int i = 0 ; i < array.length ; i++) {
			System.out.printf("%s ", array[i]) ;
		}
		System.out.println();
	}


	//�ėp�N�C�b�N�\�[�g�p�̎���N���X�E���\�b�h
	public static <T> void mySort(T[ ] array, int left, int right, Comparator<T> cmp){
		
		T temp ;
	
		/*if((right - left) == 1) return ; //�f�[�^����Ȃ�Ȃɂ����Ȃ�
			//2�Ȃ��r���Č������ďI���
		if((right - left) == 2){
			if(cmp.compare(array[0], array[1]) > 0 ){
				temp = array[0] ;
				array[0] = array[1] ;
				array[1] = temp ;
			}
			return ;
		}
		*/
		
		if (left >= right) {
            return;
        }
		
		T x = array[(left + right) / 2] ; //pivot�͍���f�[�^�̒����l�Ƃ���
		int l = left, r = right ;
	
		//����
		while(l <= r){
			while(cmp.compare(x, array[l]) > 0) l++ ; //x���傫���l�܂Ői�߂�
			while(cmp.compare(x, array[r]) < 0) r-- ; //x��菬�����l�܂Ői�߂�
			
			if(l <= r){
				temp = array[l] ; array[l] = array[r] ; array[r] = temp ;
				l++ ; r-- ;
			}
		}
		mySort(array, left, r, cmp) ;
		mySort(array, l, right, cmp) ; 
	}
}
	/*public static <T> void mySort(T[ ] array, Comparator<T> cmp){
	
		T x,temp; //x�͊�l
		int left,right ;
		int size = array.length; //�z��̃T�C�Y�����߂Ă���
	
		x = array[(size-1) / 2] ; //pivot�͍���f�[�^�̒����l�Ƃ���
	
		if(size == 1) return ; //�f�[�^����Ȃ�Ȃɂ����Ȃ�
			//2�Ȃ��r���Č������ďI���
		if(size == 2){
			if(cmp.compare(array[0], array[1]) > 0 ){
				temp = array[0] ;
				array[0] = array[1] ;
				array[1] = temp ;
			}
			return ;
		}
	
		left = 0 ;
		right = size-1 ;
	
		//����
		while(true){
			while(cmp.compare(x, array[left]) > 0) left++ ; //x���傫���l�܂Ői�߂�
			while(cmp.compare(x, array[right]) < 0) right-- ; //x��菬�����l�܂Ői�߂�
			
			if(left >= right){
				break ; //�����͏I����Ă���̂ŏI��
			}else{
				temp = array[left] ;
				array[left] = array[right] ;
				array[right] = temp ;
				left++ ;
				right-- ;
			}
		}
		mySort(a, right + 1) ;
		quick(a + right + 1, n - (right + 1)) ; 
	}*/