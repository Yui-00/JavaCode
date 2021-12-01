package ProducerConsumer.primeFactorization ;

// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.BlockingQueue; 
 
public class FactorizationTask implements Runnable {   
    protected BlockingQueue<Integer> queue ;  // �u���b�L���O�L���[���w���ϐ�. 
  
   // �R���X�g���N�^�i�����̎d�����P���j 
    public FactorizationTask(BlockingQueue<Integer> queue) {  
        this.queue = queue; //�u���b�L���O�L���[�ւ̎Q�Ƃ��Ăяo�����̃v���O����������炤. 
    } 

    public void run() { // �����X���b�h�̍s���d���i�u���b�L���O�L���[����t�@�C����1���� 
                                     // ����Ă��āC���̒��Ō����L�[���[�h���܂ލs�����ׂČ�����j. 
        try { 
            while ( true ) { // ���L�������ƌJ��Ԃ��F
                Integer inte = queue.take( ) ; // �L���[���琮�����P����Ă���. 

                if ( inte == EnumerationTask.DUMMY_INT ) { // ����Ă����̂��_�~�[�Ȃ�, 
                    queue.put( inte ) ; // ���ꂪ�I���̍��}�Ȃ̂ŁC�L���[�ɖ߂��D
                    return ; // �������I�������D
               	} 
            	
            	String s = primeFactorization(inte) ;
            	safePrintln(s) ;
            
			}  // while-���̏I��� 
      } catch ( InterruptedException e) {
    	  Thread.currentThread().interrupt();
      } 
	} // run���\�b�h�̏I��� 
	
	//�f�����胁�\�b�h
	static boolean isPrime (int num) {
		if (num ==2) return true ;
		if (num < 2 || num % 2 == 0) return false ;
		int d = (int)Math.sqrt(num) ;
		for (int i = 3 ; i <= d; i += 2) {
			if(num % i == 0){
				return false;
			}
		}
		return true;
	}
	
	//�f�����������\�b�h
	static String primeFactorization (int n) {
		int count = 0, i ;
		String s = new String(Integer.toString(n) + " = ") ;
		int num = n ;
		
		for(i = 2; i <= num;){
			if(isPrime(i)){
				if(n % i == 0){
					count++ ;
					n = n/i ;
				} else {
					
					if(count == 0){
						if(n == 1){
							return s ;
						}
						i++ ;
						count = 0 ;
					}
					
					if(count == 1){
						s = s + (Integer.toString(i)) ;
						if(n == 1){
							return s ;
						}
						count = 0 ;
						if (num != i){
	            			s = s + "*" ;
						}
						i++ ;
					}
					
					if(count > 1){
						s = s + Integer.toString(i) + "^" + Integer.toString(count) ;
						if(n == 1){
							return s ;
						}
						count = 0 ;
						if (num != i){
	            			s = s + "*" ;
						}
						i++ ;
					}
				}
			} else {
				i++ ;
			}
		}
		return s ;
	}
	
	public void safePrintln(String s) {
		synchronized (System.out) {
			System.out.println(s);
		}
	}
		
} // SearchTask.java �̏I���