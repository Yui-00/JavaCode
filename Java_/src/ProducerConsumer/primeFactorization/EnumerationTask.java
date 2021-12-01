package ProducerConsumer.primeFactorization ;

import java.util.Random;
// BlockingQueue�C���^�[�t�F�[�X���g���̂ŕK�v
import java.util.concurrent.BlockingQueue;
public class  EnumerationTask implements Runnable {  
	protected final static Integer DUMMY_INT = Integer.valueOf(0) ; //�񋓂̏I���������̂Ɏg����. 
	protected BlockingQueue<Integer> queue ;  // �u���b�L���O�L���[���w���ϐ�. 
	protected int max = 5000 ; 

	// �R���X�g���N�^�i�񋓂̎d�����P���j 
	public EnumerationTask(BlockingQueue<Integer> queue) {  
		this.queue = queue ; // �u���b�L���O�L���[���Ăяo�����̃v���O����������炤. 
	}  

	// �����X���b�h�̍s���d���i�w�肳�ꂽ�f�B���N�g���Ƃ��̃T�u�f�B���N�g���̉��ɂ��t�@�C���̗񋓁j 
	public void run( ) {  
		try { 
			enumerate(); //���상�\�b�h�i���Łj�Ńt�@�C����񋓂��ăL���[�ɒǉ�. 
			queue.put(DUMMY_INT); // �L���[�Ƀ_�~�[��u���ii.e., �񋓂̊����̍��}�𑗂�j. 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} 
	} 

	// ����directory�̎����f�B���N�g���̉��ɂ���t�@�C���i�T�u�f�B���N�g�����܂ށj�� 
	// ���ׂė񋓂��ău���b�L���O�L���[�ɒu�����߂̃��\�b�h. 
	public void enumerate( ) throws InterruptedException { 
		
		int count = 0 ;
		Random random = new Random();
		
		while(true){
			int randomValue = random.nextInt(10000) + 10000 ;
			if(isComposite(randomValue) == true){
				queue.put(randomValue) ;
				count ++ ;
				if (count == max){
					return ;
				}
			}
		}
		
		//�����������f
	} // enumerate���\�b�h�̏I���
	
	//���������ǂ������f���郁�\�b�h
	public boolean isComposite(int arg){
		for(int i = 2; i < arg; i++){
			if (arg % i == 0) {
        		return true ;
			}
		}
        return false;
	}
} // EnumerationTask.java �̏I���